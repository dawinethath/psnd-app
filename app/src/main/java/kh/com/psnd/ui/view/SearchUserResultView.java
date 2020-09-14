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
import kh.com.psnd.databinding.LayoutSearchResultBinding;
import kh.com.psnd.databinding.LayoutSearchUserBarBinding;
import kh.com.psnd.network.model.UserFilter;
import kh.com.psnd.network.model.UserProfile;
import kh.com.psnd.network.request.RequestSearchUser;
import kh.com.psnd.network.response.ResponseSearchUser;
import kh.com.psnd.network.task.TaskSearchUser;
import kh.com.psnd.ui.adapter.SearchUserAdapter;
import kh.com.psnd.ui.fragment.user.SearchUserFragment;
import lombok.val;
import retrofit2.Response;

public class SearchUserResultView extends FrameLayout {

    private LayoutSearchResultBinding  binding          = null;
    private SearchUserAdapter          adapter          = null;
    private BaseFragment               fragment         = null;
    private RequestSearchUser          requestSearch    = null;
    private LayoutSearchUserBarBinding searchBarBinding = null;

    public SearchUserResultView(@NonNull Context context) {
        super(context);
        init();
    }

    public SearchUserResultView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SearchUserResultView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        binding = LayoutSearchResultBinding.inflate(LayoutInflater.from(getContext()), this, true);
        if (isInEditMode()) {

        }
    }

    public void setupUI(@NonNull SearchUserFragment fragment, @NonNull SearchUserBarView searchBarView) {
        this.fragment = fragment;
        this.searchBarBinding = searchBarView.getBinding();
        adapter = new SearchUserAdapter(fragment);
        binding.recyclerView.setupUI(false, new CoreRecyclerView.Callback() {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                Log.i("Page : " + page + "    totalItemsCount : " + totalItemsCount + "   " + requestSearch);
                if (requestSearch != null) {
                    val nextPage = requestSearch.getPage() + 1;
                    loadMore(nextPage);
                }
            }

            @Override
            public void onRefresh() {
                loadMore(1);
            }
        });
        binding.recyclerView.enableSwipeRefresh(true);
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

    public void addNewUser(@NonNull UserProfile userProfile) {
        adapter.addItemFirst(userProfile);
        adapter.notifyDataSetChanged();
    }

    public void cleanList() {
        adapter.clear();
        adapter.notifyDataSetChanged();
        searchBarBinding.progressBar.setVisibility(GONE);
    }

    public void doSearch(UserFilter filter) {
        val page = 1;
        requestSearch = new RequestSearchUser(null, filter, page);
        loadMore(page);
    }

    public void doSearch(String search) {
        val page = 1;
        requestSearch = new RequestSearchUser(search, null, page);
        loadMore(page);
    }

    private void loadMore(int page) {
        Log.i("Sent request to server...");
        requestSearch.setPage(page);

        val compositeDisposable = fragment.getCompositeDisposable();
        compositeDisposable.clear();
        val task = new TaskSearchUser(requestSearch);
        searchBarBinding.progressBar.setVisibility(VISIBLE);
        binding.recyclerView.setRefreshing(false);
        compositeDisposable.add(task.start(task.new SimpleObserver() {

            @Override
            public Class<?> clazzResponse() {
                return null;
            }

            @Override
            public void onNext(@io.reactivex.annotations.NonNull Response result) {
                Log.i("LOG >> onNext >> result : " + result);
                if (result.isSuccessful()) {
                    val data = (ResponseSearchUser) result.body();
                    Log.i(data);
                    if (data != null) {
                        if (requestSearch.getPage() == 1) {
                            adapter.clear();
                        }
                        if (data.getResult() != null) {
                            Log.i("data.getResult() : " + data.getResult().size());
                            for (val item : data.getResult()) {
                                if (!adapter.isAdded(item)) {
                                    adapter.addItem(item);
                                }
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