package core.lib.network.model;

import android.content.Context;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Sonida.kong on 1/15/2018.
 */

@Setter
@Getter
public class TargetMessage extends BaseGson {

    @SerializedName("highlights")
    public List<TargetHighlight>   highlights        = null;
    @SerializedName("informationBanner")
    public TargetInformationBanner informationBanner = null;
    @SerializedName("activated")
    public boolean                 activated         = false;

    public void showNotificationSmartNotification(Context context) {
        informationBanner.showNotificationSmartNotification(context);
    }
}
