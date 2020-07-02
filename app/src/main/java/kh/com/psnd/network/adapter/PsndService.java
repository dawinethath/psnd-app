package kh.com.psnd.network.adapter;


import core.lib.network.request.BaseParam;
import kh.com.psnd.network.request.RequestLogin;
import kh.com.psnd.network.request.RequestSearch;
import kh.com.psnd.network.request.RequestStaff;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface PsndService {

    @POST("search")
    Call<Response> search(@Body RequestSearch requestSearch);

    @POST("login")
    Call<Response> login(@Body RequestLogin requestLogin);

    @POST("general_comm")
    Call<Response> generalComm(@Body BaseParam baseParam);

    @POST("get_staff")
    Call<Response> getStaff(@Body RequestStaff requestStaff);

}
