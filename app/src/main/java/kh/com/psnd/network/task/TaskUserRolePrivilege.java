package kh.com.psnd.network.task;


import core.lib.network.task.BaseTask;
import kh.com.psnd.helper.TaskHelper;
import kh.com.psnd.network.adapter.PsndService;
import kh.com.psnd.network.request.RequestUserRolePrivilege;
import kh.com.psnd.network.response.ResponseUserRolePrivilege;
import lombok.val;
import retrofit2.Response;
import retrofit2.Retrofit;

public class TaskUserRolePrivilege extends BaseTask<RequestUserRolePrivilege, Response, ResponseUserRolePrivilege> {

    public TaskUserRolePrivilege() {
        super(TaskHelper.getHttpHeader());
        setData(new RequestUserRolePrivilege());
    }

    @Override
    protected Response onExecute(Retrofit retrofit, RequestUserRolePrivilege param) throws Exception {
        val service = retrofit.create(PsndService.class);
        return service.userRolePrivilege(param).execute();
    }
}
