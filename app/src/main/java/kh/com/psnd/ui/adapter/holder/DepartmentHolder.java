package kh.com.psnd.ui.adapter.holder;

import androidx.fragment.app.FragmentActivity;

import core.lib.base.BaseFragment;
import core.lib.base.BaseViewHolder;
import core.lib.utils.Log;
import kh.com.psnd.databinding.ItemDepartmentBinding;
import kh.com.psnd.network.model.Commissariat;

public class DepartmentHolder extends BaseViewHolder<BaseFragment, ItemDepartmentBinding, Commissariat> {

    public DepartmentHolder(FragmentActivity activity, ItemDepartmentBinding binding) {
        super(activity, binding);
    }

    @Override
    public void bind(Commissariat item) {
        super.bind(item);
        binding.getRoot().setOnClickListener(__ -> {
            Log.i(item);
        });
    }
}
