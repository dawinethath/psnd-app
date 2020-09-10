package kh.com.psnd.network.task;


import core.lib.network.task.BaseTask;
import kh.com.psnd.helper.TaskHelper;
import kh.com.psnd.network.adapter.PsndService;
import kh.com.psnd.network.request.RequestSignUpVerify;
import kh.com.psnd.network.response.ResponseSignUpVerify;
import lombok.val;
import retrofit2.Response;
import retrofit2.Retrofit;

public class TaskSignUpVerify extends BaseTask<RequestSignUpVerify, Response, ResponseSignUpVerify> {

    public TaskSignUpVerify(RequestSignUpVerify requestSignUpVerify) {
        super(TaskHelper.getHttpHeader());
        setData(requestSignUpVerify);
    }

    @Override
    protected Response onExecute(Retrofit retrofit, RequestSignUpVerify param) throws Exception {
        val service = retrofit.create(PsndService.class);
        return service.signUpVerify(param).execute();
    }
}
