package com.eazybytes.security;

import com.eazybytes.auth.JwtAuthenticationFilter;
import com.eazybytes.exceptionhandling.CustomAccessDeniedHandler;
import com.eazybytes.exceptionhandling.CustomJwtAuthenticationEntryPoint;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final CustomAuthenticationProvider customAuthenticationProvider;

    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.sessionManagement(hssmc -> hssmc
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.securityContext(securityContextConfig->securityContextConfig.requireExplicitSave(false));

        http.authorizeHttpRequests((requests) -> requests
                .requestMatchers("/account/**").hasAnyRole("USER","ADMIN")
                .requestMatchers("/myBalance").hasRole("USER")
                .requestMatchers("/myCards").hasRole("USER")
                .requestMatchers("/myLoans").hasRole("USER")
                .requestMatchers("/my-customer").hasRole("USER")
                .requestMatchers("/contact","/notices","/error","/register", "/login").permitAll()
                .requestMatchers("/v2/**","/v3/**","swagger-resources/**","/configuration/**","/swagger-ui/**","/webjars/**","/swagger-ui.html").permitAll());

        http.requiresChannel(crmr->crmr.anyRequest().requiresInsecure());

        http.formLogin(AbstractHttpConfigurer::disable);
        http.csrf(AbstractHttpConfigurer::disable);


        http.exceptionHandling(hsehc ->
                hsehc
                        .authenticationEntryPoint(new CustomJwtAuthenticationEntryPoint())
                        .accessDeniedHandler(new CustomAccessDeniedHandler()));

        http.cors(corsConfig->corsConfig.configurationSource(corsConfigurationSource()));


        http.authenticationProvider(customAuthenticationProvider);

        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }



    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedOrigins(List.of("http://localhost:4200"));
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setAllowedHeaders(List.of("*"));
        corsConfiguration.setAllowedMethods(List.of("GET","POST","UPDATE","DELETE","OPTIONS"));
        corsConfiguration.setMaxAge(3600L);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**",corsConfiguration);
        return source;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }


    /*
            http.httpBasic(hshbc ->
                hshbc.authenticationEntryPoint(new CustomBasicAuthenticationEntryPoint()));

     */

    /*

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

     */

    /*
    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.withUsername("user").password("{noop}12345").authorities("read").build();
        UserDetails admin = User.withUsername("admin").password("{bcrypt}$2a$12$Heeryuv6FW5lenmNxFKeOO9YYtT/ZKzHII5jBT57xI1/iysazJU3S").authorities("admin").build();
        return new InMemoryUserDetailsManager(user, admin);

    }

     */

    /*
    @Bean
    public UserDetailsService userDetailsService(DataSource dataSource) {
        return new JdbcUserDetailsManager(dataSource);

    }

     */


    /*
    @Bean
    public CompromisedPasswordChecker compromisedPasswordChecker() {
        return new HaveIBeenPwnedRestApiPasswordChecker();
    }

     */

    /*

            http.csrf(csrfConfig->csrfConfig
                .ignoringRequestMatchers("/contact", "/register")
                .csrfTokenRequestHandler(new CsrfTokenRequestAttributeHandler())
                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()));
        http.addFilterAfter(csrfCookieFilter, BasicAuthenticationFilter.class)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(requestValidationBeforeFilter, BasicAuthenticationFilter.class)
                .addFilterAfter(authoritiesLoggingAfterFilter, BasicAuthenticationFilter.class)
                .addFilterAt(authoritiesLoggingAtFilter, BasicAuthenticationFilter.class);

     */


}
