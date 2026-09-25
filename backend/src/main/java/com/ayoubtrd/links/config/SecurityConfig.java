package com.ayoubtrd.links.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutFilter;

import com.ayoubtrd.links.auth.JwtService;
import com.ayoubtrd.links.auth.JwtAuthFilter;
import com.ayoubtrd.links.auth.JwtAuthProvider;
import com.ayoubtrd.links.auth.MyUserDetailsService;
import com.ayoubtrd.links.repositories.UserRepository;

@EnableWebSecurity
@Configuration
public class SecurityConfig {
    
    @Bean
    public UserDetailsService myUserDetailsService(UserRepository userRepository) {
        return new MyUserDetailsService(userRepository);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, AuthenticationManager authenticationManager) throws Exception {
        http
                .httpBasic(HttpBasicConfigurer::disable)
                .csrf(CsrfConfigurer::disable)
                .cors(Customizer.withDefaults())
                .formLogin(FormLoginConfigurer::disable)
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers("/users/login").permitAll()
                        .requestMatchers("/users/register").permitAll()
                        .anyRequest().authenticated());

        http.addFilterAfter(new JwtAuthFilter(authenticationManager), LogoutFilter.class);

        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean 
    AuthenticationManager authManager(JwtService jwtService, UserDetailsService userDetailsService) {
        return new ProviderManager(new DaoAuthenticationProvider(userDetailsService),
                new JwtAuthProvider(jwtService, userDetailsService));
    }
}
