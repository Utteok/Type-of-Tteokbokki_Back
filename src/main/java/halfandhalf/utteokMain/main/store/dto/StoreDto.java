package halfandhalf.utteokMain.main.store.dto;

import halfandhalf.utteokMain.main.store.entity.StoreEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Optional;

@Getter
@Setter
@Builder
public class StoreDto {
    private Long store_seq;
    private String store_name;
    private String store_address;
    private String menu_name;
    private String review;
    private String file_path;
    private String file_original_name;
    private String file_masking_name;
    private String question1;
    private String question2;
    private String question3;
    private String question4;
    private String question5;
    private String question6;
    private String question7;
    private int menu_count;
    private Long user_id;
}
