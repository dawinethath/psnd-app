package kh.com.psnd.network.task;


import core.lib.network.task.BaseTask;
import kh.com.psnd.helper.TaskHelper;
import kh.com.psnd.network.adapter.PsndService;
import kh.com.psnd.network.request.RequestRank_label_8;
import kh.com.psnd.network.response.ResponseRank_Label_8;
import lombok.val;
import retrofit2.Response;
import retrofit2.Retrofit;

public class TaskRank_label_8 extends BaseTask<RequestRank_label_8, Response, ResponseRank_Label_8> {

    public TaskRank_label_8() {
        super(TaskHelper.getHttpHeader());
        setData(new RequestRank_label_8());
    }

    @Override
    protected Response onExecute(Retrofit retrofit, RequestRank_label_8 param) throws Exception {
        val service = retrofit.create(PsndService.class);
        return service.rank_Label_8(param).execute();
    }
}
