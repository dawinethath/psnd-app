package core.lib.base;

import android.content.Context;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Sonida Kong on 24-Nov-17.
 */

@Setter
@Getter
public abstract class BaseRecyclerView<F extends Fragment, B extends BaseViewHolder, I extends Object> extends RecyclerView.Adapter<B> {
    protected F                fragment;
    protected FragmentActivity activity;
    protected Context          context;

    @Setter
    protected List<I> objects = new ArrayList<>();

    public BaseRecyclerView(F fragment) {
        setFragment(fragment);
        setContext(fragment.getContext());
    }

    public BaseRecyclerView(FragmentActivity activity) {
        setActivity(activity);
        setContext(activity);
        this.context = activity;
    }

    public void addItems(List<I> objects) {
        this.objects.addAll(objects);
    }

    public void addItem(I object) {
        this.objects.add(object);
    }

    public void clear() {
        objects.clear();
    }

    public abstract B onCreateViewHolder(ViewGroup parent, int viewType);

    public abstract void onBindViewHolder(B holder, int position);

    @Override
    public int getItemCount() {
        return objects.size();
    }

    public I getItemPosition(int position) {
        return objects.get(position);
    }

}
