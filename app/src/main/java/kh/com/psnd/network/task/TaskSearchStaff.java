package kh.com.psnd.network.task;


import core.lib.network.task.BaseTask;
import kh.com.psnd.helper.TaskHelper;
import kh.com.psnd.network.adapter.PsndService;
import kh.com.psnd.network.request.RequestSearchStaff;
import kh.com.psnd.network.response.ResponseSearchStaff;
import lombok.val;
import retrofit2.Response;
import retrofit2.Retrofit;

public class TaskSearchStaff extends BaseTask<RequestSearchStaff, Response, ResponseSearchStaff> {

    public TaskSearchStaff(RequestSearchStaff requestSearchStaff) {
        super(TaskHelper.getHttpHeader());
        setData(requestSearchStaff);
    }

    @Override
    protected Response onExecute(Retrofit retrofit, RequestSearchStaff param) throws Exception {
        val service = retrofit.create(PsndService.class);
        return service.searchStaff(param).execute();
    }
}
