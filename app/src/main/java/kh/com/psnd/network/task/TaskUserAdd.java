package kh.com.psnd.network.task;


import core.lib.network.task.BaseTask;
import kh.com.psnd.helper.TaskHelper;
import kh.com.psnd.network.adapter.PsndService;
import kh.com.psnd.network.request.RequestUserAdd;
import kh.com.psnd.network.response.ResponseUserAdd;
import lombok.val;
import retrofit2.Response;
import retrofit2.Retrofit;

public class TaskUserAdd extends BaseTask<RequestUserAdd, Response, ResponseUserAdd> {

    public TaskUserAdd(RequestUserAdd requestUserAdd) {
        super(TaskHelper.getHttpHeader());
        setData(requestUserAdd);
    }

    @Override
    protected Response onExecute(Retrofit retrofit, RequestUserAdd param) throws Exception {
        val service = retrofit.create(PsndService.class);
        return service.userAdd(param).execute();
    }
}
