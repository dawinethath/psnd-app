package kh.com.psnd.network.task;


import core.lib.network.task.BaseTask;
import kh.com.psnd.helper.TaskHelper;
import kh.com.psnd.network.adapter.PsndService;
import kh.com.psnd.network.request.RequestUserDisable;
import kh.com.psnd.network.response.ResponseUserDisable;
import lombok.val;
import retrofit2.Response;
import retrofit2.Retrofit;

public class TaskUserDisable extends BaseTask<RequestUserDisable, Response, ResponseUserDisable> {

    public TaskUserDisable(RequestUserDisable requestUserDisable) {
        super(TaskHelper.getHttpHeader());
        setData(requestUserDisable);
    }

    @Override
    protected Response onExecute(Retrofit retrofit, RequestUserDisable param) throws Exception {
        val service = retrofit.create(PsndService.class);
        return service.userDisable(param).execute();
    }
}
