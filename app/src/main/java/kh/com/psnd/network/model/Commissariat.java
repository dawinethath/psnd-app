package kh.com.psnd.network.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import core.lib.network.model.BaseGson;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class Commissariat extends BaseGson {

    @SerializedName("id")
    private String               id;
    @SerializedName("name")
    private String               name;
    @SerializedName("departmentType")
    private List<DepartmentType> departmentTypes;

    @Override
    public String toString() {
        return name;
    }

    public void addDepartmentType(DepartmentType departmentType) {
        if (departmentTypes == null) {
            departmentTypes = new ArrayList<>();
        }
        departmentTypes.add(departmentType);
    }

}
