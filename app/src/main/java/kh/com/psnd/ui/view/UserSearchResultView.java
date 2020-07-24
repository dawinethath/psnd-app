package kh.com.psnd.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;

import core.lib.base.BaseFragment;
import core.lib.helper.CoreRecyclerView;
import core.lib.utils.Log;
import kh.com.psnd.R;
import kh.com.psnd.databinding.LayoutUserSearchBarBinding;
import kh.com.psnd.databinding.LayoutUserSearchResultBinding;
import kh.com.psnd.network.request.RequestSearch;
import kh.com.psnd.network.request.RequestUserSearch;
import kh.com.psnd.network.response.ResponseUserSearch;
import kh.com.psnd.network.task.TaskUserSearch;
import kh.com.psnd.ui.adapter.UserSearchAdapter;
import kh.com.psnd.ui.fragment.UserManagementFragment;
import lombok.val;
import retrofit2.Response;

public class UserSearchResultView extends FrameLayout {

    private LayoutUserSearchResultBinding binding          = null;
    private UserSearchAdapter             adapter          = null;
    private BaseFragment                  fragment         = null;
    private RequestUserSearch             requestSearch    = null;
    private LayoutUserSearchBarBinding    searchBarBinding = null;

    public UserSearchResultView(@NonNull Context context) {
        super(context);
        init();
    }

    public UserSearchResultView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public UserSearchResultView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        binding = LayoutUserSearchResultBinding.inflate(LayoutInflater.from(getContext()), this, true);
        if (isInEditMode()) {

        }
    }

    public void setupUI(@NonNull UserManagementFragment fragment, @NonNull UserSearchBarView searchBarView) {
        this.fragment = fragment;
        this.searchBarBinding = searchBarView.getBinding();
        adapter = new UserSearchAdapter(fragment);
        binding.recyclerView.setupUI(false, new CoreRecyclerView.Callback() {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                Log.i("Page : " + page + "    totalItemsCount : " + totalItemsCount + "   " + requestSearch);
                if (requestSearch != null) {
                    val nextPage = requestSearch.getPage() + 1;
                    loadMore(nextPage);
                }
            }
        });
        binding.recyclerView.setAdapter(adapter);
        binding.recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (binding.recyclerView.getLayoutManagerHelper().findFirstCompletelyVisibleItemPosition() == 0) {
                    Log.i("findFirstCompletelyVisibleItemPosition");
                    ViewCompat.setElevation(searchBarView, 0);
                }
                else {
                    val elevation = getResources().getDimension(R.dimen.cardElevation);
                    ViewCompat.setElevation(searchBarView, elevation);
                }
            }
        });
    }

    public void cleanList() {
        adapter.clear();
        adapter.notifyDataSetChanged();
        searchBarBinding.progressBar.setVisibility(GONE);
    }

    public void doSearch(RequestSearch.Filter filter) {
        val page = 1;
        requestSearch = new RequestUserSearch(null, page);
        loadMore(page);
    }

    public void doSearch(String search) {
        val page = 1;
        requestSearch = new RequestUserSearch(search, page);
        loadMore(page);
    }

    private void loadMore(int page) {
        Log.i("Sent request to server...");
        requestSearch.setPage(page);

        val compositeDisposable = fragment.getCompositeDisposable();
        compositeDisposable.clear();
        val task = new TaskUserSearch(requestSearch);
        searchBarBinding.progressBar.setVisibility(VISIBLE);
        compositeDisposable.add(task.start(task.new SimpleObserver() {

            @Override
            public Class<?> clazzResponse() {
                return null;
            }

            @Override
            public void onNext(@io.reactivex.annotations.NonNull Response result) {
                Log.i("LOG >> onNext >> result : " + result);
                if (result.isSuccessful()) {
                    val data = (ResponseUserSearch) result.body();
                    Log.i(data);
                    if (data != null) {
                        if (requestSearch.getPage() == 1) {
                            adapter.clear();
                        }
                        if (data.getResult() != null) {
                            Log.i("data.getResult() : " + data.getResult().size());
                            for (val item : data.getResult()) {
                                adapter.addItem(item);
                            }
                        }
                        adapter.notifyDataSetChanged();
                    }
                    binding.recyclerView.checkList();
                    searchBarBinding.progressBar.setVisibility(GONE);
                }
            }

            @Override
            public void onError(Throwable e) {
                Log.e(e);
                searchBarBinding.progressBar.setVisibility(GONE);
            }
        }));
    }

}