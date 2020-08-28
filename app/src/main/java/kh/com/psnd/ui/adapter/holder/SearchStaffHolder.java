package kh.com.psnd.ui.adapter.holder;

import java.util.List;

import core.lib.base.BaseViewHolder;
import kh.com.psnd.databinding.ItemSearchStaffBinding;
import kh.com.psnd.network.model.SearchStaff;
import kh.com.psnd.ui.fragment.SearchStaffFragment;

public class SearchStaffHolder extends BaseViewHolder<SearchStaffFragment, ItemSearchStaffBinding, SearchStaff> {

    public SearchStaffHolder(SearchStaffFragment fragment, ItemSearchStaffBinding binding) {
        super(fragment, binding);
    }

    public void bind(List<SearchStaff> items, int position) {
        super.bind(items.get(position));
        binding.imageProfile.setImageURI(item.getPhoto());
        binding.name.setText(item.getFullName());
        binding.position.setText(item.getPosition());
        binding.staffNumber.setText(item.getStaffNumber());
        binding.office.setText(item.getOffice());
        binding.department.setText(item.getDepartment());
        binding.getRoot().setOnClickListener(__ -> fragment.onClickedItem(items, position));
    }
}
