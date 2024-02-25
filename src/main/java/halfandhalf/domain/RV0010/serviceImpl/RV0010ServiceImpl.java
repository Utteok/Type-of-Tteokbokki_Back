package halfandhalf.domain.RV0010.serviceImpl;

import halfandhalf.domain.RV0010.dao.RV0010Dao;
import halfandhalf.domain.RV0010.dto.RV0010Dto;
import halfandhalf.domain.RV0010.dto.RV0011Dto;
import halfandhalf.domain.RV0010.service.RV0010Service;
import halfandhalf.domain.RV0010.serviceImpl.upload.Upload;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RV0010ServiceImpl implements RV0010Service {

    @Value("${api.upload.dir.review}")
    private String uploadDir;
    private final RV0010Dao rV0010Dao;

    @Override
    @Transactional(readOnly = true)
    public RV0010Dto findOneFromRecommend(RV0010Dto rv0010Dto) {
        return rV0010Dao.findOneFromRecommend(rv0010Dto);
    }

    @Override
    @Transactional
    public void saveRecommend(RV0010Dto rv0010Dto, MultipartFile file) throws Exception{
        Optional.ofNullable(rv0010Dto.getContent()).orElseThrow(() -> new NullPointerException("내용을 입력해 주세요"));
        rV0010Dao.modifyRecommend(upload(rv0010Dto, file));
    }

    @Override
    @Transactional
    public List<RV0010Dto> findRecommendByPage(RV0011Dto rv0011Dto, Long user_id) {
        rv0011Dto.setPageNum(rv0011Dto.getSize() * rv0011Dto.getPageNum());
        List<RV0010Dto> result = rV0010Dao.findRecommendByPage(rv0011Dto);
        // 내가 추천한 글이면 true 반환 / 내 글에 대해 표시를 프론트단에서 처리하기 위함
        for(RV0010Dto dto : result) {
            dto.setMy_recommend(user_id.equals(dto.getUser_id()));
        }
        return result;
    }

    @Override
    public void modifyRecommend(RV0010Dto rv0010Dto, MultipartFile file) throws Exception {
        Optional.ofNullable(rv0010Dto.getContent()).orElseThrow(() -> new NullPointerException("내용을 입력해 주세요"));
        rV0010Dao.modifyRecommend(upload(rv0010Dto, file));
    }

    @Override
    @Transactional
    public void deleteRecommend(RV0010Dto rv0010Dto) {
        rV0010Dao.deleteRecommend(rv0010Dto);
    }

    private RV0010Dto upload(RV0010Dto rv0010Dto, MultipartFile file) throws Exception {
        if(file != null && !file.isEmpty()) {
            Map<String,String> uploadFile = new Upload(uploadDir, file).uploadImage();
            rv0010Dto.setFile(uploadFile.get("path"),uploadFile.get("original"),uploadFile.get("masking"));
        }
        return rv0010Dto;
    }
}
