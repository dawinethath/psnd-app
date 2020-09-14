package kh.com.psnd.ui.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import core.lib.base.BaseRecyclerView;
import kh.com.psnd.databinding.ItemSearchUserBinding;
import kh.com.psnd.network.model.UserProfile;
import kh.com.psnd.ui.adapter.holder.SearchUserHolder;
import kh.com.psnd.ui.fragment.user.SearchUserFragment;
import lombok.val;


public class SearchUserAdapter extends BaseRecyclerView<SearchUserFragment, SearchUserHolder, UserProfile> {

    public SearchUserAdapter(@NonNull SearchUserFragment fragment) {
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

    public boolean isAdded(@NonNull UserProfile userProfile) {
        for (val item : objects) {
            if (item.getId().equals(userProfile.getId())) {
                return true;
            }
        }
        return false;
    }
}
