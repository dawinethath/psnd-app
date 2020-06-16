package kh.com.psnd.ui.adapter.holder;

import androidx.fragment.app.FragmentActivity;

import core.lib.base.BaseFragment;
import core.lib.base.BaseViewHolder;
import core.lib.utils.Log;
import kh.com.psnd.databinding.ItemSearchBinding;
import kh.com.psnd.network.model.Search;

public class SearchHolder extends BaseViewHolder<BaseFragment, ItemSearchBinding, Search> {

    public SearchHolder(FragmentActivity activity, ItemSearchBinding binding) {
        super(activity, binding);
    }

    @Override
    public void bind(Search item) {
        super.bind(item);
        binding.getRoot().setOnClickListener(__ -> {
            Log.i(item);
        });
    }
}
