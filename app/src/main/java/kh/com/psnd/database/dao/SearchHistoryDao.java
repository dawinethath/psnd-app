package kh.com.psnd.database.dao;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.util.LinkedList;

import core.lib.base.BaseApp;
import core.lib.network.model.BaseGson;
import core.lib.utils.FileManager;
import kh.com.psnd.network.model.Config;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.val;
import lombok.var;

@Data
@EqualsAndHashCode(callSuper = false)
public class SearchHistoryDao extends BaseGson {

    private static final String CACHE_NAME = "SearchHistoryDAO";

    @SerializedName("search")
    private LinkedList<String> search = new LinkedList<>();

    public static SearchHistoryDao getCache() {
        try {
            String json   = FileManager.readTextFileInContext(BaseApp.context, CACHE_NAME);
            var    search = new Gson().fromJson(json, SearchHistoryDao.class);
            if (search == null || search.getSearch().size() == 0) {
                search = new SearchHistoryDao();
            }
            return search;
        } catch (Exception e) {
        }
        return new SearchHistoryDao();
    }

    public void saveToCache() {
        FileManager.writeTextToFileInContext(BaseApp.context, CACHE_NAME, toJson());
    }

    public String[] getArraySearch() {
        String[] objs = new String[search.size()];
        for (int i = 0; i < search.size(); i++) {
            objs[i] = search.get(i);
        }
        return objs;
    }


    public static SearchHistoryDao addSearch(String text) {
        val history    = getCache();
        var maxHistory = 10;
        val appConfig  = Config.newInstance().getAppConfig();
        if (appConfig != null) {
            maxHistory = appConfig.getMaxSearchHistory();
        }
        if (history.getSearch().size() >= maxHistory) {
            history.getSearch().removeLast();
        }
        int index = -1;
        for (int i = 0; i < history.getSearch().size(); i++) {
            val label = history.getSearch().get(i);
            if (label.equals(text)) {
                index = i;
            }
        }
        if (index != -1) {
            history.getSearch().remove(index);
        }
        history.getSearch().addFirst(text);
        history.saveToCache();
        return history;
    }


}
