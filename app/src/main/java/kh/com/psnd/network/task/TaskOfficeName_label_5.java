package kh.com.psnd.network.task;


import core.lib.network.task.BaseTask;
import kh.com.psnd.helper.TaskHelper;
import kh.com.psnd.network.adapter.PsndService;
import kh.com.psnd.network.request.RequestOfficeName_label_5;
import kh.com.psnd.network.response.ResponseOfficeName_Label_5;
import lombok.val;
import retrofit2.Response;
import retrofit2.Retrofit;

public class TaskOfficeName_label_5 extends BaseTask<RequestOfficeName_label_5, Response, ResponseOfficeName_Label_5> {

    public TaskOfficeName_label_5(RequestOfficeName_label_5 request) {
        super(TaskHelper.getHttpHeader());
        setData(request);
    }

    @Override
    protected Response onExecute(Retrofit retrofit, RequestOfficeName_label_5 param) throws Exception {
        val service = retrofit.create(PsndService.class);
        return service.officesName_label_5(param).execute();
    }
}
