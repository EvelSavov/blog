package com.savov.blog.service;

import com.savov.blog.domain.entities.Role;
import com.savov.blog.domain.entities.User;
import com.savov.blog.domain.model.binding.UserLoginBindingModel;
import com.savov.blog.domain.model.service.UserServiceModel;
import com.savov.blog.repository.RoleRepository;
import com.savov.blog.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, ModelMapper modelMapper, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public UserServiceModel addUser(UserServiceModel userServiceModel) {
        User user = this.modelMapper.map(userServiceModel,User.class);
        user.setRole(roleRepository.findByName("User"));
        user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));
        return this.modelMapper.map(userRepository.saveAndFlush(user),UserServiceModel.class);
    }

    @Override
    public UserServiceModel loginUser(UserLoginBindingModel userLoginBindingModel) {
        User user =  userRepository.findByUsername(userLoginBindingModel.getUsername());
        if((user!=null)&& (this.bCryptPasswordEncoder.matches(userLoginBindingModel.getPassword(),user.getPassword())))
        {
            return this.modelMapper.map(user,UserServiceModel.class);
        }else return null;
    }

    @Override
    public UserServiceModel getUserByUsername(String username) {
        User user = userRepository.findByUsername(username);

        return this.modelMapper.map(user,UserServiceModel.class);
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
    public UserServiceModel deleteUser(String username) {
        User user = userRepository.findByUsername(username);
        userRepository.deleteByUsername(username);
        return this.modelMapper.map(user,UserServiceModel.class);
    }
}
