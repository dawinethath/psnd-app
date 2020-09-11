package kh.com.psnd.service;

import android.content.Context;
import android.content.Intent;

import androidx.annotation.Nullable;

import com.amazonaws.mobile.client.results.Tokens;
import com.amplifyframework.auth.cognito.AWSCognitoAuthSession;
import com.amplifyframework.core.Amplify;

import org.greenrobot.eventbus.EventBus;

import core.lib.base.BaseIntentService;
import core.lib.utils.Log;
import kh.com.psnd.eventbus.CognitoFetchSessionFailureEventBus;
import kh.com.psnd.helper.LoginManager;
import lombok.val;

public class CognitoService extends BaseIntentService {

    public static void start(Context context) {
        val intent = new Intent(context, CognitoService.class);
        context.startService(intent);
    }

    public CognitoService() {
        super(CognitoService.class.getSimpleName());
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Amplify.Auth.fetchAuthSession(
                result -> {
                    Log.i("FetchAuthSession - result : " + result);
                    val session        = (AWSCognitoAuthSession) result;
                    val userPoolTokens = session.getUserPoolTokens();
                    Log.i("");
                    Log.i("FetchAuthSession - Tokens : " + userPoolTokens.getValue());
                    Log.i("FetchAuthSession - getType : " + userPoolTokens.getType());
                    Log.i("FetchAuthSession - getError : " + userPoolTokens.getError());

                    // AWSMobileClient.getInstance().getTokens()

                    switch (userPoolTokens.getType()) {
                        case SUCCESS:
                            val userProfile = LoginManager.getUserProfile();
                            if (userProfile != null) {
                                val tokens    = userPoolTokens.getValue();
                                val newTokens = new Tokens(tokens.getAccessToken(), tokens.getIdToken(), tokens.getRefreshToken());
                                userProfile.setTokens(newTokens);
                                LoginManager.updateUserProfile(userProfile);
                            }
                            break;
                        case FAILURE:
                            EventBus.getDefault().postSticky(new CognitoFetchSessionFailureEventBus(userPoolTokens));
                            break;
                    }
                },
                error -> {
                    Log.e("AuthQuickStart " + error.toString());
                }
        );
    }
}
