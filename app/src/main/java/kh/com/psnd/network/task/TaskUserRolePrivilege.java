package kh.com.psnd.network.task;


import core.lib.network.base.BaseNetwork;
import core.lib.network.base.HttpHeader;
import core.lib.network.task.BaseTask;
import kh.com.psnd.helper.LoginManager;
import kh.com.psnd.network.adapter.PsndService;
import kh.com.psnd.network.request.RequestUserRolePrivilege;
import kh.com.psnd.network.response.ResponseUserRolePrivilege;
import lombok.val;
import retrofit2.Response;
import retrofit2.Retrofit;

public class TaskUserRolePrivilege extends BaseTask<RequestUserRolePrivilege, Response, ResponseUserRolePrivilege> {

    public TaskUserRolePrivilege() {
        super(new HttpHeader(BaseNetwork.getToken(), LoginManager.getUserToken()));
        setData(new RequestUserRolePrivilege());
    }

    @Override
    protected Response onExecute(Retrofit retrofit, RequestUserRolePrivilege param) throws Exception {
        val service = retrofit.create(PsndService.class);
        return service.userRolePrivilege(param).execute();
    }
}
