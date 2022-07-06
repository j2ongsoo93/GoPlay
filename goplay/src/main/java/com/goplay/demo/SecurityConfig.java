package com.goplay.demo;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		
		//아래의 mavMAtchers에 따라 승인
		http.authorizeRequests() 
		
		// 해당 서비스는 모두 사용 가능 (로그인 필요 X)
		.mvcMatchers("/", "/hello","/all/**", "/join").permitAll();
		
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
}
