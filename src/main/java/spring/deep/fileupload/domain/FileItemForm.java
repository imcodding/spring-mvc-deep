package spring.deep.fileupload.domain;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

//상품 저장폼
@Data
public class FileItemForm {
    private Long itemId;
    private String itemName;
    private List<MultipartFile> imageFiles;
    private MultipartFile attachFile;
}
