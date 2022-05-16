package spring.deep.typeconverter.converter;

import org.junit.jupiter.api.Test;
import org.springframework.core.convert.support.DefaultConversionService;
import spring.deep.typeconverter.type.IpPort;

import static org.assertj.core.api.Assertions.*;

public class ConversionServiceTest {

    @Test
    void conversionService() {
        DefaultConversionService conversionService = new DefaultConversionService();

        /* 등록 */
        //예시) 스프링에서 기본적으로 제공하는 컨버터임. 없어도 알아서 변환해줌
        //직접 추가한 것이 우선순위 가짐.
        conversionService.addConverter(new IntegerToStringConverter());
        conversionService.addConverter(new StringToIntegerConverter());
        //사용자 정의
        conversionService.addConverter(new StringToIpPortConverter());
        conversionService.addConverter(new IpPortToStringConverter());

        /* 사용 */
        assertThat(conversionService.convert("10", Integer.class)).isEqualTo(10);
        assertThat(conversionService.convert(10, String.class)).isEqualTo("10");
        IpPort ipPort = conversionService.convert("127.0.0.1:8080", IpPort.class);
        assertThat(ipPort).isEqualTo(new IpPort("127.0.0.1", 8080));

        String ipPortString = conversionService.convert(new IpPort("127.0.0.1", 8080), String.class);
        assertThat(ipPortString).isEqualTo("127.0.0.1:8080");
    }
    /*
     #ISP 원칙 적용
     - 타입을 변환을 원하는 사용자는 컨버전 서비스 인터페이스에만 의존하면 된다.
     - 특히 컨버터를 사용하는 클라이언트는 ConversionService 만 의존하면 되므로,
       컨버터를 어떻게 등록하고 관리하는지는 전혀 몰라도 된다.
     */
}
