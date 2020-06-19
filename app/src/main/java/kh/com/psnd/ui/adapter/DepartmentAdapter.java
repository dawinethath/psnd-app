package kh.com.psnd.ui.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import core.lib.base.BaseRecyclerView;
import kh.com.psnd.databinding.ItemDepartmentBinding;
import kh.com.psnd.network.model.Commissariat;
import kh.com.psnd.ui.adapter.holder.DepartmentHolder;

public class DepartmentAdapter extends BaseRecyclerView<Fragment, DepartmentHolder, Commissariat> {

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
