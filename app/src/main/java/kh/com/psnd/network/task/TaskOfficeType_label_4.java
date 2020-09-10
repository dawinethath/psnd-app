package kh.com.psnd.network.task;


import core.lib.network.task.BaseTask;
import kh.com.psnd.helper.TaskHelper;
import kh.com.psnd.network.adapter.PsndService;
import kh.com.psnd.network.request.RequestOfficeType_label_4;
import kh.com.psnd.network.response.ResponseOfficeType_Label_4;
import lombok.val;
import retrofit2.Response;
import retrofit2.Retrofit;

public class TaskOfficeType_label_4 extends BaseTask<RequestOfficeType_label_4, Response, ResponseOfficeType_Label_4> {

    public TaskOfficeType_label_4(RequestOfficeType_label_4 request) {
        super(TaskHelper.getHttpHeader());
        setData(request);
    }

    @Override
    protected Response onExecute(Retrofit retrofit, RequestOfficeType_label_4 param) throws Exception {
        val service = retrofit.create(PsndService.class);
        return service.officeType_label_4(param).execute();
    }
}
