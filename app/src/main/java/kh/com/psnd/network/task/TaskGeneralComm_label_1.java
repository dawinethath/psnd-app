package kh.com.psnd.network.task;


import core.lib.network.base.BaseNetwork;
import core.lib.network.base.HttpHeader;
import core.lib.network.task.BaseTask;
import kh.com.psnd.helper.LoginManager;
import kh.com.psnd.network.adapter.PsndService;
import kh.com.psnd.network.request.RequestGeneralComm_label_1;
import kh.com.psnd.network.response.ResponseGeneralComm_Label_1;
import lombok.val;
import retrofit2.Response;
import retrofit2.Retrofit;

public class TaskGeneralComm_label_1 extends BaseTask<RequestGeneralComm_label_1, Response, ResponseGeneralComm_Label_1> {

    public TaskGeneralComm_label_1() {
        super(new HttpHeader(BaseNetwork.getToken(), LoginManager.getUserToken()));
        setData(new RequestGeneralComm_label_1());
    }

    @Override
    protected Response onExecute(Retrofit retrofit, RequestGeneralComm_label_1 param) throws Exception {
        val service = retrofit.create(PsndService.class);
        return service.generalComm_label_1(param).execute();
    }
}
