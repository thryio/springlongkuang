package org.pcl.springlongkuang.Configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class MyWebMvcConfigurerAdapter extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        //指向外部目录
        try {
            registry.addResourceHandler("/res/**").addResourceLocations("file:"+ ResourceUtils.getURL("classpath:").getPath()+"/files/");
            super.addResourceHandlers(registry);
        }catch (Exception e ){
            e.printStackTrace();
        }
    }

//    @Bean
//    public JWTInterceptor jwtInterceptor() {
//        return new JWTInterceptor();
//    }
//
//    // 自定义过滤规则
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
////        registry.addInterceptor(jwtInterceptor()).addPathPatterns("/driver/*");
//        List<String> path = new ArrayList<>();
//        path.add("/driver/*");
//        path.add("/order/*");
////        path.add("/client/*");
//        registry.addInterceptor(jwtInterceptor()).addPathPatterns(path);
//
//    }

    // 跨域请求
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowCredentials(true)
                .allowedHeaders("*")
                .allowedOrigins("*")
                .allowedMethods("GET","POST", "PUT", "DELETE","OPTIONS")
                .allowedMethods("*");

    }
}
