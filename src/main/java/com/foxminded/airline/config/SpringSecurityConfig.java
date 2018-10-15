package com.foxminded.airline.config;

import com.foxminded.airline.utils.SuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private SuccessHandler successHandler;

    @Value("select login, password, enabled from userairline where login=?")
    private String usersQuery;

    @Value("select u.login, r.name from userairline u inner join role r on(u.role_id=r.role_id) where u.login=?")
    private String rolesQuery;

    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.
                jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery(usersQuery)
                .authoritiesByUsernameQuery(rolesQuery)
                .passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.
                authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/registration").permitAll()
                .antMatchers("/searchflight").permitAll()
                .antMatchers("/buyticket").permitAll()
                .antMatchers("/searchAirport").permitAll()
                .antMatchers("/userlogin").permitAll()
                .antMatchers("/resources/**").permitAll().anyRequest().permitAll()
                .antMatchers("/css/**").permitAll().anyRequest().permitAll()
                .antMatchers("/js/**").permitAll().anyRequest().permitAll()
                .antMatchers("/webjars/**").permitAll().anyRequest().permitAll()
                .antMatchers("/admin/**").hasAuthority("admin").anyRequest().authenticated()
                .antMatchers("/user/**").hasAuthority("user").anyRequest().authenticated()
                .antMatchers("/user").hasAuthority("user").anyRequest().authenticated()
                .and().csrf().disable()
                .formLogin()
                .successHandler(successHandler)
                .loginPage("/login").failureUrl("/login?error=true")
                .usernameParameter("login")
                .passwordParameter("password")
                .and().logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/").and().exceptionHandling()
                .accessDeniedPage("/403");
    }

    //                .antMatchers("/resources/**").permitAll()
//                .antMatchers("/static/**").permitAll()
//                .antMatchers("/resources/static/user/js/**").permitAll()
//                .antMatchers("/resources/static/js/**").permitAll()
//                .antMatchers("/resources/static/css/**").permitAll()

//                .antMatchers(HttpMethod.GET, "/css/**", "/js/**","/fonts/**","/**/favicon.ico", "/about","user/js/user/**").permitAll()
//                .antMatchers(HttpMethod.POST, "/css/**", "/js/**","/fonts/**","/**/favicon.ico", "/about","user/js/user/**").permitAll()

    //                .antMatchers("/css/**").permitAll()
//                .antMatchers("/css").permitAll()
//                .antMatchers("/js/**").permitAll()
//                .antMatchers("/webjars/**").permitAll()
    //                .antMatchers("/user/userlogin").hasAnyAuthority("user","admin")
//                .antMatchers("/user/js/user/**").hasAnyAuthority("user","admin")

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**","/webjars/**").anyRequest();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }
}