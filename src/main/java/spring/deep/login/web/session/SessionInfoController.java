package spring.deep.login.web.session;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

@Slf4j
@RestController
public class SessionInfoController {

    @GetMapping("/session-info")
    public String sessionInfo(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if(session == null) {
            return "세션이 없습니다.";
        }

        //세션 데이터 출력
        session.getAttributeNames().asIterator()
                .forEachRemaining(name -> log.info("session name ={}, value={}", name, session.getAttribute(name)));

        log.info("sessionId={}", session.getId()); //세션 ID
        log.info("maxInactiveInterval={}", session.getMaxInactiveInterval()); //세션 유효 시간
        log.info("creationTime={}", new Date(session.getCreationTime())); //세션 생성 일시
        log.info("lastAccessedTime={}", session.getLastAccessedTime()); //세션 연결된 사용자가 서버에 접근한 시간
        log.info("isNew={}", session.isNew()); //새로 생성된 세션인지

        //session.setMaxInactiveInterval(1800); //특정 세션 시간 설정

        return "세션 출력";
    }
}
