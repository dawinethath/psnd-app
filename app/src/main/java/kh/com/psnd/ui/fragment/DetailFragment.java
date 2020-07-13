package kh.com.psnd.ui.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.snackbar.Snackbar;
import com.kelin.translucentbar.library.TranslucentBarManager;

import java.io.IOException;

import core.lib.base.BaseFragment;
import core.lib.dialog.DialogProgress;
import core.lib.utils.Log;
import core.lib.utils.OkHttpUtils;
import kh.com.psnd.R;
import kh.com.psnd.databinding.FragmentDetailBinding;
import kh.com.psnd.helper.ActivityHelper;
import kh.com.psnd.network.model.Search;
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

    public static DetailFragment newInstance(@NonNull Search search) {
        val fragment = new DetailFragment();
        val bundle   = new Bundle();
        bundle.putSerializable(Search.EXTRA, search);
        fragment.setArguments(bundle);
        return fragment;
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
        loadData();
    }

    private void bindView(@NonNull Staff staff) {
        binding.headerToolbarView.setupUI(this, staff);
        binding.detailHeader.setupUI(this, staff);
        binding.layoutDetail.setupUI(this, staff);
        binding.appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                float offsetAlpha = (appBarLayout.getY() / binding.appBarLayout.getTotalScrollRange());
                offsetAlpha = (offsetAlpha * -1);
                Log.i("offsetAlpha : " + offsetAlpha);
                binding.toolbar.setAlpha(offsetAlpha);
            }
        });

        if (staff.getStaffHistories() != null) {
            binding.adapterView1.setupUI(this, staff.getStaffHistories().getRank(), R.string.detail_label_1, callback);
            binding.adapterView2.setupUI(this, staff.getStaffHistories().getPosition(), R.string.detail_label_2, callback);
            binding.adapterView3.setupUI(this, staff.getStaffHistories().getDepartment(), R.string.detail_label_change_position, callback);
        }
    }

    private void loadData() {
        val search = (Search) getArguments().getSerializable(Search.EXTRA);
        bindView(Staff.getStaffTmp(search));

        val task = new TaskStaff(new RequestStaff(search.getStaffId()));
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
                    val data = (ResponseStaff) result.body();
                    Log.i(data);
                    bindView(data.getResult());
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