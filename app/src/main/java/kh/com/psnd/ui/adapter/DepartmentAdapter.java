package kh.com.psnd.ui.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import core.lib.base.BaseFragment;
import core.lib.base.BaseRecyclerView;
import kh.com.psnd.databinding.ItemDepartmentBinding;
import kh.com.psnd.databinding.ItemSearchBinding;
import kh.com.psnd.network.model.Department;
import kh.com.psnd.network.model.Search;
import kh.com.psnd.ui.adapter.holder.DepartmentHolder;
import kh.com.psnd.ui.adapter.holder.SearchHolder;

public class DepartmentAdapter extends BaseRecyclerView<Fragment, DepartmentHolder, Department> {

    public DepartmentAdapter(@NonNull Fragment fragment) {
        super(fragment);
    }

    @Override
    public DepartmentHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new DepartmentHolder(activity, ItemDepartmentBinding.inflate(LayoutInflater.from(context), parent, false));
    }

    @Override
    public void onBindViewHolder(DepartmentHolder holder, int position) {
        // holder.bind(getItemPosition(position));
        holder.bind(null);
    }

    @Override
    public int getItemCount() {
        return 20;
    }
}
