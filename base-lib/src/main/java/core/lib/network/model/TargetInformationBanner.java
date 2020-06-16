package core.lib.network.model;

import android.app.NotificationChannel;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;

import com.facebook.common.executors.UiThreadImmediateExecutorService;
import com.facebook.common.references.CloseableReference;
import com.facebook.datasource.DataSource;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.datasource.BaseBitmapDataSubscriber;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.request.ImageRequest;
import com.google.gson.annotations.SerializedName;

import kmobile.library.R;
import kmobile.library.base.MyApplication;
import kmobile.library.constant.BaseConstTargetMessage;
import kmobile.library.helper.BaseNotificationChannelHelper;
import kmobile.library.utils.ColorUtils;
import kmobile.library.utils.Log;
import lombok.Getter;
import lombok.Setter;
import lombok.val;

/**
 * Created by kongsonida on 1/16/18.
 */

@Getter
@Setter
public class TargetInformationBanner extends BaseGson {

    public static final String EXTRA = "InformationBanner";

    public static final String DEFAULT_ACTION_FONT_COLOR              = "#" + Integer.toHexString(ContextCompat.getColor(MyApplication.mContext, R.color.white));
    public static final String DEFAULT_ACTION_BUTTON_BACKGROUND_COLOR = "#00000000";
    public static final String DEFAULT_BACKGROUND_COLOR               = "#" + Integer.toHexString(ContextCompat.getColor(MyApplication.mContext, R.color.colorPrimaryDark));
    public static final String DEFAULT_FONT_COLOR                     = "#" + Integer.toHexString(ContextCompat.getColor(MyApplication.mContext, R.color.white));

    @SerializedName("fontColor")
    private String fontColor       = null;
    @SerializedName("backgroundColor")
    private String backgroundColor = null;
    @SerializedName("actionFontColor")
    private String actionFontColor = null;
    @SerializedName("actionText")
    private String actionText; // “LEARN MORE”
    @SerializedName("title")
    private String title           = "";
    @SerializedName("message")
    private String message         = "";
    @SerializedName("pictureUrl")
    private String pictureUrl      = "";
    @SerializedName("actionType")
    private String actionType      = "";
    @SerializedName("appCategory")
    private String appCategory     = "";
    @SerializedName("item")
    private String item            = "";

    public String getTitle() {
        return title;
    }

    public String getActionText() {
        if (TextUtils.isEmpty(actionText)) {
            return "";
        }
        return actionText;
    }

    public String getFontColor() {
        return ColorUtils.getColor(fontColor, DEFAULT_FONT_COLOR);
    }


    public String getBackgroundColor() {
        return ColorUtils.getColor(TextUtils.isEmpty(backgroundColor) ? DEFAULT_BACKGROUND_COLOR : backgroundColor, DEFAULT_BACKGROUND_COLOR);
    }

    public String getActionTextColor() {
        return ColorUtils.getColor(TextUtils.isEmpty(actionFontColor) ? DEFAULT_ACTION_FONT_COLOR : actionFontColor, DEFAULT_ACTION_FONT_COLOR);
    }

    public void showNotificationSmartNotification(Context context) {
        val builder    = getBuilder(context);
        val notId      = (int) System.currentTimeMillis();
        val dataSource = getPictureDataSource();
        if (dataSource != null) {
            dataSource.subscribe(
                    new BaseBitmapDataSubscriber() {

                        @Override
                        protected void onNewResultImpl(Bitmap bitmap) {
                            builder.setLargeIcon(bitmap);
                            builder.setStyle(new NotificationCompat.BigPictureStyle().bigPicture(bitmap).bigLargeIcon(null));
                            NotificationManagerCompat.from(context).notify(notId, builder.build());
                        }

                        @Override
                        protected void onFailureImpl(DataSource<CloseableReference<CloseableImage>> dataSource) {
                            Log.i("LOG >> onFailureImpl >> " + dataSource);
                            NotificationManagerCompat.from(context).notify(notId, builder.build());
                        }
                    },
                    UiThreadImmediateExecutorService.getInstance());
        }
        else {
            NotificationManagerCompat.from(context).notify(notId, builder.build());
        }
    }

    private PendingIntent getPendingIntent(Context context) {
        int requestCode = (int) System.currentTimeMillis();
        val intent      = context.getPackageManager().getLaunchIntentForPackage(context.getPackageName());
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra(EXTRA, this);
        return PendingIntent.getActivity(context, requestCode, intent, PendingIntent.FLAG_UPDATE_CURRENT);
    }

    private NotificationCompat.Builder getBuilder(Context context) {
        NotificationCompat.Builder builder             = null;
        NotificationChannel        notificationChannel = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            notificationChannel = BaseNotificationChannelHelper.getNotificationChannelSmart(context);
            builder = new NotificationCompat.Builder(context, notificationChannel.getId());
        }
        else {
            builder = new NotificationCompat.Builder(context, null);
        }
        builder.setGroup(BaseNotificationChannelHelper.CHANNEL_SMART_NAME);
        builder.setContentIntent(getPendingIntent(context));
        builder.setAutoCancel(true);
        builder.setOngoing(false);
        builder.setShowWhen(true);
        builder.setColor(ContextCompat.getColor(context, R.color.yellowLight));
        builder.setContentTitle(title);
        builder.setContentText(message);


        builder.setSmallIcon(R.mipmap.ic_notification);
        builder.setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher_round));
        switch (getActionType()) {
            case BaseConstTargetMessage.INFORMATION_BANNER_TYPE_URL:
                if (!TextUtils.isEmpty(actionText)) {
                    builder.addAction(R.drawable.ic_touch_app_black_24dp, actionText, getPendingIntent(context));
                }
                break;
        }
        return builder;
    }

    private DataSource<CloseableReference<CloseableImage>> getPictureDataSource() {
        if (!TextUtils.isEmpty(pictureUrl)) {
            val imageRequest  = ImageRequest.fromUri(pictureUrl);
            val imagePipeline = Fresco.getImagePipeline();
            return imagePipeline.fetchDecodedImage(imageRequest, null);
        }
        return null;
    }

}
