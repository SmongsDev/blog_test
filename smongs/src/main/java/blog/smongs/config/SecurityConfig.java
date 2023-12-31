package blog.smongs.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
// import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import blog.smongs.config.auth.PrincipalDetailService;

@Configuration // 빈등록 (IoC 관리)
@EnableWebSecurity // 시큐리티 필터가 등록됨
@EnableGlobalMethodSecurity(prePostEnabled = true) // 특정 주소로 접근하면 권한 및 인증을 미리 체크
public class SecurityConfig{

	@Autowired
	private PrincipalDetailService principalDetailService;

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

	@Bean // IoC (=Spring이 해당 매서드 관리해줌)
	public BCryptPasswordEncoder encoderPWD(){
		return new BCryptPasswordEncoder();
	}

	public void filterChain(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(principalDetailService).passwordEncoder(encoderPWD());
	}

    @Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		http
		  .csrf().disable() // csrf 토큰 비활성화 테스트시 걸어두면 편함
		  .authorizeHttpRequests()
		    .antMatchers("/","/auth/**","/js/**","/css/**","/images/**","/WEB-INF/**", "/dummy/**")
			.permitAll()
			.anyRequest()
			.authenticated()
		  .and()
		    .formLogin()
		    .loginPage("/auth/loginForm")
			.loginProcessingUrl("/auth/loginProc")
			.defaultSuccessUrl("/");
			// .permitAll();
		
        return http.build();
    }
}
