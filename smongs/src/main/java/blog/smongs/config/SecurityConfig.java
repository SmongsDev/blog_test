package blog.smongs.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration // 빈등록 (IoC 관리)
@EnableWebSecurity // 시큐리티 필터가 등록됨
@EnableGlobalMethodSecurity(prePostEnabled = true) // 특정 주소로 접근하면 권한 및 인증을 미리 체크
public class SecurityConfig{

	@Bean // IoC (=Spring이 해당 매서드 관리해줌)
	public BCryptPasswordEncoder encoderPWD(){
		return new BCryptPasswordEncoder();
	}

    @Bean
	public SecurityFilterChain configure(HttpSecurity http) throws Exception{
		http
		  .csrf().disable() // csrf 토큰 비활성화 테스트시 걸어두면 편함
		  .authorizeHttpRequests()
		    .antMatchers("/","/auth/**","/js/**","/css/**","/images/**","/WEB-INF/**")
			.permitAll()
			.anyRequest()
			.authenticated()
		  .and()
		    .formLogin()
		    .loginPage("/auth/loginForm")
			.permitAll();
		
        return http.build();
    }
}
