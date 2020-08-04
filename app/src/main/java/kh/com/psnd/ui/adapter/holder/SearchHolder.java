package kh.com.psnd.ui.adapter.holder;

import java.util.List;

import core.lib.base.BaseViewHolder;
import kh.com.psnd.databinding.ItemSearchBinding;
import kh.com.psnd.network.model.SearchStaff;
import kh.com.psnd.ui.fragment.SearchFragment;

public class SearchHolder extends BaseViewHolder<SearchFragment, ItemSearchBinding, SearchStaff> {

    public SearchHolder(SearchFragment fragment, ItemSearchBinding binding) {
        super(fragment, binding);
    }

    public void bind(List<SearchStaff> items, int position) {
        super.bind(items.get(position));
        binding.image.setImageURI(item.getPhoto());
        binding.name.setText(item.getFullName());
        binding.department.setText(item.getDepartment());
        binding.id.setText(item.getStaffNumber());
        binding.getRoot().setOnClickListener(__ -> fragment.onClickedItem(items, position));
    }
}
