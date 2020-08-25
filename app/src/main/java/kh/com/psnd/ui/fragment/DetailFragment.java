package kh.com.psnd.ui.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.snackbar.Snackbar;
import com.kelin.translucentbar.library.TranslucentBarManager;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.IOException;

import core.lib.base.BaseFragment;
import core.lib.dialog.DialogProgress;
import core.lib.utils.Log;
import core.lib.utils.OkHttpUtils;
import io.reactivex.CompletableObserver;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import kh.com.psnd.R;
import kh.com.psnd.database.config.AppDatabase;
import kh.com.psnd.database.entities.DetailStaffEntity;
import kh.com.psnd.databinding.FragmentDetailBinding;
import kh.com.psnd.eventbus.ImagePreviewSwipeEventBus;
import kh.com.psnd.helper.ActivityHelper;
import kh.com.psnd.network.model.SearchStaff;
import kh.com.psnd.network.model.Staff;
import kh.com.psnd.network.model.StaffRecord;
import kh.com.psnd.network.request.RequestStaff;
import kh.com.psnd.network.response.ResponseStaff;
import kh.com.psnd.network.task.TaskStaff;
import kh.com.psnd.ui.view.ItemDetailSectionView;
import lombok.val;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okio.BufferedSink;
import okio.Okio;

public class DetailFragment extends BaseFragment<FragmentDetailBinding> {
    private       DialogProgress progress = null;
    private final String         TAG_PDF  = "DownloadPDF";
    private final OkHttpClient   client   = new OkHttpClient();
    private       Staff          mStaff   = null;

    public static DetailFragment newInstance(@NonNull SearchStaff searchStaff) {
        val fragment = new DetailFragment();
        val bundle   = new Bundle();
        bundle.putSerializable(SearchStaff.EXTRA, searchStaff);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onImagePreviewSwipeEventBus(ImagePreviewSwipeEventBus event) {
        Log.i(event);
        if (mStaff != null && event.getStaff() != null && mStaff.getStaffId() == event.getStaff().getStaffId()) {
            binding.detailHeader.setCurrentImageStaff(event.getPosition());
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setEnabledEventBus(false);
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getBaseFragmentActivity().setSupportActionBar(binding.toolbar);
        val translucentBarManager = new TranslucentBarManager(this);
        translucentBarManager.transparent(this, view, R.color.colorPrimaryDark);
    }

    @Override
    public void setupUI() {
        progress = new DialogProgress(getContext(), true, dialogInterface -> OkHttpUtils.cancelCallWithTag(client, TAG_PDF));
        progress.setCanceledOnTouchOutside(false);

        val search = (SearchStaff) getArguments().getSerializable(SearchStaff.EXTRA);

        AppDatabase.getInstance().detailStaffDao().loadSingle_Rx(search.getStaffId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<DetailStaffEntity>() {
                    @Override
                    public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {
                        Log.i("onSubscribe");
                    }

                    @Override
                    public void onSuccess(@io.reactivex.annotations.NonNull DetailStaffEntity detailStaff) {
                        Log.i("onSuccess : detailStaff");
                        long currentTime = System.currentTimeMillis();
                        if (currentTime - detailStaff.getRecent() > DetailStaffEntity.INTERVAL_CHECK_NEW_DATA) {
                            loadData(search);
                        }
                        else {
                            Log.i("Show detail from database");
                            bindView(detailStaff.getStaff());
                        }
                    }

                    @Override
                    public void onError(@io.reactivex.annotations.NonNull Throwable e) {
                        Log.e("onError " + e);
                        loadData(search);
                    }
                });
    }

    private void bindView(@NonNull Staff staff) {
        this.mStaff = staff;
        binding.headerToolbarView.setupUI(this, staff);
        binding.detailHeader.setupUI(this, binding.headerToolbarView, staff);
        binding.layoutDetail.setupUI(this, staff);
        binding.appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                float offsetAlpha = (appBarLayout.getY() / binding.appBarLayout.getTotalScrollRange());
                offsetAlpha = (offsetAlpha * -1);
                binding.toolbar.setAlpha(offsetAlpha);
            }
        });

        if (staff.getStaffHistories() != null) {
            binding.adapterView1.setupUI(this, staff.getStaffHistories().getRank(), callback, R.string.detail_label_1, R.string.detail_label_1);
            binding.adapterView2.setupUI(this, staff.getStaffHistories().getPosition(), callback, R.string.detail_label_13, R.string.detail_label_5);
            binding.adapterView3.setupUI(this, staff.getStaffHistories().getDepartment(), callback, R.string.detail_label_change_position, R.string.detail_label_2);
            binding.adapterView4.setupUI(this, staff.getCourseRecord(), callback, R.string.detail_label_skill_header, R.string.detail_label_skill, R.string.detail_label_department);
            binding.adapterView5.setupUI(this, staff.getMedalRecord(), callback, R.string.detail_label_praise, R.string.detail_label_announce_number, R.string.detail_header_2, R.string.detail_label_follow_by);
        }
    }

    private void loadData(SearchStaff searchStaff) {
        Log.i("Loading new detail");
        bindView(Staff.getStaffTmp(searchStaff));
        val task = new TaskStaff(new RequestStaff(searchStaff.getStaffId()));
        binding.detailHeader.showProgress();
        getCompositeDisposable().add(task.start(task.new SimpleObserver() {

            @Override
            public Class<?> clazzResponse() {
                return null;
            }

            @Override
            public void onNext(@io.reactivex.annotations.NonNull retrofit2.Response result) {
                Log.i("LOG >> onNext >> result : " + result);
                if (result.isSuccessful()) {
                    val data  = (ResponseStaff) result.body();
                    val staff = data.getResult();
                    bindView(staff);

                    AppDatabase.getInstance()
                            .detailStaffDao()
                            .insertAll(new DetailStaffEntity(staff))
                            .subscribeOn(Schedulers.io())
                            .subscribe(new CompletableObserver() {
                                @Override
                                public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {
                                }

                                @Override
                                public void onComplete() {
                                }

                                @Override
                                public void onError(@io.reactivex.annotations.NonNull Throwable e) {
                                }
                            });
                }
                binding.detailHeader.hideProgress();
            }

            @Override
            public void onError(Throwable e) {
                Log.e(e);
                binding.detailHeader.hideProgress();
            }
        }));
    }

    private ItemDetailSectionView.Callback callback = new ItemDetailSectionView.Callback() {
        @Override
        public void clickedDownloadPdf(StaffRecord staffRecord) {
            Log.i(staffRecord);
            val pdfFile = staffRecord.getLocalPdfFile();
            Log.i("pdfFile : " + pdfFile);

            if (pdfFile.exists() && pdfFile.length() > 0) {
                ActivityHelper.openPdfActivity(getContext(), pdfFile.getPath());
            }
            else {
                staffRecord.deleteLocalPdf();
                OkHttpUtils.cancelCallWithTag(client, TAG_PDF);
                val request = new Request.Builder()
                        .url(staffRecord.getPdfUrl())
                        .tag(TAG_PDF)
                        .build();
                progress.show();
                client.newCall(request).enqueue(new Callback() {

                    @Override
                    public void onFailure(Call call, IOException e) {
                        Log.e(e);
                        progress.dismiss();
                    }

                    @Override
                    public void onResponse(Call call, Response response) {
                        if (response.isSuccessful()) {
                            try {
                                BufferedSink sink = Okio.buffer(Okio.sink(pdfFile));
                                sink.writeAll(response.body().source());
                                sink.close();
                                ActivityHelper.openPdfActivity(getContext(), pdfFile.getPath());
                            } catch (Exception e) {
                                staffRecord.deleteLocalPdf();
                            }
                        }
                        else {
                            staffRecord.deleteLocalPdf();
                            Snackbar.make(binding.getRoot(), R.string.file_not_found, Snackbar.LENGTH_LONG).show();
                        }
                        progress.dismiss();
                    }
                });
            }
        }
    };

    @Override
    protected void initToolbar() {

    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_detail;
    }

}