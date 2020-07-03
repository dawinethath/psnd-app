package kh.com.psnd.network.response;


import com.google.gson.annotations.SerializedName;

import java.util.List;

import core.lib.network.response.BaseResponse;
import kh.com.psnd.network.model.Search;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ResponseSearch extends BaseResponse {

    @SerializedName("result")
    private List<Search> result;

}
