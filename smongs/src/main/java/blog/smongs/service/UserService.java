package blog.smongs.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import blog.smongs.model.User;
import blog.smongs.repository.UserRepository;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void signUp(User user){
        userRepository.save(user);
    }
}
