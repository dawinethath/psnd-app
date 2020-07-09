package kh.com.psnd.network.request;

import com.google.gson.annotations.SerializedName;

import core.lib.network.request.BaseParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class RequestSearch extends BaseParam {

    @SerializedName("search")
    private String search;
    @SerializedName("filter")
    private Filter filter = null;
    @SerializedName("page")
    private int    page;

    /**
     * "general_id": 6,
     * "department_id": 114,
     * "office_id": 1,
     * "sector_id": 1
     */
    @Data
    @EqualsAndHashCode(callSuper = false)
    public static class Filter extends BaseParam {

        @SerializedName("general_id")
        private int    generalId   = -1;
        @SerializedName("general_name")
        private String generalName = null;

        @SerializedName("department_id")
        private int    departmentId   = -1;
        @SerializedName("department_name")
        private String departmentName = null;

        @SerializedName("office_id")
        private int    officeId   = -1;
        @SerializedName("office_name")
        private String officeName = null;

        @SerializedName("sector_id")
        private int    sectorId   = -1;
        @SerializedName("sector_name")
        private String sectorName = null;
    }
}
