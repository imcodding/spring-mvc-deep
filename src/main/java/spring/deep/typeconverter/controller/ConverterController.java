package spring.deep.typeconverter.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import spring.deep.typeconverter.type.IpPort;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
public class ConverterController {

    @GetMapping("/hello-v1")
    public String helloV1(HttpServletRequest request) {
        String data = request.getParameter("data"); //문자 타입 조회
        Integer intValue = Integer.valueOf(data); //숫자 타입으로 변경
        log.info("intValue = {}", intValue);

        return "ok";
    }

    @GetMapping("/hello-v2")
    public String helloV2(@RequestParam Integer data) {
        //hello-v1?data=10
        //request 데이터는 문자 10이 넘어오는데 스프링이 타입변환 해줌(@RequestParam)
        log.info("data = {}", data);
        return "ok";
    }

    @GetMapping("/ip-port")
    public String ipPort(@RequestParam IpPort ipPort) { //스프링이 ConversionService 를 사용해서 타입을 변환함
        System.out.println("ipPort IP = " + ipPort.getIp());
        System.out.println("ipPort PORT = " + ipPort.getPort());
        return "ok";
    }
}
