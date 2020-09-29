package kh.com.psnd.network.task;


import core.lib.network.task.BaseTask;
import kh.com.psnd.helper.TaskHelper;
import kh.com.psnd.network.adapter.PsndService;
import kh.com.psnd.network.request.RequestQRCode;
import kh.com.psnd.network.response.ResponseStaff;
import kh.com.psnd.ui.activity.MainActivity;
import kh.com.psnd.ui.fragment.user.SignUpStep1Fragment;
import lombok.val;
import retrofit2.Response;
import retrofit2.Retrofit;

public class TaskQRCode extends BaseTask<RequestQRCode, Response, ResponseStaff> {

    private Object from = null;

    public TaskQRCode(RequestQRCode requestQRCode, Object from) {
        super(TaskHelper.getHttpHeader());
        setData(requestQRCode);
        this.from = from;
    }

    @Override
    protected Response onExecute(Retrofit retrofit, RequestQRCode param) throws Exception {
        val service = retrofit.create(PsndService.class);
        if (from instanceof MainActivity) {
            return service.qrcode(param).execute();
        }
        else if (from instanceof SignUpStep1Fragment) {
            return service.qrcodeNonAuth(param).execute();
        }
        return null;
    }
}
