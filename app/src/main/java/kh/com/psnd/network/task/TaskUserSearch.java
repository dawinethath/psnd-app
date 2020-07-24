package kh.com.psnd.network.task;


import core.lib.network.base.BaseNetwork;
import core.lib.network.base.HttpHeader;
import core.lib.network.task.BaseTask;
import kh.com.psnd.network.adapter.PsndService;
import kh.com.psnd.network.request.RequestUserSearch;
import kh.com.psnd.network.response.ResponseUserSearch;
import lombok.val;
import retrofit2.Response;
import retrofit2.Retrofit;

public class TaskUserSearch extends BaseTask<RequestUserSearch, Response, ResponseUserSearch> {

    public TaskUserSearch(RequestUserSearch requestUserSearch) {
        super(new HttpHeader(BaseNetwork.getToken()));
        setData(requestUserSearch);
    }

    @Override
    protected Response onExecute(Retrofit retrofit, RequestUserSearch param) throws Exception {
        val service = retrofit.create(PsndService.class);
        return service.userSearch(param).execute();
    }
}
