package core.lib.network.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public abstract class BaseGson implements Serializable {
    public String toJson() {
        return new Gson().toJson(this);
    }

    public String toPrettyJson() {
        return new GsonBuilder().setPrettyPrinting().create().toJson(this);
    }

}
