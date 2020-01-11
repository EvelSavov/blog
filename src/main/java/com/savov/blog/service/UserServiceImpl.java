package com.savov.blog.service;

import com.savov.blog.domain.entities.Post;
import com.savov.blog.domain.entities.Role;
import com.savov.blog.domain.entities.User;
import com.savov.blog.domain.model.binding.UserLoginBindingModel;
import com.savov.blog.domain.model.service.PostServiceModel;
import com.savov.blog.domain.model.service.UserServiceModel;
import com.savov.blog.repository.RoleRepository;
import com.savov.blog.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PostService postService;
    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserServiceImpl(UserRepository userRepository, PostService postService, RoleRepository roleRepository, ModelMapper modelMapper, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.postService = postService;
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public UserServiceModel getUserByUsername(String username) {
        User user = userRepository.findByUsername(username);

        return this.modelMapper.map(user,UserServiceModel.class);
    }

    @Override
    public List<PostServiceModel> getPostByUsername(String username) {
        User user =  userRepository.findByUsername(username);
        List<Post> posts = postService.getPostByUserId(user.getId()).stream().map(p -> this.modelMapper.map(p, Post.class)).collect(Collectors.toList());
        return posts.stream().map(p->this.modelMapper.map(p,PostServiceModel.class)).collect(Collectors.toList());

    }

    @Override
    public UserServiceModel addUser(UserServiceModel userServiceModel) {
        User user = this.modelMapper.map(userServiceModel,User.class);
        user.setRole(roleRepository.findByName("User"));
        user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));
        return this.modelMapper.map(userRepository.saveAndFlush(user),UserServiceModel.class);
    }

    @Override
    public UserServiceModel updateUser(String username, UserServiceModel userServiceModel) {
        User user = userRepository.findByUsername(username);
        user.setFirstName(userServiceModel.getFirstName());
        user.setLastName(userServiceModel.getLastName());
        user.setUsername(userServiceModel.getUsername());
        user.setAddress(userServiceModel.getAddress());
        user.setEmail(userServiceModel.getEmail());
        user.setPassword(this.bCryptPasswordEncoder.encode(userServiceModel.getPassword()));
        return  this.modelMapper.map(userRepository.save(user),UserServiceModel.class);
    }

    @Override
    public UserServiceModel deleteUser(String username) {
        User user = userRepository.findByUsername(username);
        userRepository.deleteByUsername(username);
        return this.modelMapper.map(user,UserServiceModel.class);
    }

    @Override
    public UserServiceModel giveAdminToUser(String username) {
        User user = userRepository.findByUsername(username);
        Role role = roleRepository.findByName("Admin");
        user.setRole(role);
        return this.modelMapper.map(userRepository.save(user),UserServiceModel.class);
    }

    @Override
    public UserServiceModel takeAdminToUser(String username) {
        User user = userRepository.findByUsername(username);
        Role role = roleRepository.findByName("User");
        user.setRole(role);
        return this.modelMapper.map(userRepository.save(user),UserServiceModel.class);
    }

    @Override
    public UserServiceModel loginUser(UserLoginBindingModel userLoginBindingModel) {
        User user =  userRepository.findByUsername(userLoginBindingModel.getUsername());
        if((user!=null)&& (this.bCryptPasswordEncoder.matches(userLoginBindingModel.getPassword(),user.getPassword())))
        {
            return this.modelMapper.map(user,UserServiceModel.class);
        }else return null;
    }
}
