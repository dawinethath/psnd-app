package kh.com.psnd.network.task;


import core.lib.network.task.BaseTask;
import kh.com.psnd.helper.TaskHelper;
import kh.com.psnd.network.adapter.PsndService;
import kh.com.psnd.network.request.RequestDepartment_label_3;
import kh.com.psnd.network.response.ResponseDepartment_Label_3;
import lombok.val;
import retrofit2.Response;
import retrofit2.Retrofit;

public class TaskDepartment_label_3 extends BaseTask<RequestDepartment_label_3, Response, ResponseDepartment_Label_3> {

    public TaskDepartment_label_3(RequestDepartment_label_3 request) {
        super(TaskHelper.getHttpHeader());
        setData(request);
    }

    @Override
    protected Response onExecute(Retrofit retrofit, RequestDepartment_label_3 param) throws Exception {
        val service = retrofit.create(PsndService.class);
        return service.department_label_3(param).execute();
    }
}
