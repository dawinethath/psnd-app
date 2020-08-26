package kh.com.psnd.utils;

import androidx.annotation.NonNull;

import java.io.File;

import core.lib.base.BaseApp;
import kh.com.psnd.R;
import kh.com.psnd.network.model.Staff;
import lombok.val;

public class PdfUtil {

    public static File getScreenshotPdfStaff(@NonNull Staff staff) {
        val appName  = BaseApp.context.getString(R.string.app_name);
        val rootPath = BaseApp.context.getCacheDir().getPath() + File.separator + "screenshot_cache";
        new File(rootPath).mkdirs();
        String pdfFile = rootPath + File.separator + appName + "_staff_" + staff.getFullName() + ".pdf";
        return new File(pdfFile);
    }

    public static boolean deleteScreenshotPdfStaff(@NonNull Staff staff) {
        return getScreenshotPdfStaff(staff).delete();
    }

    public static String getPathPdf() {
        val path = BaseApp.context.getCacheDir().getPath() + File.separator + "pdf_cache" + File.separator;
        new File(path).mkdirs();
        return path;
    }
}
