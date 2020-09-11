package kh.com.psnd.helper;

import android.text.TextUtils;

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
            return getAuthSignUpOptions(null, null, null, null);
        }
        return getAuthSignUpOptions(staff.getFullName(), staff.getPhoto(), "", staff.getStaffId() + "");
    }

    public static AuthSignUpOptions getAuthSignUpOptions(Staff staff) {
        return getAuthSignUpOptions(staff.getFullName(), staff.getPhoto(), staff.getAddress(), staff.getStaffId() + "");
    }

    private static AuthSignUpOptions getAuthSignUpOptions(String fullName, String photo, String address, String staffId) {
        val userAttributes = new ArrayList<AuthUserAttribute>();
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

        val signUpOption = AuthSignUpOptions.builder()
                .userAttributes(userAttributes)
                .build();
        return signUpOption;
    }
}
