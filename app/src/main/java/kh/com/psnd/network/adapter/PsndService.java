package kh.com.psnd.network.adapter;


import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;

public interface PsndService {

    @POST("search")
    Call<String> search(@Body String base64);

    @POST("login")
    Call<String> login(@Body String base64);

}
