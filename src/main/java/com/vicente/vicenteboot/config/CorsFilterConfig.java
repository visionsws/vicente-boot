package com.vicente.vicenteboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * @author :lijunkang
 * @description: 处理AJAX请求跨域的问题
 * @dateTime 2019/111/06 16:42
 */

@Configuration
public class CorsFilterConfig {
    private CorsConfiguration buildConfig() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        // 允许跨域访问的域名
        corsConfiguration.addAllowedOrigin("*");
        // 请求头
        corsConfiguration.addAllowedHeader("*");
        // 请求方法
        corsConfiguration.addAllowedMethod("*");
        // 预检请求的有效期，单位为秒。
        corsConfiguration.setMaxAge(3600L);
        // 是否支持安全证书
        corsConfiguration.setAllowCredentials(true);
        return corsConfiguration;
    }

    /**
     * 跨域过滤器
     *
     * @return
     */
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", buildConfig());
        return new CorsFilter(source);
    }
}
