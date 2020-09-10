package kh.com.psnd.network.task;


import core.lib.network.task.BaseTask;
import kh.com.psnd.helper.TaskHelper;
import kh.com.psnd.network.adapter.PsndService;
import kh.com.psnd.network.request.RequestUserCheck;
import kh.com.psnd.network.response.ResponseUserCheck;
import lombok.val;
import retrofit2.Response;
import retrofit2.Retrofit;

public class TaskUserCheck extends BaseTask<RequestUserCheck, Response, ResponseUserCheck> {

    public TaskUserCheck(RequestUserCheck requestUserCheck) {
        super(TaskHelper.getHttpHeader());
        setData(requestUserCheck);
    }

    @Override
    protected Response onExecute(Retrofit retrofit, RequestUserCheck param) throws Exception {
        val service = retrofit.create(PsndService.class);
        return service.userCheck(param).execute();
    }
}
