package kh.com.psnd.network.task;


import core.lib.network.task.BaseTask;
import kh.com.psnd.helper.TaskHelper;
import kh.com.psnd.network.adapter.PsndService;
import kh.com.psnd.network.request.RequestGeneralComm_label_1;
import kh.com.psnd.network.response.ResponseGeneralComm_Label_1;
import lombok.val;
import retrofit2.Response;
import retrofit2.Retrofit;

public class TaskGeneralComm_label_1 extends BaseTask<RequestGeneralComm_label_1, Response, ResponseGeneralComm_Label_1> {

    public TaskGeneralComm_label_1() {
        super(TaskHelper.getHttpHeader());
        setData(new RequestGeneralComm_label_1());
    }

    @Override
    protected Response onExecute(Retrofit retrofit, RequestGeneralComm_label_1 param) throws Exception {
        val service = retrofit.create(PsndService.class);
        return service.generalComm_label_1(param).execute();
    }
}
