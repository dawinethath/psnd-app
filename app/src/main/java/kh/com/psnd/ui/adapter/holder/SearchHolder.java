package kh.com.psnd.ui.adapter.holder;

import core.lib.base.BaseViewHolder;
import kh.com.psnd.databinding.ItemSearchBinding;
import kh.com.psnd.network.model.Search;
import kh.com.psnd.ui.fragment.SearchFragment;

public class SearchHolder extends BaseViewHolder<SearchFragment, ItemSearchBinding, Search> {

    public SearchHolder(SearchFragment fragment, ItemSearchBinding binding) {
        super(fragment, binding);
    }

    @Override
    public void bind(Search item) {
        super.bind(item);
        binding.image.setImageURI(item.getPhoto());
        binding.name.setText(item.getFullName());
        binding.department.setText(item.getDepartment());
        binding.id.setText(item.getStaffNumber());
        binding.getRoot().setOnClickListener(__ -> fragment.onClickedItem(item));
    }
}
