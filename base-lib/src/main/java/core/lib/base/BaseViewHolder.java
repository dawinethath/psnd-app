package core.lib.base;

import android.content.Context;

import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import core.lib.network.model.BaseGson;

/**
 * Created by Sonida Kong on 24-Nov-17.
 */

public abstract class BaseViewHolder<F extends Fragment, T extends ViewDataBinding, I extends BaseGson> extends RecyclerView.ViewHolder implements BindViewHolder<I> {
    protected T       binding  = null;
    protected Context context  = null;
    protected F       fragment = null;
    protected I       item     = null;

    public static final int ROW_FOOTER = 0;
    public static final int ROW_DATA   = 1;

    public BaseViewHolder(final F fragment, final T binding) {
        super(binding.getRoot());
        this.binding = binding;
        this.fragment = fragment;
        this.context = fragment.getContext();
    }

    public BaseViewHolder(final FragmentActivity activity, final T binding) {
        super(binding.getRoot());
        this.binding = binding;
        this.context = activity;
    }

    @Override
    public void bind(final I item) {
        this.item = item;
    }
}