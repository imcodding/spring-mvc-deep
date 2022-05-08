package spring.deep.login.web.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.UUID;

@Slf4j
public class LogFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("log filter init");
    }

    @Override // http 요청 시 호출됨
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request; //down casting - http 요청만 고려할 경우
        String requestURI = httpRequest.getRequestURI();

        String uuid = UUID.randomUUID().toString(); //요청 구분

        try {
            log.info("REQUEST  [{}][{}]", uuid, requestURI);
            chain.doFilter(request, response); //중요! 필터 있으면 필터 호출, 없으면 서블릿 호출
        } catch (Exception e) {
            throw e;
        } finally {
            log.info("RESPONSE [{}][{}]", uuid, requestURI);
        }
    }

    @Override
    public void destroy() {
        log.info("log filter destroy");
    }
}
