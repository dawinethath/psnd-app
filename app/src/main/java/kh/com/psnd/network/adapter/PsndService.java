package kh.com.psnd.network.adapter;


import kh.com.psnd.network.request.RequestConfig;
import kh.com.psnd.network.request.RequestDepartmentType_label_2;
import kh.com.psnd.network.request.RequestDepartment_label_3;
import kh.com.psnd.network.request.RequestGeneralComm_label_1;
import kh.com.psnd.network.request.RequestLogin;
import kh.com.psnd.network.request.RequestOfficeName_label_5;
import kh.com.psnd.network.request.RequestOfficeType_label_4;
import kh.com.psnd.network.request.RequestPosition_label_9;
import kh.com.psnd.network.request.RequestQRCode;
import kh.com.psnd.network.request.RequestRank_label_8;
import kh.com.psnd.network.request.RequestSearch;
import kh.com.psnd.network.request.RequestSectorName_label_7;
import kh.com.psnd.network.request.RequestSectorType_label_6;
import kh.com.psnd.network.request.RequestSignUpVerify;
import kh.com.psnd.network.request.RequestStaff;
import kh.com.psnd.network.request.RequestUserPrivilege;
import kh.com.psnd.network.request.RequestUserRole;
import kh.com.psnd.network.request.RequestUserSearch;
import kh.com.psnd.network.response.ResponseConfig;
import kh.com.psnd.network.response.ResponseDepartmentType_Label_2;
import kh.com.psnd.network.response.ResponseDepartment_Label_3;
import kh.com.psnd.network.response.ResponseGeneralComm_Label_1;
import kh.com.psnd.network.response.ResponseLogin;
import kh.com.psnd.network.response.ResponseOfficeName_Label_5;
import kh.com.psnd.network.response.ResponseOfficeType_Label_4;
import kh.com.psnd.network.response.ResponsePosition_Label_9;
import kh.com.psnd.network.response.ResponseRank_Label_8;
import kh.com.psnd.network.response.ResponseSearch;
import kh.com.psnd.network.response.ResponseSectionName_Label_7;
import kh.com.psnd.network.response.ResponseSectorType_Label_6;
import kh.com.psnd.network.response.ResponseSignUpVerify;
import kh.com.psnd.network.response.ResponseStaff;
import kh.com.psnd.network.response.ResponseUserPrivileges;
import kh.com.psnd.network.response.ResponseUserRoles;
import kh.com.psnd.network.response.ResponseUserSearch;
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

    @POST("user_privilege")
    Call<ResponseUserPrivileges> userPrivileges(@Body RequestUserPrivilege requestUserPrivilege);

    @POST("user_roles")
    Call<ResponseUserRoles> userRoles(@Body RequestUserRole requestUserRole);

    @POST("user_search")
    Call<ResponseUserSearch> userSearch(@Body RequestUserSearch requestUserSearch);

    @POST("sign_up_authentication")
    Call<ResponseSignUpVerify> signUpVerify(@Body RequestSignUpVerify requestSignUpVerify);

    @POST("search_staffs")
    Call<ResponseSearch> search(@Body RequestSearch requestSearch);

    @POST("get_staff_by_qrcode")
    Call<ResponseStaff> qrcode(@Body RequestQRCode requestQRCode);

    @POST("amplify_configure")
    Call<ResponseConfig> config(@Body RequestConfig requestConfig);

    @POST("login")
    Call<ResponseLogin> login(@Body RequestLogin requestLogin);

    @POST("get_staff")
    Call<ResponseStaff> getStaff(@Body RequestStaff requestStaff);

    @POST("general_comm")
    Call<ResponseGeneralComm_Label_1> generalComm_label_1(@Body RequestGeneralComm_label_1 param);

    @POST("dep_type_from_departments")
    Call<ResponseDepartmentType_Label_2> departmentType_label_2(@Body RequestDepartmentType_label_2 param);

    @POST("departments")
    Call<ResponseDepartment_Label_3> department_label_3(@Body RequestDepartment_label_3 param);

    @POST("office_type_id")
    Call<ResponseOfficeType_Label_4> officeType_label_4(@Body RequestOfficeType_label_4 param);

    @POST("offices_name")
    Call<ResponseOfficeName_Label_5> officesName_label_5(@Body RequestOfficeName_label_5 param);

    @POST("sec_type_id")
    Call<ResponseSectorType_Label_6> sectorType_label_6(@Body RequestSectorType_label_6 param);

    @POST("sectors_name")
    Call<ResponseSectionName_Label_7> sectionName_label_7(@Body RequestSectorName_label_7 param);

    @POST("get_all_ranks")
    Call<ResponseRank_Label_8> rank_Label_8(@Body RequestRank_label_8 param);

    @POST("get_all_positions")
    Call<ResponsePosition_Label_9> position_Label_8(@Body RequestPosition_label_9 param);

}
