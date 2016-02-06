package com.test.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
/**
 * Reference : http://www.mkyong.com/spring-security/spring-security-hello-world-annotation-example/
 * http://howtodoinjava.com/2013/04/16/login-form-based-spring-3-security-example/
 *
 */
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth)
			throws Exception {
		auth.inMemoryAuthentication().withUser("user").password("user")
				.roles("USER");
		auth.inMemoryAuthentication().withUser("admin").password("admin")
				.roles("ADMIN");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		/*
		 * http.authorizeRequests().antMatchers("/admin/**")
		 * .access("hasRole('ROLE_ADMIN')").antMatchers("/dba/**")
		 * .access("hasRole('ROLE_ADMIN') or hasRole('ROLE_DBA')").and()
		 * .formLogin();
		 */
		http.authorizeRequests()
				.antMatchers("/admin/**")
				.access("hasRole('ROLE_USER')")
				.and()
				.formLogin()
				.successHandler(savedRequestAwareAuthenticationSuccessHandler())
				.loginPage("/login").failureUrl("/login?error")
				.loginProcessingUrl("/auth/login_check")
				.usernameParameter("username").passwordParameter("password")
				.and().logout().logoutSuccessUrl("/login?logout")
				.deleteCookies("JSESSIONID").and().csrf().and().rememberMe()
				.key("remember-me")
				// .rememberMe().ttotokenRepository(tokenRepository)(persistentTokenRepository())
				.tokenValiditySeconds(1209600);

	}

	@Bean
	public SavedRequestAwareAuthenticationSuccessHandler savedRequestAwareAuthenticationSuccessHandler() {
		SavedRequestAwareAuthenticationSuccessHandler auth = new SavedRequestAwareAuthenticationSuccessHandler();
		auth.setTargetUrlParameter("targetUrl");
		return auth;
	}
}