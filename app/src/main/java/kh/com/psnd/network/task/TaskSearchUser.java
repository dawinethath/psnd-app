package kh.com.psnd.network.task;


import core.lib.network.base.BaseNetwork;
import core.lib.network.base.HttpHeader;
import core.lib.network.task.BaseTask;
import kh.com.psnd.helper.LoginManager;
import kh.com.psnd.network.adapter.PsndService;
import kh.com.psnd.network.request.RequestSearchUser;
import kh.com.psnd.network.response.ResponseSearchUser;
import lombok.val;
import retrofit2.Response;
import retrofit2.Retrofit;

public class TaskSearchUser extends BaseTask<RequestSearchUser, Response, ResponseSearchUser> {

    public TaskSearchUser(RequestSearchUser requestSearchUser) {
        super(new HttpHeader(BaseNetwork.getToken(), LoginManager.getUserToken()));
        setData(requestSearchUser);
    }

    @Override
    protected Response onExecute(Retrofit retrofit, RequestSearchUser param) throws Exception {
        val service = retrofit.create(PsndService.class);
        return service.searchUser(param).execute();
    }
}
