package kh.com.psnd.eventbus;

import core.lib.network.model.BaseGson;
import kh.com.psnd.network.model.UserProfile;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class CreateAccountSuccessEventBus extends BaseGson {
    private UserProfile userProfile;
}
