package blog.smongs.config.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import blog.smongs.model.User;
import blog.smongs.repository.UserRepository;

@Service // Bean 등록
public class PrincipalDetailService implements UserDetailsService{
    
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        User principal = userRepository.findByUsername(username)
        .orElseThrow(()->{
            return new UsernameNotFoundException("해당 사용자를 찾을 수 없습니다.");
        });
        return new PrincipalDetail(principal); // 시큐리티 세션에 유저 정보가 저장됨
    }
}
