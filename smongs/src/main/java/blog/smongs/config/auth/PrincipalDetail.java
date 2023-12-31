package blog.smongs.config.auth;

import blog.smongs.model.User;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Getter
public class PrincipalDetail implements UserDetails{
    private User user;

    public PrincipalDetail(User user){
        this.user = user;
    }

    @Override
    public String getPassword(){
        return user.getPassword();
    }

    @Override
    public String getUsername(){
        return user.getUsername();
    }

    // 계정이 만료되지 않았는지 리턴 (true: 만료 안됨)
    @Override
    public boolean isAccountNonExpired(){
        return true;
    }

    // 계정이 잠겨있지 않는지 리턴 (true: 잠기지 않음)
    @Override
    public boolean isAccountNonLocked(){
        return true;
    }

    // 비밀번호가 만료되지 않았는지 리턴 (true: 만료 안됨)
    @Override
    public boolean isCredentialsNonExpired(){
        return true;
    }

    // 계정 활성화(사용가능)인지 리턴 (true: 활성화)
    @Override
    public boolean isEnabled(){
        return true;
    }

    // 계정이 갖고 있는 권한 목록 리턴 (권한이 여러개 있을 수 있어서 루프를 돌아야 하는데 일단 한개만)
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){
        Collection<GrantedAuthority> collectors = new ArrayList<>();        
        collectors.add(()->{ return "ROLE_" + user.getRole(); });

        return collectors;
    }
}
