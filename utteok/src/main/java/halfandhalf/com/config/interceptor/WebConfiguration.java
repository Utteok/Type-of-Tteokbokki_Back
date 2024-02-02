package halfandhalf.com.config.interceptor;

import halfandhalf.com.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/**");
        System.out.println("WebConfiguration addInterceptors");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("118.67.132.171:3000")
                .allowedMethods("GET","POST","DELETE","PUT")
                .allowCredentials(true);
        System.out.println("WebConfiguration addCorsMappings");
    }
}
