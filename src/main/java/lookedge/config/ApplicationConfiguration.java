package lookedge.config;

import org.h2.server.web.WebServlet;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

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
}
