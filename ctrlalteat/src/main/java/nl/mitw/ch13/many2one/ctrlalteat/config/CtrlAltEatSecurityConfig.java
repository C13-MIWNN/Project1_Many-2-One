package nl.mitw.ch13.many2one.ctrlalteat.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Author Linda Munsterman
 * Configure security for this application
 **/

@Configuration
@EnableWebSecurity
public class CtrlAltEatSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/", "/recipe", "/recipe/detail/{recipeId}").permitAll()
                        .requestMatchers("/webjars/**", "/ctrlalteat.css", "/logo_ctrlAltEat.png",
                                "/recipe/image/**", "/search/**").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin((form) -> form.permitAll())
                .logout((logout) -> logout.logoutSuccessUrl("/").permitAll());

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}


