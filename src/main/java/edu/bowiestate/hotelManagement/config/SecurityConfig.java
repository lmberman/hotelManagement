package edu.bowiestate.hotelManagement.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.DelegatingAuthenticationEntryPoint;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;

    @Override
    public void configure(WebSecurity webSecurity) throws Exception {
        webSecurity.ignoring().antMatchers("/h2-console/**");
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.sessionManagement().sessionAuthenticationErrorUrl("/login")
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.NEVER)
                .and()
                .sessionManagement().invalidSessionUrl("/login")
                .and()
                .sessionManagement().maximumSessions(2);
        httpSecurity
                .authorizeRequests()
                .antMatchers("/home").authenticated()
                .and()
                .exceptionHandling().authenticationEntryPoint(new Http403ForbiddenEntryPoint())
                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/home")
                .permitAll()
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login")
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true);

    }

    @Bean
    public HttpSessionEventPublisher httpSessionEventPublisher() {
        return new HttpSessionEventPublisher();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        //default schema to create users
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("select employee_id as username,password,true as enabled from employee where employee_id = ?")
                .authoritiesByUsernameQuery("select em.employee_id as username, em.role from employee em where em.employee_id = ?")
                .rolePrefix("ROLE_")
                .passwordEncoder(getPasswordEncoder());
    }

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
