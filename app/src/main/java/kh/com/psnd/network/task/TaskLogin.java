package kh.com.psnd.network.task;


import core.lib.network.base.BaseNetwork;
import core.lib.network.base.HttpHeader;
import core.lib.network.task.BaseTask;
import kh.com.psnd.network.adapter.PsndService;
import kh.com.psnd.network.request.RequestLogin;
import kh.com.psnd.network.response.ResponseLogin;
import lombok.val;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

public class TaskLogin extends BaseTask<RequestLogin, Response, ResponseLogin> {

    public TaskLogin(RequestLogin requestLogin) {
        super(new HttpHeader(BaseNetwork.getToken()));
        setData(requestLogin);
    }

    @Override
    protected Response onExecute(Retrofit retrofit, RequestLogin param) throws Exception {
        val          service = retrofit.create(PsndService.class);
        Call<ResponseLogin> call    = service.login(param);
        return call.execute();
    }
}
