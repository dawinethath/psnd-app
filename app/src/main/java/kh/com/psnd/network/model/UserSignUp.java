package kh.com.psnd.network.model;

import com.google.gson.annotations.SerializedName;

import core.lib.network.model.BaseGson;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class UserSignUp extends BaseGson {
    public static final String EXTRA = "UserSignUp";

    @SerializedName("firstName")
    private String firstName;

    @SerializedName("lastName")
    private String lastName;

    @SerializedName("staffNumber")
    private String staffNumber;

    @SerializedName("phoneNumber")
    private String phoneNumber;

}
