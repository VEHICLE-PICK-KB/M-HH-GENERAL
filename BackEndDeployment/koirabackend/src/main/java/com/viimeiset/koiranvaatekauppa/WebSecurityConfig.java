package com.viimeiset.koiranvaatekauppa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.viimeiset.koiranvaatekauppa.web.UserDetailServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Set;

@Configuration
@EnableMethodSecurity(securedEnabled = true)
public class WebSecurityConfig {
	//cors-säännöt päivitetty
	//kaikki uudelleenohjaukset päivitetty vastaamaan serverlinkkejä
	//SSL-konfiguraatio -> application.properties
	//SSL-avaimet SHA-512, PKCS12, springboot.p12
	//HTTPS-säännöt lisätty
	

    @Autowired
    private UserDetailServiceImpl userDetailsService;

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("https://ohjelmistoprojekti-1-viimeiset.github.io", "http://localhost:5173"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http.requiresChannel(channel ->
                channel.anyRequest().requiresSecure())
            .authorizeHttpRequests(authorize ->
                authorize
                    .requestMatchers("/css/**").permitAll()
                    .requestMatchers("/").hasRole("ADMIN")
                    .requestMatchers("/signup").hasRole("ADMIN")
                    .requestMatchers("/saveuser").hasRole("ADMIN")
                    .requestMatchers("/tuotelista").hasRole("ADMIN")
                    .requestMatchers("/valmistajalista").hasRole("ADMIN")
                    .requestMatchers("/asiakaslista").hasRole("ADMIN")
                    .requestMatchers("/index").hasRole("ADMIN")
                    .requestMatchers("/h2-console/**").hasRole("ADMIN")
                    .requestMatchers("/lisaa/**").hasRole("ADMIN")
                    .requestMatchers("/muokkaa/**").hasRole("ADMIN")
                    .requestMatchers("/delete/{id}").hasRole("ADMIN")
                    .requestMatchers("/deleteValmistaja/{id}").hasRole("ADMIN")
                    .requestMatchers("/poistaasiakas/{userId}").hasRole("ADMIN")
                    .requestMatchers("/muokkaaasiakasta/{userId}").hasRole("ADMIN")
                    .requestMatchers("/api/**").permitAll()
                    .anyRequest().authenticated()
            )
            .cors().configurationSource(corsConfigurationSource())
            .and()
            .headers(headers -> headers.frameOptions(frameoptions -> frameoptions.disable()))
            .formLogin(formlogin -> formlogin
                .loginPage("/login")
                .successHandler(successHandler())
                .permitAll()
            )
            .logout(logout -> logout
                .logoutSuccessUrl("/login")
                .permitAll()
            )
            .csrf(csrf -> csrf.disable());

        return http.build();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Bean
    public AuthenticationSuccessHandler successHandler() {
        return new SimpleUrlAuthenticationSuccessHandler() {
            @Override
            public void onAuthenticationSuccess(
                HttpServletRequest request,
                HttpServletResponse response,
                Authentication authentication
            ) throws IOException, ServletException {
                Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
                if (roles.contains("USER")) {
                    response.sendRedirect("https://ohjelmistoprojekti-1-viimeiset.github.io/koirafrontend/");
                } else {
                    super.onAuthenticationSuccess(request, response, authentication);
                }
            }
        };
    }
}