package kh.com.psnd.helper;

import android.text.TextUtils;

import androidx.annotation.NonNull;

import com.amplifyframework.auth.AuthUserAttribute;
import com.amplifyframework.auth.AuthUserAttributeKey;
import com.amplifyframework.auth.options.AuthSignUpOptions;

import java.util.ArrayList;

import kh.com.psnd.network.model.SearchStaff;
import kh.com.psnd.network.model.Staff;
import lombok.val;

public class CognitoHelper {

    public static AuthSignUpOptions getAuthSignUpOptions(SearchStaff staff) {
        if (staff == null) {
            val adminProfile = LoginManager.getUserProfile();
            val signUpBy     = adminProfile.getUsername();
            return getAuthSignUpOptions(null, null, null, null, signUpBy, null);
        }
        return getAuthSignUpOptions(staff.getFullName(), staff.getPhoto(), null, staff.getStaffId() + "", null, null);
    }

    public static AuthSignUpOptions getAuthSignUpOptions(@NonNull Staff staff) {
        return getAuthSignUpOptions(staff.getFullName(), staff.getPhoto(), staff.getAddress(), staff.getStaffId() + "", null, null);
    }

    public static AuthSignUpOptions getAuthSignUpOptionsByPhone(@NonNull Staff staff, @NonNull String signUpBy, @NonNull String phoneNumber) {
        return getAuthSignUpOptions(staff.getFullName(), staff.getPhoto(), staff.getAddress(), staff.getStaffId() + "", signUpBy, phoneNumber);
    }

    private static AuthSignUpOptions getAuthSignUpOptions(String fullName, String photo, String address, String staffId, String signUpBy, String phoneNumber) {
        val userAttributes = new ArrayList<AuthUserAttribute>();
        if (TextUtils.isEmpty(signUpBy)) {
            val adminProfile = LoginManager.getUserProfile();
            signUpBy = adminProfile.getUsername();
        }
        userAttributes.add(new AuthUserAttribute(AuthUserAttributeKey.custom("custom:signup_by"), signUpBy));

        if (!TextUtils.isEmpty(fullName)) {
            userAttributes.add(new AuthUserAttribute(AuthUserAttributeKey.name(), fullName));
        }
        if (!TextUtils.isEmpty(photo)) {
            userAttributes.add(new AuthUserAttribute(AuthUserAttributeKey.picture(), photo));
        }
        if (!TextUtils.isEmpty(address)) {
            userAttributes.add(new AuthUserAttribute(AuthUserAttributeKey.address(), address));
        }
        if (!TextUtils.isEmpty(staffId)) {
            userAttributes.add(new AuthUserAttribute(AuthUserAttributeKey.custom("custom:staff_id"), staffId));
        }
        if (!TextUtils.isEmpty(phoneNumber)) {
            userAttributes.add(new AuthUserAttribute(AuthUserAttributeKey.phoneNumber(), phoneNumber));
        }

        val signUpOption = AuthSignUpOptions.builder()
                .userAttributes(userAttributes)
                .build();
        return signUpOption;
    }
}
