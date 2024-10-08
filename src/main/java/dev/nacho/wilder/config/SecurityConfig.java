package dev.nacho.wilder.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import dev.nacho.wilder.facades.encryptations.Base64Encoder;
import dev.nacho.wilder.services.JpaUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    
    @Value("${api-endpoint}")
        private String endpoint;

        private MyBasicAuthenticationEntryPoint myBasicAuthenticationEntryPoint;

        private JpaUserDetailsService jpaUserDetailsService;

        public SecurityConfig(JpaUserDetailsService jpaUserDetailsService, MyBasicAuthenticationEntryPoint basicEntryPoint) {
                this.jpaUserDetailsService = jpaUserDetailsService;
                this.myBasicAuthenticationEntryPoint = basicEntryPoint;
        }
        
        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

                http
                        .cors(cors -> cors.configurationSource(corsConfiguration()))
                        .csrf(csrf -> csrf.disable())
                        .formLogin(form -> form.disable())
                        .logout(out -> out
                                .logoutUrl(endpoint + "/logout")
                                .logoutSuccessHandler((new HttpStatusReturningLogoutSuccessHandler(HttpStatus.OK)))
                                .deleteCookies("JSESSIONID"))
                        .authorizeHttpRequests(auth -> auth
                                .requestMatchers(HttpMethod.POST, endpoint + "/videogames").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.DELETE, endpoint + "/videogames/{id}").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.PUT, endpoint + "/videogames/{id}").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.GET, endpoint + "/videogames/{id}").permitAll()
                                .requestMatchers(AntPathRequestMatcher.antMatcher("/h2-console/**")).permitAll()
                                .requestMatchers(HttpMethod.POST, endpoint + "/register").permitAll()
                                .requestMatchers(HttpMethod.GET, endpoint + "/login").permitAll()
                                .requestMatchers(HttpMethod.GET, endpoint + "/videogames").permitAll()
                                .requestMatchers(HttpMethod.GET, endpoint + "/genres").permitAll()
                                .anyRequest().authenticated())
                        .userDetailsService(jpaUserDetailsService)
                        .httpBasic(basic -> basic.authenticationEntryPoint(myBasicAuthenticationEntryPoint))
                        .sessionManagement(session -> session
                                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED));

                http.headers(header -> header.frameOptions(frame -> frame.sameOrigin()));

                return http.build();
        }

        @Bean
        CorsConfigurationSource corsConfiguration() {
                CorsConfiguration configuration = new CorsConfiguration();
                configuration.setAllowCredentials(true);
                configuration.setAllowedOrigins(Arrays.asList("http://localhost:5173"));
                configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
                configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));

                UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
                source.registerCorsConfiguration("/**", configuration);
                return source;
        }

         @Bean
        PasswordEncoder passwordEncoder() {
                return new BCryptPasswordEncoder();
        }

        @Bean
        Base64Encoder base64Encoder() {
                return new Base64Encoder();
        }
}
