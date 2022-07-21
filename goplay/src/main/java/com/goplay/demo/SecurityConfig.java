package com.goplay.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.goplay.demo.service.MemberService;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	MemberService ms;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		http.headers().frameOptions().disable();
		http.formLogin()
		.loginPage("/login")  //로그인 페이지
		.defaultSuccessUrl("/matchBoard")//로그인 성공 url
		.loginProcessingUrl("/login/action")
		.usernameParameter("id")
		.passwordParameter("pwd")
		.failureUrl("/login/error")
		.and()
		.logout()
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		.logoutSuccessUrl("/login")
		.and()
		.sessionManagement()
		.maximumSessions(1)
		.maxSessionsPreventsLogin(true);

		
		//아래의 mavMAtchers에 따라 승인

		// 해당 서비스는 모두 사용 가능 (로그인 필요 X)
//		.mvcMatchers("/", "/hello","/all/**", "/join").permitAll();
		
//		// 해당 서비스는 admin만 사용 가능
//		.mvcMatchers("/admin/**").hasRole("admin")
//		
//		// 해당 서비스는 로그인 하면 사용 가능
//		.anyRequest().authenticated();
		
		//로그인
		//로그인 폼 명 /login
//		http.formLogin().loginPage("/login")
//									.permitAll()
//								.defaultSuccessUrl("/service1");

		//로그아웃
		//invalidateHttpSession = 로그 아웃 후 세션 파기하는 속성
//		http.logout()
//					.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//					.invalidateHttpSession(true)
//					.logoutSuccessUrl("/login");
//		
		http.httpBasic();
	}
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(ms)
				.passwordEncoder(passwordEncoder());
	}
	
}
