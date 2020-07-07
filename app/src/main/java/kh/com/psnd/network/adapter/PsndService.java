package kh.com.psnd.network.adapter;


import core.lib.network.request.BaseParam;
import kh.com.psnd.network.request.RequestLogin;
import kh.com.psnd.network.request.RequestSearch;
import kh.com.psnd.network.request.RequestStaff;
import kh.com.psnd.network.response.ResponseDepartmentType_Label_2;
import kh.com.psnd.network.response.ResponseGeneralComm_Label_1;
import kh.com.psnd.network.response.ResponseLogin;
import kh.com.psnd.network.response.ResponseSearch;
import kh.com.psnd.network.response.ResponseStaff;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * POST - https://246chazd1d.execute-api.ap-southeast-1.amazonaws.com/dev/general_comm
 * POST - https://246chazd1d.execute-api.ap-southeast-1.amazonaws.com/dev/dep_type_from_departments
 * POST - https://246chazd1d.execute-api.ap-southeast-1.amazonaws.com/dev/departments
 * POST - https://246chazd1d.execute-api.ap-southeast-1.amazonaws.com/dev/office_type_id
 * POST - https://246chazd1d.execute-api.ap-southeast-1.amazonaws.com/dev/offices_name
 * POST - https://246chazd1d.execute-api.ap-southeast-1.amazonaws.com/dev/sec_type_id
 * POST - https://246chazd1d.execute-api.ap-southeast-1.amazonaws.com/dev/sectors_name
 * <p>
 * POST - https://246chazd1d.execute-api.ap-southeast-1.amazonaws.com/dev/search_staffs
 * POST - https://246chazd1d.execute-api.ap-southeast-1.amazonaws.com/dev/get_staff
 */
public interface PsndService {

    @POST("search_staffs")
    Call<ResponseSearch> search(@Body RequestSearch requestSearch);

    @POST("login")
    Call<ResponseLogin> login(@Body RequestLogin requestLogin);

    @POST("general_comm")
    Call<ResponseGeneralComm_Label_1> generalComm_label_1(@Body BaseParam baseParam);

    @POST("dep_type_from_departments")
    Call<ResponseDepartmentType_Label_2> departmentType_label_2(@Body BaseParam baseParam);

    @POST("get_staff")
    Call<ResponseStaff> getStaff(@Body RequestStaff requestStaff);

}
