package blog.smongs.controller;

import java.util.List;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import blog.smongs.model.RoleType;
import blog.smongs.model.User;
import blog.smongs.repository.UserRepository;

@RestController
public class BlogController {

    @Autowired // 의존성 주입
    private UserRepository userRepository;

    @GetMapping("/userList")
    public List<User> list(){
        return userRepository.findAll();
    }

    @GetMapping("/user")
    public List<User> pageList(@PageableDefault(size=2, sort="id", direction=Sort.Direction.DESC) Pageable pageable){
        Page<User> pagingUser = userRepository.findAll(pageable);

        List<User> users = pagingUser.getContent();
        return users;
    }

    @GetMapping("/user/{id}")
    public User detail(@PathVariable int id){
        User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
            @Override
            public IllegalArgumentException get(){
                return new IllegalArgumentException("해당 유저는 없습니다.");
            }
        });

        return user;
    }

    @PostMapping("/join")
    public String join(User user){
        System.out.println("username : " + user.getUsername());
        System.out.println("password : " + user.getPassword());
        System.out.println("email : " + user.getEmail());

        user.setRole(RoleType.USER);
        userRepository.save(user);

        return "회원가입이 완료되었습니다.";
    }

    @DeleteMapping("/user/{id}")
    public String delete(@PathVariable int id){
        try{
            userRepository.deleteById(id);
        } catch(EmptyResultDataAccessException e){
            return "삭제에 실패하였습니다. 해당 id는 DB에 없습니다.";
        }
        return "삭제되었습니다." + id;
    }

    @Transactional
    @PutMapping("/user/{id}")
    public User updateUser(@PathVariable int id, @RequestBody User requestUser){
        System.out.println("id" + id);
        System.out.println("password" + requestUser.getPassword());
        System.out.println("email" + requestUser.getEmail());

        User user = userRepository.findById(id).orElseThrow(() -> {
            return new IllegalArgumentException("수정에 실패하였습니다.");
        });
        user.setPassword(requestUser.getPassword());
        user.setEmail(requestUser.getEmail());
        requestUser.setId(id);
        
        // userRepository.save(requestUser);
        return user;
    }
}
