package kh.com.psnd.network.task;


import core.lib.network.task.BaseTask;
import kh.com.psnd.helper.TaskHelper;
import kh.com.psnd.network.adapter.PsndService;
import kh.com.psnd.network.request.RequestDepartmentType_label_2;
import kh.com.psnd.network.response.ResponseDepartmentType_Label_2;
import lombok.val;
import retrofit2.Response;
import retrofit2.Retrofit;

public class TaskDepartmentType_label_2 extends BaseTask<RequestDepartmentType_label_2, Response, ResponseDepartmentType_Label_2> {

    public TaskDepartmentType_label_2(RequestDepartmentType_label_2 request) {
        super(TaskHelper.getHttpHeader());
        setData(request);
    }

    @Override
    protected Response onExecute(Retrofit retrofit, RequestDepartmentType_label_2 param) throws Exception {
        val service = retrofit.create(PsndService.class);
        return service.departmentType_label_2(param).execute();
    }
}
