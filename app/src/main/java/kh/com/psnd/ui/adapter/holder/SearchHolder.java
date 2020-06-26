package kh.com.psnd.ui.adapter.holder;

import core.lib.base.BaseFragment;
import core.lib.base.BaseViewHolder;
import core.lib.utils.Log;
import kh.com.psnd.databinding.ItemSearchBinding;
import kh.com.psnd.helper.ActivityHelper;
import kh.com.psnd.network.model.Search;
import kh.com.psnd.ui.view.DetailHeaderView;

public class SearchHolder extends BaseViewHolder<BaseFragment, ItemSearchBinding, Search> {

    public SearchHolder(BaseFragment fragment, ItemSearchBinding binding) {
        super(fragment, binding);
    }

    @Override
    public void bind(Search item) {
        super.bind(item);
        binding.image.setImageURI(DetailHeaderView.TEST_IMAGE);
        binding.getRoot().setOnClickListener(__ -> {
            Log.i(item);
            ActivityHelper.openDetailActivity(context, item);
        });
    }
}
