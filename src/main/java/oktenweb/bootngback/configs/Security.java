package oktenweb.bootngback.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
public class Security extends WebSecurityConfigurerAdapter {


        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.inMemoryAuthentication().withUser("admin").password("{noop}admin").roles("ADMIN");
            auth.inMemoryAuthentication().withUser("user").password("{noop}user").roles("USER");
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                    .cors().configurationSource(corsConfigurationSource())
                    .and()
                    .csrf()
                    .disable()
                    .authorizeRequests()
                    .antMatchers("/").permitAll() // by default method get
                    .antMatchers(HttpMethod.POST, "/login").permitAll()
                    .antMatchers("/get").authenticated()
                    //.anyRequest().authenticated()
                    .and()
                    // We filter the api/login requests
                    .addFilterBefore(new RequestProcessingJWTFilter(), UsernamePasswordAuthenticationFilter.class)
                    .addFilterBefore(new LoginFilter("/login", authenticationManager()), UsernamePasswordAuthenticationFilter.class);
        }

        @Bean
    CorsConfigurationSource corsConfigurationSource(){
            CorsConfiguration configuration = new CorsConfiguration();
            configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200")); // allows to ask our server from this url
            configuration.addAllowedHeader("*");
            configuration.setAllowedMethods(Arrays.asList(
                    HttpMethod.GET.name(),
                    HttpMethod.HEAD.name(),
                    HttpMethod.POST.name(),
                    HttpMethod.PUT.name(),
                    HttpMethod.DELETE.name()));
            configuration.addExposedHeader("Authorization");
            UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
            source.registerCorsConfiguration("/**", configuration);
           // source.registerCorsConfiguration("/saveEvent", configuration);
            return source;
        }


    }


