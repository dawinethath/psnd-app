package kh.com.psnd.network.task;


import core.lib.network.task.BaseTask;
import kh.com.psnd.helper.TaskHelper;
import kh.com.psnd.network.adapter.PsndService;
import kh.com.psnd.network.request.RequestUserProfile;
import kh.com.psnd.network.response.ResponseUserProfile;
import lombok.val;
import retrofit2.Response;
import retrofit2.Retrofit;

public class TaskUserProfile extends BaseTask<RequestUserProfile, Response, ResponseUserProfile> {

    public TaskUserProfile(RequestUserProfile requestUserProfile) {
        super(TaskHelper.getHttpHeader());
        setData(requestUserProfile);
    }

    @Override
    protected Response onExecute(Retrofit retrofit, RequestUserProfile param) throws Exception {
        val service = retrofit.create(PsndService.class);
        return service.userProfile(param).execute();
    }
}
