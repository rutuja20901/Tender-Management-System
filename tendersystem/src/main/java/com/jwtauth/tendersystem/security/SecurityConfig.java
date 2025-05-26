package com.jwtauth.tendersystem.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.jwtauth.tendersystem.service.LoginService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtFilter jwtFilter;

    @Autowired
    private LoginService loginService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(loginService).passwordEncoder(passwordEncoder());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/h2-console/**").antMatchers("/login");
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/auth/login").permitAll()
                .antMatchers("/tenders/bidding/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    /*
     * @Configuration
     * 
     * @EnableWebSecurity
     * public class SecurityConfig extends WebSecurityConfigurerAdapter {
     * 
     * @Autowired
     * private JwtFilter jwtFilter;
     * 
     * @Autowired
     * private UserDetailsService userDetailsService;
     * 
     * @Bean
     * public PasswordEncoder passwordEncoder() {
     * return new BCryptPasswordEncoder();
     * }
     * 
     * @Override
     * protected void configure(AuthenticationManagerBuilder auth) throws Exception
     * {
     * auth.userDetailsService(userDetailsService)
     * .passwordEncoder(passwordEncoder());
     * }
     * 
     * @Override
     * protected void configure(HttpSecurity http) throws Exception {
     * http.csrf().disable()
     * .authorizeRequests()
     * .antMatchers("/auth/register", "/auth/login").permitAll()
     * .antMatchers("/tenders/**").hasAnyRole("BIDDER", "APPROVER")
     * .anyRequest().authenticated()
     * .and()
     * .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
     * 
     * http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
     * }
     * 
     * @Bean
     * 
     * @Override
     * public AuthenticationManager authenticationManagerBean() throws Exception {
     * return super.authenticationManagerBean();
     * }
     * }
     * 
     * 
     */

}
