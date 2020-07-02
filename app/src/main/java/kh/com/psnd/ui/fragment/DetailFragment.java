package kh.com.psnd.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;

import com.google.android.material.appbar.AppBarLayout;
import com.kelin.translucentbar.library.TranslucentBarManager;

import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import core.lib.base.BaseFragment;
import core.lib.dialog.DialogProgress;
import core.lib.utils.Log;
import core.lib.utils.OkHttpUtils;
import kh.com.psnd.R;
import kh.com.psnd.databinding.FragmentDetailBinding;
import kh.com.psnd.helper.ActivityHelper;
import kh.com.psnd.mock.MockData;
import kh.com.psnd.network.model.StaffRecord;
import kh.com.psnd.network.model.Search;
import kh.com.psnd.ui.view.ItemDetailSectionView;
import kh.com.psnd.utils.PdfUtil;
import lombok.val;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.BufferedSink;
import okio.Okio;

public class DetailFragment extends BaseFragment<FragmentDetailBinding> {
    private DialogProgress progress = null;

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
        if (getArguments() != null) {
            val search = (Search) getArguments().getSerializable(Search.EXTRA);
            Log.i(search);
        }
        binding.detailHeader.setupUI(this);
        binding.appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                float offsetAlpha = (appBarLayout.getY() / binding.appBarLayout.getTotalScrollRange());
                offsetAlpha = (offsetAlpha * -1);
                Log.i("offsetAlpha : " + offsetAlpha);
                binding.toolbar.setAlpha(offsetAlpha);
            }
        });

        progress = new DialogProgress(getContext(), false, null);
        binding.adapterView1.setupUI(this, R.string.detail_label_1, callback);
        binding.adapterView2.setupUI(this, R.string.detail_label_2, callback);
        binding.adapterView3.setupUI(this, R.string.detail_label_change_position, callback);
    }

    private ItemDetailSectionView.Callback callback = new ItemDetailSectionView.Callback() {
        @Override
        public void clickedDownloadPdf(StaffRecord staffRecord) {
            Log.i(staffRecord);
            val url      = MockData.getUrlPDF;
            val filename = FilenameUtils.getName(MockData.getUrlPDF);
            val pdfFile  = new File(PdfUtil.getPathPdf() + filename);

            Log.i("pdfFile : " + pdfFile);

            if (pdfFile.exists()) {
                ActivityHelper.openPdfActivity(getContext(), pdfFile.getPath());
            }
            else {
                val TAG    = "DownloadPDF";
                val client = new OkHttpClient();
                val request = new Request.Builder()
                        .url(url)
                        .tag(TAG)
                        .build();

                OkHttpUtils.cancelCallWithTag(client, TAG);

                progress.show();
                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Log.e(e);
                        progress.dismiss();
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        if (response.isSuccessful()) {
                            BufferedSink sink = Okio.buffer(Okio.sink(pdfFile));
                            sink.writeAll(response.body().source());
                            sink.close();
                            ActivityHelper.openPdfActivity(getContext(), pdfFile.getPath());
                        }
                        progress.dismiss();
                    }
                });
            }
        }
    };

    private static File writeResponseBodyToDisk(Context context, String fileName, ResponseBody body) {
        Log.i("writeResponseBodyToDisk");
        try {
            val file = new File(context.getFilesDir().getAbsolutePath() + "/" + fileName);

            InputStream  inputStream  = null;
            OutputStream outputStream = null;
            try {
                byte[] fileReader = new byte[4096];

                inputStream = body.byteStream();
                outputStream = new FileOutputStream(file);

                while (true) {
                    int read = inputStream.read(fileReader);
                    if (read == -1) {
                        break;
                    }
                    outputStream.write(fileReader, 0, read);
                }
                outputStream.flush();
                return file;
            } catch (IOException e) {
                Log.e(e);
                return null;
            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }

                if (outputStream != null) {
                    outputStream.close();
                }
            }
        } catch (IOException e) {
            Log.e(e);
            return null;
        }
    }


    @Override
    protected void initToolbar() {

    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_detail;
    }

}