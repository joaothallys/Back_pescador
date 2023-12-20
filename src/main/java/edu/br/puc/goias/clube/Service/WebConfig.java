package edu.br.puc.goias.clube.Service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
public class WebConfig implements WebMvcConfigurer {


    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/classes/edu/br/puc/goias/clube/Membro.jrxml");
        resolver.setSuffix(".jasper");
        resolver.setViewNames("Membro");
        resolver.setOrder(1);
        return resolver;
    }
}
