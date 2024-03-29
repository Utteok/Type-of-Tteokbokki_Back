package halfandhalf.domain.QA0010.controller;


import halfandhalf.com.config.ResponseMessage;
import halfandhalf.domain.QA0010.dto.QA0010Dto;
import halfandhalf.domain.QA0010.dto.QA0011Dto;
import halfandhalf.domain.QA0010.dto.QA0012Dto;
import halfandhalf.domain.QA0010.service.QA0010Service;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@CrossOrigin(origins = {"http://118.67.132.171", "http://101.101.209.59", "http://dev.utteok.com/", "http://www.utteok.com/", "http://localhost:3000"}, allowCredentials = "true")
@RestController
@RequestMapping("/api")
public class QA0010Controller {

    private final QA0010Service qA0010Service;

    public QA0010Controller(QA0010Service qA0010Service) {
        this.qA0010Service = qA0010Service;
    }

    /*
     *  질문 및 답 가져오기
     */
    @GetMapping(value="/getQA", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getQA(HttpServletRequest request) {
        try {
            List<QA0011Dto> qA0011Dto = qA0010Service.findQuestion();
            List<QA0012Dto> qA0012Dto = qA0010Service.findAnswer();

            QA0010Dto qA0010 = new QA0010Dto(qA0011Dto, qA0012Dto);

            return ResponseEntity.ok(qA0010);
        }
        catch(Exception e){
            // 그 외 에러의 경우 500 메세지
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResponseMessage.valueOfCode("InternalServerError").getMessage());
        }
    }

}
