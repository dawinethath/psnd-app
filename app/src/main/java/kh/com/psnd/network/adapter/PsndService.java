package kh.com.psnd.network.adapter;


import core.lib.network.request.BaseParam;
import kh.com.psnd.network.request.RequestLogin;
import kh.com.psnd.network.request.RequestSearch;
import kh.com.psnd.network.request.RequestStaff;
import kh.com.psnd.network.response.ResponseGeneralComm;
import kh.com.psnd.network.response.ResponseLogin;
import kh.com.psnd.network.response.ResponseSearch;
import kh.com.psnd.network.response.ResponseStaff;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface PsndService {

    @POST("search_staffs")
    Call<ResponseSearch> search(@Body RequestSearch requestSearch);

    @POST("login")
    Call<ResponseLogin> login(@Body RequestLogin requestLogin);

    @POST("general_comm")
    Call<ResponseGeneralComm> generalComm(@Body BaseParam baseParam);

    @POST("get_staff")
    Call<ResponseStaff> getStaff(@Body RequestStaff requestStaff);

}
