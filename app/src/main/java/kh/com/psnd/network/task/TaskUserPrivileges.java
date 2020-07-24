package kh.com.psnd.network.task;


import core.lib.network.base.BaseNetwork;
import core.lib.network.base.HttpHeader;
import core.lib.network.task.BaseTask;
import kh.com.psnd.network.adapter.PsndService;
import kh.com.psnd.network.request.RequestUserPrivilege;
import kh.com.psnd.network.response.ResponseUserPrivileges;
import lombok.val;
import retrofit2.Response;
import retrofit2.Retrofit;

public class TaskUserPrivileges extends BaseTask<RequestUserPrivilege, Response, ResponseUserPrivileges> {

    public TaskUserPrivileges() {
        super(new HttpHeader(BaseNetwork.getToken()));
        setData(new RequestUserPrivilege());
    }

    @Override
    protected Response onExecute(Retrofit retrofit, RequestUserPrivilege param) throws Exception {
        val service = retrofit.create(PsndService.class);
        return service.userPrivileges(param).execute();
    }
}
