package kh.com.psnd.network.model;

import com.google.gson.annotations.SerializedName;

import org.apache.commons.io.FilenameUtils;

import java.io.File;

import core.lib.network.model.BaseGson;
import kh.com.psnd.utils.PdfUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.val;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class StaffRecord extends BaseGson {

    @SerializedName("text")
    private String name;
    @SerializedName("announce_number")
    private String announceNumber;
    @SerializedName("announce_date")
    private String dateAnnounce;
    @SerializedName("pdfUrl")
    private String pdfUrl;

    public File getLocalPdfFile() {
        val filename = FilenameUtils.getName(pdfUrl);
        val pdfFile  = new File(PdfUtil.getPathPdf() + filename);
        return pdfFile;
    }

    public boolean deleteLocalPdf(){
        return getLocalPdfFile().delete();
    }
}
