package kh.com.psnd.network.task;


import core.lib.network.base.BaseNetwork;
import core.lib.network.base.HttpHeader;
import core.lib.network.request.BaseParam;
import core.lib.network.task.BaseTask;
import kh.com.psnd.network.adapter.PsndService;
import kh.com.psnd.network.response.ResponseDepartmentType_Label_2;
import lombok.val;
import retrofit2.Response;
import retrofit2.Retrofit;

public class TaskDepartmentType_label_2 extends BaseTask<BaseParam, Response, ResponseDepartmentType_Label_2> {

    public TaskDepartmentType_label_2() {
        super(new HttpHeader(BaseNetwork.getToken()));
        setData(new BaseParam());
    }

    @Override
    protected Response onExecute(Retrofit retrofit, BaseParam param) throws Exception {
        val service = retrofit.create(PsndService.class);
        return service.departmentType_label_2(param).execute();
    }
}
