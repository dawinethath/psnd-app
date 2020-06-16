package kh.com.psnd.ui.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import core.lib.base.BaseFragment;
import core.lib.base.BaseRecyclerView;
import kh.com.psnd.databinding.ItemSearchBinding;
import kh.com.psnd.network.model.Search;
import kh.com.psnd.ui.adapter.holder.SearchHolder;


public class SearchAdapter extends BaseRecyclerView<BaseFragment, SearchHolder, Search> {

    public SearchAdapter(@NonNull BaseFragment fragment) {
        super(fragment);
    }

    @Override
    public SearchHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SearchHolder(activity, ItemSearchBinding.inflate(LayoutInflater.from(context), parent, false));
    }

    @Override
    public void onBindViewHolder(SearchHolder holder, int position) {
        // holder.bind(getItemPosition(position));
        holder.bind(null);
    }

    @Override
    public int getItemCount() {
        return 20;
    }
}
