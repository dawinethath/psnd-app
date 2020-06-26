package core.lib.helper;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import core.lib.utils.Log;
import core.lib.databinding.LayoutRecyclerViewBinding;

public class CoreRecyclerView extends FrameLayout {
    private LayoutRecyclerViewBinding               binding                     = null;
    private EndlessRecyclerViewScrollListenerHelper scrollListener              = null;
    private LayoutManagerHelper                     layoutManagerHelper         = null;
    private GridLayoutManager                       gridLayoutManager           = null;
    private Callback                                callback                    = null;
    private DividerItemDecorationHelper             dividerItemDecorationHelper = null;

    public CoreRecyclerView(@NonNull Context context) {
        super(context);
        init();
    }

    public CoreRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CoreRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        binding = LayoutRecyclerViewBinding.inflate(LayoutInflater.from(getContext()), this, true);

        if (isInEditMode()) {

        }
    }

    public void setupUI(boolean enableSeparator, @Nullable Callback callback) {
        this.callback = callback;
        initUI(enableSeparator, 1);
    }

    public void setupUI(int column, @Nullable Callback callback) {
        this.callback = callback;
        initUI(false, column);
    }

    public void setupUI(boolean enableSeparator) {
        setupUI(enableSeparator, null);
    }

    public void setupUI(Callback callback) {
        setupUI(true, callback);
    }

    private void initUI(boolean enableSeparator, int column) {
        dividerItemDecorationHelper = new DividerItemDecorationHelper(getContext());
        if (column > 1) {
            gridLayoutManager = new GridLayoutManager(getContext(), column);
            binding.recyclerView.setLayoutManager(gridLayoutManager);
        }
        else {
            layoutManagerHelper = new LayoutManagerHelper(getContext());
            binding.recyclerView.setLayoutManager(layoutManagerHelper);
        }
        binding.recyclerView.setItemAnimator(new DefaultItemAnimator());
        if (enableSeparator) {
            binding.recyclerView.addItemDecoration(dividerItemDecorationHelper);
        }
        binding.swipeRefreshLayout.setEnabled(false);
        binding.swipeRefreshLayout.setOnRefreshListener(() -> {
            Log.i("LOG >> onRefresh");
            if (callback != null) {
                callback.onRefresh();
            }
        });
        scrollListener = new EndlessRecyclerViewScrollListenerHelper(layoutManagerHelper != null ? layoutManagerHelper : gridLayoutManager, binding.recyclerView) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                Log.i("LOG >> onLoadMore >> page : " + page + "  >> totalItemCount : " + totalItemsCount);
                if (callback != null) {
                    callback.onLoadMore(page, totalItemsCount, view);
                }
            }
        };
        binding.recyclerView.addOnScrollListener(scrollListener);
    }

    public void nestedScrollingEnabled(boolean enabled) {
        ViewCompat.setNestedScrollingEnabled(binding.recyclerView, enabled);
        binding.recyclerView.setHasFixedSize(enabled);
    }

    public void removeItemDecoration() {
        binding.recyclerView.removeItemDecoration(dividerItemDecorationHelper);
    }

    public void resetStateLoadMore() {
        if (scrollListener != null) {
            scrollListener.resetState();
        }
    }

    public void enableSwipeRefresh(boolean enable) {
        binding.swipeRefreshLayout.setEnabled(enable);
    }

    public void setRefreshing(boolean refreshing) {
        binding.swipeRefreshLayout.setRefreshing(refreshing);
    }

    public void setAdapter(RecyclerView.Adapter adapter) {
        binding.recyclerView.setAdapter(adapter);
    }

    public void scrollToPosition(int position) {
        binding.recyclerView.scrollToPosition(position);
    }

    public void checkList() {
        if (binding.recyclerView.getAdapter().getItemCount() > 0) {
            binding.txtNodData.setVisibility(View.GONE);
        }
        else {
            binding.txtNodData.setVisibility(View.VISIBLE);
        }
    }

    public void showProgressBar() {
        binding.progressBar.setVisibility(View.VISIBLE);
    }

    public void hideProgressBar() {
        binding.progressBar.setVisibility(View.GONE);
    }

    public abstract static class Callback {
        public void onRefresh() {
        }

        public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
        }
    }
}