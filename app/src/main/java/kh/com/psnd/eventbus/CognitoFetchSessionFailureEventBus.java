package kh.com.psnd.eventbus;

import com.amplifyframework.auth.cognito.AWSCognitoUserPoolTokens;
import com.amplifyframework.auth.result.AuthSessionResult;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CognitoFetchSessionFailureEventBus {
    AuthSessionResult<AWSCognitoUserPoolTokens> userPoolTokens;
}
