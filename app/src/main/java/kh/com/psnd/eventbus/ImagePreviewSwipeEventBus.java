package kh.com.psnd.eventbus;

import kh.com.psnd.network.model.Staff;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ImagePreviewSwipeEventBus {
    private Staff staff;
    private int   position;
}
