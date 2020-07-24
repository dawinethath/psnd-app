package kh.com.psnd.ui.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import core.lib.base.BaseRecyclerView;
import kh.com.psnd.databinding.ItemUserSearchBinding;
import kh.com.psnd.network.model.UserSearch;
import kh.com.psnd.ui.adapter.holder.UserSearchHolder;
import kh.com.psnd.ui.fragment.UserManagementFragment;


public class UserSearchAdapter extends BaseRecyclerView<UserManagementFragment, UserSearchHolder, UserSearch> {

    public UserSearchAdapter(@NonNull UserManagementFragment fragment) {
        super(fragment);
    }

    @Override
    public UserSearchHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new UserSearchHolder(fragment, ItemUserSearchBinding.inflate(LayoutInflater.from(context), parent, false));
    }

    @Override
    public void onBindViewHolder(UserSearchHolder holder, int position) {
        holder.bind(objects, position);
    }

}
