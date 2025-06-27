package com.api.devapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

/**
 * Classe responsável por gerenciar as configurações de segurança.
 *
 * @author Gabriel Mathias
 */
@Configuration
public class SecurityConfig {

    /**
     * Filtro para desativar CSRF (apenas para os testes).
     *
     * @param http
     * @return
     * @throws Exception
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().authenticated() // Exige autenticação para qualquer requisição
                )
                .formLogin(withDefaults()) // Habilita o login por formulário (opcional)
                .httpBasic(withDefaults()) // ou login via HTTP Basic
                .build();
    }

    /**
     * Método básico de acesso a API via Basic Auth.
     *
     * @return
     */
    @Bean
    public UserDetailsService userDetailsService() {
        var user = User.withUsername("admin")
                .password(passwordEncoder().encode("teste@123"))
                .build();

        return new InMemoryUserDetailsManager(user);
    }

    /**
     * Sala e verifica senhas de forma segura.
     *
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}