package kh.com.psnd.network.task;


import core.lib.network.base.BaseNetwork;
import core.lib.network.base.HttpHeader;
import core.lib.network.task.BaseTask;
import kh.com.psnd.helper.LoginManager;
import kh.com.psnd.network.adapter.PsndService;
import kh.com.psnd.network.request.RequestUserProfile;
import kh.com.psnd.network.response.ResponseUserProfile;
import lombok.val;
import retrofit2.Response;
import retrofit2.Retrofit;

public class TaskUserProfile extends BaseTask<RequestUserProfile, Response, ResponseUserProfile> {

    public TaskUserProfile(RequestUserProfile requestUserProfile) {
        super(new HttpHeader(BaseNetwork.getToken(), LoginManager.getUserToken()));
        setData(requestUserProfile);
    }

    @Override
    protected Response onExecute(Retrofit retrofit, RequestUserProfile param) throws Exception {
        val service = retrofit.create(PsndService.class);
        return service.userProfile(param).execute();
    }
}
