package kh.com.psnd.network.task;


import core.lib.network.base.BaseNetwork;
import core.lib.network.base.HttpHeader;
import core.lib.network.task.BaseTask;
import kh.com.psnd.helper.LoginManager;
import kh.com.psnd.network.adapter.PsndService;
import kh.com.psnd.network.request.RequestSectorName_label_7;
import kh.com.psnd.network.response.ResponseSectionName_Label_7;
import lombok.val;
import retrofit2.Response;
import retrofit2.Retrofit;

public class TaskSectorName_label_7 extends BaseTask<RequestSectorName_label_7, Response, ResponseSectionName_Label_7> {

    public TaskSectorName_label_7(RequestSectorName_label_7 request) {
        super(new HttpHeader(BaseNetwork.getToken(), LoginManager.getUserToken()));
        setData(request);
    }

    @Override
    protected Response onExecute(Retrofit retrofit, RequestSectorName_label_7 param) throws Exception {
        val service = retrofit.create(PsndService.class);
        return service.sectionName_label_7(param).execute();
    }
}
