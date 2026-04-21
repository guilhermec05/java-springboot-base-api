package br.com.cabral.basic_api.configuration.security;


import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.*;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {
    private final JwtService jwtService;
    private final SecurityProperties securityProperties;

    public SecurityConfig(JwtService jwtService, SecurityProperties securityProperties) {
        this.jwtService = jwtService;
        this.securityProperties = securityProperties;
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http){

        if (!securityProperties.isEnabled()){
            return  http
                    .csrf(csrf -> csrf.disable())
                    .authorizeHttpRequests(auth -> auth.anyRequest().permitAll()

                            )
                    .build();
        }


        return http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth ->
                        auth.requestMatchers( "/swagger-ui/**","/docs/**","/swagger-ui.html","/auth/**", "/v3/api-docs/**","/api-docs/**")
                                .permitAll()
                                .anyRequest()
                                .authenticated()
                ).addFilterBefore(new JwtAuthenticationFilter(jwtService,securityProperties), UsernamePasswordAuthenticationFilter.class)
                .build();

    }
}

