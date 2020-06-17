package kh.com.psnd.network.task;


import core.lib.network.base.BaseNetwork;
import core.lib.network.base.HttpHeader;
import core.lib.network.task.BaseTask;
import core.lib.utils.Log;
import kh.com.psnd.network.adapter.PsndService;
import kh.com.psnd.network.request.RequestLogin;
import kh.com.psnd.network.request.RequestSearch;
import kh.com.psnd.network.response.ResponseLogin;
import kh.com.psnd.network.response.ResponseSearch;
import kmobile.logger.BuildConfig;
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
        val service = retrofit.create(PsndService.class);
        if (BuildConfig.DEBUG_MODE) {
            Log.i("LOG >> " + param.toPrettyJson());
        }
        Call<String> call = service.search(null);
        return call.execute();
    }
}
