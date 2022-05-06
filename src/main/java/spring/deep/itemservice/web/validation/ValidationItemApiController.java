package spring.deep.itemservice.web.validation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import spring.deep.itemservice.web.validation.form.ItemSaveForm;

@Slf4j
@RestController
@RequestMapping("/validation/api/items")
public class ValidationItemApiController {

    /* API 경우
     - 성공 요청: 성공
     - 실패 요청: JSON을 객체로 생성하는 것 자체가 실패함 => 404 에러(ex. 가격에 문자 입력)
     - 검증 오류 요청: JSON을 객체로 생성하는 것은 성공했고, 검증에서 실패함
     */
    @PostMapping("/add")
    public Object addItem(@RequestBody @Validated ItemSaveForm form, BindingResult bindingResult) {

        log.info("API 컨트롤러 호출");

        if(bindingResult.hasErrors()) {
            log.info("검증 오류 발생 errors={}", bindingResult);
            return bindingResult.getAllErrors();
        }

        log.info("성공 로직 실행");
        return form;
    }

    /*
    @ModelAttribute 는 필드 단위로 정교하게 바인딩이 적용된다. 특정 필드가 바인딩 되지 않아도 나머지
    필드는 정상 바인딩 되고, Validator를 사용한 검증도 적용할 수 있다.

    @RequestBody 는 HttpMessageConverter 단계에서 JSON 데이터를 객체로 변경하지 못하면 이후
    단계 자체가 진행되지 않고 예외가 발생한다. 컨트롤러도 호출되지 않고, Validator도 적용할 수 없다.
     */
}
