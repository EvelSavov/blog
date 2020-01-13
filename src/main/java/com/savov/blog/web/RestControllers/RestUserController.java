package com.savov.blog.web.RestControllers;

import com.savov.blog.domain.entities.User;
import com.savov.blog.domain.model.binding.UserLoginBindingModel;
import com.savov.blog.domain.model.service.PostServiceModel;
import com.savov.blog.domain.restModel.bainding.RestUserBindingModel;
import com.savov.blog.domain.restModel.bainding.RestUserLoginBindingModel;
import com.savov.blog.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.savov.blog.domain.model.service.UserServiceModel;

import javax.servlet.http.HttpSession;
import java.util.List;


@RestController
@RequestMapping("api/v1/users")
public class RestUserController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    @Autowired
    public RestUserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    public ResponseEntity<?> addUsers(@RequestBody RestUserBindingModel restUserBindingModel, HttpSession session) {

        UserServiceModel user = userService.addUser(this.modelMapper.map(restUserBindingModel, UserServiceModel.class));
        return new ResponseEntity<>(this.modelMapper.map(user, RestUserBindingModel.class), HttpStatus.OK);

    }

    @PostMapping("/login")
    public ResponseEntity<?> singUp(@RequestBody RestUserLoginBindingModel restUserLoginBindingModel, HttpSession session) {
        User user = this.modelMapper.map(userService.loginUser(this.modelMapper.map(restUserLoginBindingModel, UserLoginBindingModel.class)), User.class);
        if (user != null) {
            session.setAttribute("id", user.getId());
            session.setAttribute("username", user.getUsername());
            return new ResponseEntity<>(this.modelMapper.map(user, RestUserBindingModel.class), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(restUserLoginBindingModel, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<?> singIn(HttpSession session) {
        if (session.getAttribute("username") != null) {
            session.invalidate();
        }
        return new ResponseEntity<>("logout", HttpStatus.OK);
    }

    @GetMapping("/me")
    public ResponseEntity<?> getMyProfile(HttpSession session) {
        if (session.getAttribute("username") != null) {
            return new ResponseEntity<>(userService.getUserByUsername((String) session.getAttribute("username")), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("no login user", HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping("/{username}")
    public ResponseEntity<?> getProfileByUsername(@PathVariable(name = "username") String username, HttpSession session) {
        if (session.getAttribute("username") != null) {
            UserServiceModel user = userService.getUserByUsername(username);
            return new ResponseEntity<>(this.modelMapper.map(user, RestUserBindingModel.class), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("no login user", HttpStatus.FORBIDDEN);
        }

    }

    @PutMapping("/{username}")
    public ResponseEntity<?> updateUsers(@PathVariable(name = "username") String username, @RequestBody RestUserBindingModel restUserBindingModel, HttpSession session) {
        if (session.getAttribute("username") != null) {
            UserServiceModel user = userService.updateUser(username, this.modelMapper.map(restUserBindingModel, UserServiceModel.class));
            return new ResponseEntity<>(this.modelMapper.map(user, RestUserBindingModel.class), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("no login user", HttpStatus.FORBIDDEN);
        }
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<?> deleteUsers(@PathVariable(name = "username") String username, HttpSession session) {
        if (session.getAttribute("username") != null) {
            UserServiceModel user = userService.deleteUser(username);
            return new ResponseEntity<>(this.modelMapper.map(user, RestUserBindingModel.class), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("no login user", HttpStatus.FORBIDDEN);
        }
    }

    @PutMapping("/{username}/giveAdmin")
    public ResponseEntity<?> giveAdminToUsers(@PathVariable(name = "username") String username, HttpSession session) {
        if (session.getAttribute("username") != null) {
            UserServiceModel user = userService.giveAdminToUser(username);
            return new ResponseEntity<>(this.modelMapper.map(user, RestUserBindingModel.class), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("no login user", HttpStatus.FORBIDDEN);
        }
    }

    @PutMapping("/{username}/takeAdmin")
    public ResponseEntity<?> takeAdminToUsers(@PathVariable(name = "username") String username, HttpSession session) {
        if (session.getAttribute("username") != null) {
            UserServiceModel user = userService.takeAdminToUser(username);
            return new ResponseEntity<>(this.modelMapper.map(user, RestUserBindingModel.class), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("no login user", HttpStatus.FORBIDDEN);
        }
    }


}
