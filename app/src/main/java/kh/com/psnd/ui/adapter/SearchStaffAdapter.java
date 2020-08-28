package kh.com.psnd.ui.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import core.lib.base.BaseRecyclerView;
import kh.com.psnd.databinding.ItemSearchStaffBinding;
import kh.com.psnd.network.model.SearchStaff;
import kh.com.psnd.ui.adapter.holder.SearchStaffHolder;
import kh.com.psnd.ui.fragment.SearchStaffFragment;


public class SearchStaffAdapter extends BaseRecyclerView<SearchStaffFragment, SearchStaffHolder, SearchStaff> {

    public SearchStaffAdapter(@NonNull SearchStaffFragment fragment) {
        super(fragment);
    }

    @Override
    public SearchStaffHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SearchStaffHolder(fragment, ItemSearchStaffBinding.inflate(LayoutInflater.from(context), parent, false));
    }

    @Override
    public void onBindViewHolder(SearchStaffHolder holder, int position) {
        holder.bind(objects, position);
    }

}
