package kh.com.psnd.network.task;


import core.lib.network.base.BaseNetwork;
import core.lib.network.base.HttpHeader;
import core.lib.network.task.BaseTask;
import kh.com.psnd.network.adapter.PsndService;
import kh.com.psnd.network.request.RequestPosition_label_9;
import kh.com.psnd.network.response.ResponsePosition_Label_9;
import lombok.val;
import retrofit2.Response;
import retrofit2.Retrofit;

public class TaskPosition_label_9 extends BaseTask<RequestPosition_label_9, Response, ResponsePosition_Label_9> {

    public TaskPosition_label_9() {
        super(new HttpHeader(BaseNetwork.getToken()));
        setData(new RequestPosition_label_9());
    }

    @Override
    protected Response onExecute(Retrofit retrofit, RequestPosition_label_9 param) throws Exception {
        val service = retrofit.create(PsndService.class);
        return service.position_Label_8(param).execute();
    }
}
