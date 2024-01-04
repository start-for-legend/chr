package com.chr.tree.global.security.config;

import com.chr.tree.global.filter.JwtRequestFilter;
import com.chr.tree.global.filter.LogRequestFilter;
import com.chr.tree.global.security.handler.CustomAccessDeniedHandler;
import com.chr.tree.global.security.handler.CustomAuthenticationEntryPointHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;


@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtRequestFilter jwtRequestFilter;
    private final LogRequestFilter logRequestFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception {
        http
                .authorizeHttpRequests()
                .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll();
        http
                .rememberMe().disable()
                .cors().configurationSource(corsConfigurationSource()).and()
                .csrf().disable();
        http
                .exceptionHandling()
                    .accessDeniedHandler(new CustomAccessDeniedHandler())
                .authenticationEntryPoint(new CustomAuthenticationEntryPointHandler());
        http
                .httpBasic().disable()
                .formLogin().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeHttpRequests()
                .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()

                .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()
                .requestMatchers(HttpMethod.POST, "/v1/auth").permitAll()
                .requestMatchers(HttpMethod.POST, "/v1/auth/new").permitAll()
                .requestMatchers(HttpMethod.POST, "/v1/email").permitAll()
                .requestMatchers(HttpMethod.PATCH, "/v1/email").permitAll()
                .requestMatchers(HttpMethod.GET, "/v1/auth/**").permitAll()
                .requestMatchers(HttpMethod.PATCH, "/v1/auth").authenticated()
                .requestMatchers(HttpMethod.DELETE, "/v1/auth").authenticated()
                .requestMatchers(HttpMethod.POST, "/v1/comment/**").authenticated()
                .requestMatchers(HttpMethod.GET, "/v1/comment/**").authenticated()
                .requestMatchers(HttpMethod.GET, "/v1/commentList/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/v1/link").authenticated()
                .requestMatchers("/static/**").permitAll()

                .anyRequest().denyAll();
        http
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(logRequestFilter, JwtRequestFilter.class);

        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.addAllowedOriginPattern("*");
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
