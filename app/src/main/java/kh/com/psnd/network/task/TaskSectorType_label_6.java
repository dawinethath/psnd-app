package kh.com.psnd.network.task;


import core.lib.network.task.BaseTask;
import kh.com.psnd.helper.TaskHelper;
import kh.com.psnd.network.adapter.PsndService;
import kh.com.psnd.network.request.RequestSectorType_label_6;
import kh.com.psnd.network.response.ResponseSectorType_Label_6;
import lombok.val;
import retrofit2.Response;
import retrofit2.Retrofit;

public class TaskSectorType_label_6 extends BaseTask<RequestSectorType_label_6, Response, ResponseSectorType_Label_6> {

    public TaskSectorType_label_6() {
        super(TaskHelper.getHttpHeader());
        setData(new RequestSectorType_label_6());
    }

    @Override
    protected Response onExecute(Retrofit retrofit, RequestSectorType_label_6 param) throws Exception {
        val service = retrofit.create(PsndService.class);
        return service.sectorType_label_6(param).execute();
    }
}
