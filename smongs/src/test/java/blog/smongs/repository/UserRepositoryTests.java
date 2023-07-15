package blog.smongs.repository;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import blog.smongs.model.RoleType;
import blog.smongs.model.User;

@SpringBootTest
public class UserRepositoryTests {
    
    @Autowired
    private UserRepository userRepository;

    @Test
    public void userInsert(){
        IntStream.rangeClosed(1, 3).forEach(i -> {
            User user = User.builder()
                    .username("USER"+i)
                    .password("1111")
                    .email("user"+i + "@aaa.com")
                    .role(RoleType.USER)
                    .build();

            userRepository.save(user);
        });
    }
}
