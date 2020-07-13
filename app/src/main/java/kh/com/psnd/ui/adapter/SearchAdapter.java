package kh.com.psnd.ui.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import core.lib.base.BaseRecyclerView;
import kh.com.psnd.databinding.ItemSearchBinding;
import kh.com.psnd.network.model.Search;
import kh.com.psnd.ui.adapter.holder.SearchHolder;
import kh.com.psnd.ui.fragment.SearchFragment;


public class SearchAdapter extends BaseRecyclerView<SearchFragment, SearchHolder, Search> {

    public SearchAdapter(@NonNull SearchFragment fragment) {
        super(fragment);
    }

    @Override
    public SearchHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SearchHolder(fragment, ItemSearchBinding.inflate(LayoutInflater.from(context), parent, false));
    }

    @Override
    public void onBindViewHolder(SearchHolder holder, int position) {
        holder.bind(objects, position);
    }

}
