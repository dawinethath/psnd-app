package kh.com.psnd.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import core.lib.base.BaseFragment;
import core.lib.helper.CoreRecyclerView;
import core.lib.utils.Log;
import kh.com.psnd.databinding.LayoutSearchResultBinding;
import kh.com.psnd.ui.adapter.SearchAdapter;

public class SearchResultView extends FrameLayout {

    private LayoutSearchResultBinding binding = null;
    private SearchAdapter adapter = null;

    public SearchResultView(@NonNull Context context) {
        super(context);
        init();
    }

    public SearchResultView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SearchResultView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        binding = LayoutSearchResultBinding.inflate(LayoutInflater.from(getContext()), this, true);

        if (isInEditMode()) {

        }
    }

    public void setupUI(@NonNull BaseFragment fragment) {
        Log.i(fragment);
        adapter = new SearchAdapter(fragment);
        binding.recyclerView.setupUI(new CoreRecyclerView.Callback() {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                Log.i("Page : " + page + "    totalItemsCount : " + totalItemsCount);
            }
        });
        binding.recyclerView.setAdapter(adapter);
    }

}