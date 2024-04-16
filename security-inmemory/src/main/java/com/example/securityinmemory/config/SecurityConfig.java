package com.example.securityinmemory.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Collection;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain configure(HttpSecurity httpSecurity) {
        try {
            return  httpSecurity.cors(AbstractHttpConfigurer::disable)
                    .authorizeHttpRequests(new Customizer<AuthorizeHttpRequestsConfigurer<org.springframework.security.config.annotation.web.builders.HttpSecurity>.AuthorizationManagerRequestMatcherRegistry>() {
                        @Override
                        public void customize(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry authorizationManagerRequestMatcherRegistry) {
                            authorizationManagerRequestMatcherRegistry.requestMatchers("/products/**").hasRole("ADMIN");
                        }
                    })
                    .authenticationProvider(ap())
                    .formLogin(
                            Customizer.withDefaults()
//                            page  -> page.defaultSuccessUrl("/products").successForwardUrl("/products")
                    )
                    .logout(page -> page.logoutUrl("/logout")
                            .logoutSuccessUrl("/login?logout")
                            .invalidateHttpSession(true)
                            .deleteCookies("JSESSIONID"))
                    .build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

//    Customizer<HttpSecurity> customizer = new Customizer<HttpSecurity>() {
//        @Override
//        public void customize(HttpSecurity httpSecurity) {
//            try {
//                httpSecurity.authorizeHttpRequests(a ->).anyRequest().authenticated();
//            } catch (Exception e) {
//                throw new RuntimeException(e);
//            }
//        }
//    }

//    AuthorizeHttpRequestsConfigurer.AuthorizationManagerRequestMatcherRegistry =

    @Bean
    public UserDetailsService auth() {

        User.UserBuilder users = User.withDefaultPasswordEncoder();
        UserDetails user = users
                .username("user")
                .password("password")
                .roles("USER")
                .build();
        UserDetails admin = users
                .username("admin")
                .password("password")
                .roles("USER", "ADMIN")
                .build();
        return new InMemoryUserDetailsManager(user, admin);
    }

    @Bean
    public DaoAuthenticationProvider ap() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(auth());
        return authProvider;
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public void add(AuthenticationManagerBuilder authenticationManagerBuilder) {
//        authenticationManagerBuilder.authenticationProvider(ap());
//    }


}
