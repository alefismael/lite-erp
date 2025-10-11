package com.ketsoft.liteerp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.ketsoft.liteerp.auth.UserAuthenticationFilter;
import com.ketsoft.liteerp.constants.PathAuthConstant;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
    private UserAuthenticationFilter userAuthenticationFilter;

    @Bean
    protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .cors(cors -> {})
            .csrf(csrf -> csrf.disable())
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(PathAuthConstant.ENDPOINTS_WITH_AUTHENTICATION_NOT_REQUIRED).permitAll()
                .requestMatchers(PathAuthConstant.ENDPOINTS_WITH_AUTHENTICATION_REQUIRED).authenticated()
                .anyRequest().denyAll()
            )
            .addFilterBefore(userAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }


    @Bean
    protected AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
	
	@Bean BCryptPasswordEncoder bCryptPasswordEncoder() {
	    return new BCryptPasswordEncoder(); 
	}
}
