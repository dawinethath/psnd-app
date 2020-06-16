package core.lib.helper;

import android.content.Context;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;

import kmobile.logger.R;

public class DividerItemDecorationHelper extends DividerItemDecoration {
    public DividerItemDecorationHelper(Context context) {
        super(context, DividerItemDecoration.VERTICAL);
        setDrawable(ContextCompat.getDrawable(context, R.drawable.recycler_divider));
    }
}
