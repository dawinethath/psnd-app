package kh.com.psnd.network.task;


import core.lib.network.base.BaseNetwork;
import core.lib.network.base.HttpHeader;
import core.lib.network.request.BaseParam;
import core.lib.network.task.BaseTask;
import kh.com.psnd.network.adapter.PsndService;
import kh.com.psnd.network.response.ResponseGeneralComm;
import lombok.val;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

public class TaskGeneralComm extends BaseTask<BaseParam, Response, ResponseGeneralComm> {

    public TaskGeneralComm() {
        super(new HttpHeader(BaseNetwork.getToken()));
        setData(new BaseParam());
    }

    @Override
    protected Response onExecute(Retrofit retrofit, BaseParam param) throws Exception {
        val                       service = retrofit.create(PsndService.class);
        Call<ResponseGeneralComm> call    = service.generalComm(param);
        return call.execute();
    }
}
