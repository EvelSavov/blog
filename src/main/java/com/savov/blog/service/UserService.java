package com.savov.blog.service;

import com.savov.blog.domain.model.binding.UserLoginBindingModel;
import com.savov.blog.domain.model.service.PostServiceModel;
import com.savov.blog.domain.model.service.UserServiceModel;

import java.util.List;

public interface UserService {

    List<PostServiceModel> getPostByUsername(String username);

    UserServiceModel getUserByUsername(String username);

    UserServiceModel addUser(UserServiceModel userServiceModel);

    UserServiceModel updateUser(String username, UserServiceModel userServiceModel);

    UserServiceModel deleteUser(String username);

    UserServiceModel giveAdminToUser(String username);

    UserServiceModel takeAdminToUser(String username);

    UserServiceModel loginUser(UserLoginBindingModel userLoginBindingModel);
}
