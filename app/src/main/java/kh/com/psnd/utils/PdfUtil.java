package kh.com.psnd.utils;

import java.io.File;

import core.lib.base.BaseApp;
import lombok.val;

public class PdfUtil {
    public static String getPathPdf() {
        val path = BaseApp.context.getCacheDir().getPath() + File.separator + "pdf_cache" + File.separator;
        new File(path).mkdirs();
        return path;
    }
}
