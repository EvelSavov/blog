package com.savov.blog.service;

import com.savov.blog.domain.entities.Post;
import com.savov.blog.domain.entities.Role;
import com.savov.blog.domain.entities.User;
import com.savov.blog.domain.model.binding.UserLoginBindingModel;
import com.savov.blog.repository.RoleRepository;
import com.savov.blog.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PostService postService;
    private final RoleRepository roleRepository;



    public UserServiceImpl(UserRepository userRepository, PostService postService, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.postService = postService;
        this.roleRepository = roleRepository;
    }

    @Override
    public User getUserByUsername(String username) {
        User User = userRepository.findByUsername(username);

        return User;
    }

    @Override
    public List<Post> getPostByUsername(String username) {
        User user =  userRepository.findByUsername(username);

        return postService.getPostByUserId(user.getId());
    }

    @Override
    public User addUser(User user) {
        user.setRole(roleRepository.findByName("User"));
        return userRepository.saveAndFlush(user);
    }

    @Override
    public User updateUser(String username, User user) {
        User user1 = userRepository.findByUsername(username);
        user1.setFirstName(user.getFirstName());
        user1.setLastName(user.getLastName());
        user1.setUsername(user.getUsername());
        user1.setAddress(user.getAddress());
        user1.setEmail(user.getEmail());
        user1.setPassword(user.getPassword());
        return  userRepository.saveAndFlush(user1);
    }

    @Override
    public User deleteUser(String username) {
        User user = userRepository.findByUsername(username);
        userRepository.deleteByUsername(username);
        return user;
    }

    @Override
    public User giveAdminToUser(String username) {
        User user = userRepository.findByUsername(username);
        Role role = roleRepository.findByName("Admin");
        user.setRole(role);
        return userRepository.saveAndFlush(user);
    }

    @Override
    public User takeAdminToUser(String username) {
        User user = userRepository.findByUsername(username);
        Role role = roleRepository.findByName("User");
        user.setRole(role);
        return userRepository.saveAndFlush(user);
    }

    @Override
    public User loginUser(UserLoginBindingModel userLoginBindingModel) {
        User user =  userRepository.findByUsername(userLoginBindingModel.getUsername());
        if(user!=null&& user.getPassword().equals(userLoginBindingModel.getPassword()))
        {
            return user;
        }else return null;
    }
}
