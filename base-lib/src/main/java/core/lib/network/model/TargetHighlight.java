package core.lib.network.model;

import android.text.TextUtils;

import com.google.gson.annotations.SerializedName;

import kmobile.library.utils.ColorUtils;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by kongsonida on 1/16/18.
 */

@Getter
@Setter
public class TargetHighlight extends BaseGson {
    public static final String DEFAULT_PIN_COLOR = "#ff6969";
    @SerializedName("item")
    private             String item              = null;  // myair
    @SerializedName("color")
    private             String color             = null; // #000

    public TargetHighlight() {
    }

    public TargetHighlight(String item, String color) {
        this.item = item;
        this.color = color;
    }

    public String getColor() {
        return ColorUtils.getColor(TextUtils.isEmpty(color) ? DEFAULT_PIN_COLOR : color, DEFAULT_PIN_COLOR);
    }
}
