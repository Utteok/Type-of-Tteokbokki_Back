package halfandhalf.utteokMain.review.dto;


import halfandhalf.utteokMain.manageEntity.FileManageEntity;
import halfandhalf.utteokMain.review.entity.ReviewEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Getter
@Setter
public class ReviewDto {
    private Long id;
    private Long user_id;

    private String content;
    private String create_date;

    private String file_path;
    private String file_original_name;
    private String file_masking_name;

    public ReviewDto() {
    }

    public ReviewDto(ReviewEntity entity) {
        this.id = entity.getId();
        this.content = entity.getContent();
        this.create_date = entity.getCreate_date().toString();
        if(!ObjectUtils.isEmpty(entity.getFileManage())) {
            this.file_path = entity.getFileManage().getFile_path();
            this.file_original_name = entity.getFileManage().getFile_original_name();
        }
    }

    public ReviewDto(Long id, String content, String path, String original, String masking) {
        this.id = id;
        this.content = content;
        this.file_path = path;
        this.file_original_name = original;
        this.file_masking_name = masking;
    }
}
