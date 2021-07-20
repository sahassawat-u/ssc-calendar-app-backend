package ssc.project.backend.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import ssc.project.backend.SimpleResponse;
import ssc.project.backend.auth.OurUserDetailsService;
import ssc.project.backend.util.AjaxUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private OurUserDetailsService ourUserDetailsService;

	@Bean
	public PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// configuring security for REST backend APIs because we will not login using from login anymore
		// disable csrf, normally you shouldn't
		http.csrf().disable();
		// permit root and /api/login and /api/logout
		http.authorizeRequests()
				.antMatchers("/","/api/login","/api/whoami","/api/register").permitAll();
		// permit all OPTIONS requests
		http.authorizeRequests()
				.antMatchers(HttpMethod.OPTIONS, "/**").permitAll();
		// handle error output as JSON for unauthorized access
		http.exceptionHandling()
				.authenticationEntryPoint(new JsonHttp403ForbiddenEntryPoint());

		// set every other path to require authentication
		http.authorizeRequests().antMatchers("/**").authenticated();
	}

	@Override
	public UserDetailsService userDetailsService() {
		return ourUserDetailsService;
	}

	class JsonHttp403ForbiddenEntryPoint implements AuthenticationEntryPoint {

		@Override
		public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {

			String ajaxJson = AjaxUtils.convertToString(
					SimpleResponse.builder()
							.success(false)
							.message("Forbidden")
							.build()
			);
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json");
			response.getWriter().println(ajaxJson);
		}
	}
}
