package com.catbaseboot.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.catbaseboot.services.UserService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class MySecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	UserService userService;
	
	@Autowired
	UserDetailsService userDetailsService;
	
	/*@Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
        auth.authenticationProvider(authenticationProvider());
    }*/
	
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	    auth.userDetailsService(userDetailsService)
	        .passwordEncoder(new BCryptPasswordEncoder());
	    
    	/*auth.userDetailsService(new UserDetailsService() {
			@Override
			public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
				return userService.getUserByUsername(username);
			}
		});*/
    	/*auth.inMemoryAuthentication()
                .withUser("user").password("password").roles("USER")
                .and()
        		.withUser("superuser").password("password").roles("USER", "ADMIN");*/
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
	@Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
        		.antMatchers("/webjars/**")
                .antMatchers("/js/**")
                .antMatchers("/css/**");

    }
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/", "/about", "/login", "/logout", "/users/**").permitAll()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/cats/**").hasAnyRole("USER", "ADMIN")
                .anyRequest().authenticated()
            .and()
            .formLogin()
            	.loginPage("/login")
            	.failureUrl("/login?error")
                .defaultSuccessUrl("/")
            .and()
                .logout()
                .logoutUrl("/index")
        		.deleteCookies("remember-me")
        	.and()
        		.rememberMe();
    }
}