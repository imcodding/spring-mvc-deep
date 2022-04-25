package spring.deep;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DeepApplication {

	public static void main(String[] args) {
		SpringApplication.run(DeepApplication.class, args);
	}

	/* 스프링 부트는 MessageSource 를 자동으로 스프링 빈으로 등록
	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasenames("messages", "errors");
		messageSource.setDefaultEncoding("utf-8");
		return messageSource;
	}
	 */
}
