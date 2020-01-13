package com.savov.blog.service;

import com.savov.blog.domain.model.binding.UserLoginBindingModel;
import com.savov.blog.domain.model.service.PostServiceModel;
import com.savov.blog.domain.model.service.UserServiceModel;

import java.util.List;

public interface UserService {

    UserServiceModel addUser(UserServiceModel userServiceModel);

    UserServiceModel loginUser(UserLoginBindingModel userLoginBindingModel);

    UserServiceModel getUserByUsername(String username);

    UserServiceModel updateUser(String username, UserServiceModel userServiceModel);

    UserServiceModel giveAdminToUser(String username);

    UserServiceModel takeAdminToUser(String username);

    UserServiceModel deleteUser(String username);
}
