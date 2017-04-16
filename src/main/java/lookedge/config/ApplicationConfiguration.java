package lookedge.config;

import org.h2.server.web.WebServlet;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

/**
 * Created by guoxu.wu on 16/6/28.
 */
@Configuration
public class ApplicationConfiguration {
	
	@Bean
    public RestTemplate restTemplate() {
		RestTemplate template = new RestTemplate();
        return template;
    }
	
	
    @Bean
    ServletRegistrationBean h2servletRegistration(){
        ServletRegistrationBean registrationBean = new ServletRegistrationBean( new WebServlet());
        registrationBean.addUrlMappings("/console/*");
        return registrationBean;
    }
    @Bean
    public ResourceBundleMessageSource messageSource() {
        ResourceBundleMessageSource source = new ResourceBundleMessageSource();
        source.setBasenames("i18n/messages");  // name of the resource bundle
        source.setUseCodeAsDefaultMessage(true);
        return source;
    }
    @Bean

    public LocaleResolver localeResolver() {

        SessionLocaleResolver slr = new SessionLocaleResolver();

        //设置默认区域,

        slr.setDefaultLocale(Locale.CHINA);

        return slr;

    }
}
