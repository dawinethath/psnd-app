package kh.com.psnd.ui.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import core.lib.base.BaseRecyclerView;
import kh.com.psnd.databinding.ItemSearchUserBinding;
import kh.com.psnd.network.model.UserSearch;
import kh.com.psnd.ui.adapter.holder.SearchUserHolder;
import kh.com.psnd.ui.fragment.UserManagementFragment;


public class SearchUserAdapter extends BaseRecyclerView<UserManagementFragment, SearchUserHolder, UserSearch> {

    public SearchUserAdapter(@NonNull UserManagementFragment fragment) {
        super(fragment);
    }

    @Override
    public SearchUserHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SearchUserHolder(fragment, ItemSearchUserBinding.inflate(LayoutInflater.from(context), parent, false));
    }

    @Override
    public void onBindViewHolder(SearchUserHolder holder, int position) {
        holder.bind(objects, position);
    }

}
