package kh.com.psnd.network.task;


import core.lib.network.base.BaseNetwork;
import core.lib.network.base.HttpHeader;
import core.lib.network.task.BaseTask;
import kh.com.psnd.network.adapter.PsndService;
import kh.com.psnd.network.request.RequestSearch;
import kh.com.psnd.network.response.ResponseSearch;
import lombok.val;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

public class TaskSearch extends BaseTask<RequestSearch, Response, ResponseSearch> {

    public TaskSearch(RequestSearch requestSearch) {
        super(new HttpHeader(BaseNetwork.getToken()));
        setData(requestSearch);
    }

    @Override
    protected Response onExecute(Retrofit retrofit, RequestSearch param) throws Exception {
        val          service = retrofit.create(PsndService.class);
        Call<ResponseSearch> call    = service.search(param);
        return call.execute();
    }
}
