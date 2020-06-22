package kh.com.psnd.ui.adapter.holder;

import core.lib.base.BaseFragment;
import core.lib.base.BaseViewHolder;
import core.lib.utils.Log;
import kh.com.psnd.databinding.ItemDetailBinding;
import kh.com.psnd.network.model.Search;

public class DetailHolder extends BaseViewHolder<BaseFragment, ItemDetailBinding, Search> {

    public DetailHolder(BaseFragment fragment, ItemDetailBinding binding) {
        super(fragment, binding);
    }

    @Override
    public void bind(Search item) {
        super.bind(item);

        Log.i(item);
    }
}
