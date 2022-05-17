package spring.deep.typeconverter.formatter;

import org.junit.jupiter.api.Test;
import org.springframework.format.support.DefaultFormattingConversionService;
import spring.deep.typeconverter.converter.IpPortToStringConverter;
import spring.deep.typeconverter.converter.StringToIpPortConverter;
import spring.deep.typeconverter.type.IpPort;

import static org.assertj.core.api.Assertions.assertThat;

class FormattingConversionServiceTest {

    @Test
    void formattingConversionService() {

        //FormattingConversionService 는 ConversionService 관련 기능을 상속받음.
        //따라서 컨버터도 포맷터도 모두 등록할 수 있다
        DefaultFormattingConversionService conversionService = new DefaultFormattingConversionService();

        //컨버터 등록
        conversionService.addConverter(new StringToIpPortConverter());
        conversionService.addConverter(new IpPortToStringConverter());

        //포맷터 등록
        conversionService.addFormatter(new MyNumberFormatter());

        //컨버터 사용
        IpPort ipPort = conversionService.convert("127.0.0.1:8080", IpPort.class);
        assertThat(ipPort).isEqualTo(new IpPort("127.0.0.1", 8080));

        //포맷터 사용
        assertThat(conversionService.convert(1000, String.class)).isEqualTo("1,000");
        assertThat(conversionService.convert("1,000", Long.class)).isEqualTo(1000L);
    }
}
