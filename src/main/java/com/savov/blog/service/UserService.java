package com.savov.blog.service;

import com.savov.blog.domain.entities.Post;
import com.savov.blog.domain.entities.User;
import com.savov.blog.domain.model.binding.UserLoginBindingModel;

import java.util.List;

public interface UserService {

    List<Post> getPostByUsername(String username);

    User getUserByUsername(String username);

    User addUser(User user);

    User updateUser(String username, User user);

    User deleteUser(String username);

    User giveAdminToUser(String username);

    User takeAdminToUser(String username);

    User loginUser(UserLoginBindingModel userLoginBindingModel);
}
