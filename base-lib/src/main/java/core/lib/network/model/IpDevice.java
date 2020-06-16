package core.lib.network.model;


import com.google.gson.annotations.SerializedName;

import kmobile.library.base.MyApplication;
import kmobile.library.utils.Utils;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IpDevice extends BaseGson {

    private static final String CACHE_NAME = "IpDeviceCache";

    public void saveToCache() {
        Utils.writeTextToFileInContext(MyApplication.mContext, CACHE_NAME, toJson());
    }

    public static IpDevice getCache() {
        try {
            String json = Utils.readTextFileInContext(MyApplication.mContext, CACHE_NAME);
            return MyApplication.getGson().fromJson(json, IpDevice.class);
        } catch (Exception e) {
        }
        return null;
    }

    @SerializedName("city")
    private String city;

    @SerializedName("country")
    private String country;

    @SerializedName("countryCode")
    private String countryCode;

    @SerializedName("isp")
    private String isp;

    @SerializedName("lat")
    private double lat;

    @SerializedName("lon")
    private double lon;

    @SerializedName("org")
    private String org;

    @SerializedName("query")
    private String query;

    @SerializedName("region")
    private String region;

    @SerializedName("regionName")
    private String regionName;

    @SerializedName("status")
    private String status;

    @SerializedName("timezone")
    private String timezone;

    @SerializedName("zip")
    private String zip;

}
