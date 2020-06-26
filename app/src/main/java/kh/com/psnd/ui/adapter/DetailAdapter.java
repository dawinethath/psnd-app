package kh.com.psnd.ui.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import core.lib.base.BaseFragment;
import core.lib.base.BaseRecyclerView;
import kh.com.psnd.databinding.ItemDetailSectionBinding;
import kh.com.psnd.network.model.Search;
import kh.com.psnd.ui.adapter.holder.DetailHolder;


public class DetailAdapter extends BaseRecyclerView<BaseFragment, DetailHolder, Search> {

    public DetailAdapter(@NonNull BaseFragment fragment) {
        super(fragment);
    }

    @Override
    public DetailHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new DetailHolder(fragment, ItemDetailSectionBinding.inflate(LayoutInflater.from(context), parent, false));
    }

    @Override
    public void onBindViewHolder(DetailHolder holder, int position) {
        // holder.bind(getItemPosition(position));
        holder.bind(null);
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
