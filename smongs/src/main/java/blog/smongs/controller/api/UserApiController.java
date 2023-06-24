package blog.smongs.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import blog.smongs.dto.ResponseDto;
import blog.smongs.model.RoleType;
import blog.smongs.model.User;
import blog.smongs.service.UserService;

@RestController
public class UserApiController {
    
    @Autowired
    private UserService userService;

    @PostMapping("/api/user")
    public ResponseDto<Integer> save(@RequestBody User user){
        System.out.println("호출됨");
        user.setRole(RoleType.USER);
        userService.signUp(user);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }
}
