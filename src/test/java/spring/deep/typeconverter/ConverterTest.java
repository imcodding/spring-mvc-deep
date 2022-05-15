package spring.deep.typeconverter;

import org.junit.jupiter.api.Test;
import spring.deep.typeconverter.converter.IntegerToStringConverter;
import spring.deep.typeconverter.converter.StringToIntegerConverter;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ConverterTest {

    @Test
    void stringToInteger() {
        StringToIntegerConverter converter = new StringToIntegerConverter();
        Integer result = converter.convert("10");
        assertThat(result).isEqualTo(10);
    }

    @Test
    void integerToString() {
        IntegerToStringConverter converter = new IntegerToStringConverter();
        String result = converter.convert(10);
        assertThat(result).isEqualTo("10");
    }

}
