package kh.com.psnd.network.adapter;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface PsndService {

    @POST("search")
    Call<String> search(@Body String base64);

    @POST("login")
    Call<String> login(@Body String base64);

}
