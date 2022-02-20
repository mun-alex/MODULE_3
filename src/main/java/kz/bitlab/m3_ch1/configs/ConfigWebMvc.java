package kz.bitlab.m3_ch1.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import java.util.Locale;

@Configuration
public class ConfigWebMvc implements WebMvcConfigurer {

    @Bean
    public ReloadableResourceBundleMessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:messages");
        messageSource.setDefaultEncoding("utf-8");
        return messageSource;
    }

    @Bean
    public CookieLocaleResolver localeResolver() {
        CookieLocaleResolver clr = new CookieLocaleResolver();
        clr.setDefaultLocale(new Locale("en"));
        clr.setCookieName("language");
        clr.setCookieMaxAge(3600);
        return clr;
    }

    @Bean
    public LocaleChangeInterceptor interceptor() {
        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
        lci.setParamName("lng");
        return lci;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(interceptor());
    }
}
