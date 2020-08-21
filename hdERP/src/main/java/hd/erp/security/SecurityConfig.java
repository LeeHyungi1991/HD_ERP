package hd.erp.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import hd.erp.service.EmployeeService;

@Configuration
@EnableWebSecurity
//@AllArgsConstructor ·Òº½ ¤Ð
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private EmployeeService employeeservice;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(employeeservice).passwordEncoder(passwordEncoder());
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		//web.ignoring().antMatchers("/css/**","/assets/**","/img/**","/js/**");
				//.antMatchers("/**").anyRequest();
				//.requestMatchers(PathRequest.toStaticResources().atCommonLocations());
		//web.ignoring().antMatchers("/**");
		web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/admin.**").hasRole("ADMIN")
				.antMatchers("/member.**").hasAnyRole("ADMIN","MEMBER")
				.antMatchers("/user.**").hasAnyRole("USER","ADMIN","MEMBER")
				//.antMatchers("/**").permitAll()
			.and()
				.formLogin()
				.loginPage("/login")
				.defaultSuccessUrl("/user.index")
			.and()
				.logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/user/logout"))
				.logoutSuccessUrl("/login")
				.invalidateHttpSession(true)
			.and()
				.csrf()
				.ignoringAntMatchers("/user.index")
				.ignoringAntMatchers("/chat**")
				.ignoringAntMatchers("/ws","/topic**","/app**")
			.and()
			.exceptionHandling().accessDeniedPage("/denied");
	}
	
	
}
