package kh.com.psnd.network.task;


import core.lib.network.base.BaseNetwork;
import core.lib.network.base.HttpHeader;
import core.lib.network.task.BaseTask;
import kh.com.psnd.helper.LoginManager;
import kh.com.psnd.network.adapter.PsndService;
import kh.com.psnd.network.request.RequestQRCode;
import kh.com.psnd.network.response.ResponseStaff;
import lombok.val;
import retrofit2.Response;
import retrofit2.Retrofit;

public class TaskQRCode extends BaseTask<RequestQRCode, Response, ResponseStaff> {

    public TaskQRCode(RequestQRCode requestQRCode) {
        super(new HttpHeader(BaseNetwork.getToken(), LoginManager.getUserToken()));
        setData(requestQRCode);
    }

    @Override
    protected Response onExecute(Retrofit retrofit, RequestQRCode param) throws Exception {
        val service = retrofit.create(PsndService.class);
        return service.qrcode(param).execute();
    }
}
