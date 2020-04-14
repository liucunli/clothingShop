package com.clothesshop.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2
public class MyConfiguration extends WebMvcConfigurationSupport {
    @Override
    protected void addCorsMappings(CorsRegistry registry) {
        super.addCorsMappings(registry);
        registry.addMapping("/**").allowedOrigins("*").allowedMethods("*")
                .allowedHeaders("*").maxAge(3600);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    @Bean
    public Docket docket0() {

        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("li1");
    }
    @Bean
    public Docket docket(){

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("li")
                .enable(true)
                .select()
                //RequestHandlerSelectors:配置接口扫描的方式
                /*
                 * any()
                 * none()
                 * basePackage():基于某个包扫描
                 * */
                .apis(RequestHandlerSelectors.basePackage("com.news.controller"))
                //过滤
                /*
                 * ant():过滤某个路径
                 * any():过滤所有
                 * none():不过滤
                 * regex():基于正则表达式
                 * */
                // .paths(PathSelectors.ant("/com"))
                .build();
    }
    private ApiInfo apiInfo(){
        Contact contact = new Contact("Chaney","no","954275226@qq.com");
        return new ApiInfo(
                "Test swagger",
                "ClotesShop api",
                "1.0",
                "urn:tos",
                contact,
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList<VendorExtension>()
        );
    }
}
