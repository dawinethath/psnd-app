package core.lib.network.task;


import core.lib.network.base.AuthorizationHandler;
import core.lib.network.base.BaseNetwork;
import core.lib.network.response.BaseResponse;

public abstract class BaseTask<D, R extends retrofit2.Response, T extends BaseResponse> extends BaseNetwork<D, R, T> {

    public BaseTask(AuthorizationHandler authorizationHandler) {
        super(authorizationHandler);
    }

}
