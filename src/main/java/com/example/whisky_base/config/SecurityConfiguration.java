package com.example.whisky_base.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.sql.DataSource;
import java.util.Arrays;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;


    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        final String sqlUserName = "select u.user_mail, u.user_pass, u.enable from user u where u.user_mail = ?";
        final String sqlAuthorities = "select ur.user_user_mail, ur.user_role from user_role ur where ur.user_user_mail = ?";
        auth.jdbcAuthentication().dataSource(dataSource).usersByUsernameQuery(sqlUserName)
                .authoritiesByUsernameQuery(sqlAuthorities).passwordEncoder(passwordEncoder());

        System.out.println(sqlAuthorities);

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers(HttpMethod.POST, "/user").permitAll()
                .antMatchers(HttpMethod.DELETE, "/user").hasRole("ADMIN")

                .antMatchers(HttpMethod.GET, "/distillery").authenticated()
                .antMatchers(HttpMethod.POST, "/distillery").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/distillery").hasRole("ADMIN")

                .antMatchers(HttpMethod.GET,"/whisky").authenticated()
                .antMatchers(HttpMethod.POST,"/whisky").authenticated()
                .antMatchers(HttpMethod.DELETE,"/whisky").authenticated()

                .antMatchers(HttpMethod.GET,"/storage").authenticated()
                .antMatchers(HttpMethod.POST,"/storage").authenticated()
                .antMatchers(HttpMethod.DELETE,"/storage").authenticated()
                .antMatchers(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, )


                .anyRequest().permitAll()
                .and().formLogin().permitAll()
                .and().logout().permitAll()
                .and().csrf().disable()
//                .and()
//                .exceptionHandling()
//                .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
        ;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    CorsConfigurationSource corsConfigurationSource()
//    {
//        CorsConfiguration configuration = new CorsConfiguration();
//        configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
//        configuration.setAllowedMethods(Arrays.asList("GET","POST", "DELETE"));
//        configuration.addAllowedHeader("Access-Control-Allowed-Header");
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", configuration);
//        return source;
//    }

}
