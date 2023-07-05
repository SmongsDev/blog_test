package blog.smongs.controller.api;

// import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import blog.smongs.dto.ResponseDto;
import blog.smongs.model.User;
import blog.smongs.service.UserService;

@RestController
public class UserApiController {
    
    @Autowired
    private UserService userService;

    @PostMapping("/auth/joinProc")
    public ResponseDto<Integer> save(@RequestBody User user){
        System.out.println("호출됨");
        userService.signUp(user);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }
}
