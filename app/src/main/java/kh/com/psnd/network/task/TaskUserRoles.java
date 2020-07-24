package kh.com.psnd.network.task;


import core.lib.network.base.BaseNetwork;
import core.lib.network.base.HttpHeader;
import core.lib.network.task.BaseTask;
import kh.com.psnd.network.adapter.PsndService;
import kh.com.psnd.network.request.RequestUserRole;
import kh.com.psnd.network.response.ResponseUserRoles;
import lombok.val;
import retrofit2.Response;
import retrofit2.Retrofit;

public class TaskUserRoles extends BaseTask<RequestUserRole, Response, ResponseUserRoles> {

    public TaskUserRoles() {
        super(new HttpHeader(BaseNetwork.getToken()));
        setData(new RequestUserRole());
    }

    @Override
    protected Response onExecute(Retrofit retrofit, RequestUserRole param) throws Exception {
        val service = retrofit.create(PsndService.class);
        return service.userRoles(param).execute();
    }
}
