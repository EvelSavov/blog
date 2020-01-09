package com.savov.blog.web.RestControllers;

import com.savov.blog.domain.entities.User;
import com.savov.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("api/v1/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

//    @PostMapping("/singup")
//    public String singUp(){
//        return "sing up";
//    }
//
//    @PostMapping("/singin")
//    public String singIn(){
//        return "sing in";
//    }
//
//
//    @GetMapping("/me")
//    public String getMyProfile() {
//        return "My profile";
//    }

    @GetMapping("/{username}/profile")
    public ResponseEntity<?> getProfileByUsername(@PathVariable(name = "username") String username) {
        return new ResponseEntity<>(userService.getUserByUsername(username), HttpStatus.OK);
    }

    @GetMapping("/{username}/posts")
    public ResponseEntity<?> getPostsByUsername(@PathVariable(name = "username") String username) {
        return new ResponseEntity<>(userService.getPostByUsername(username), HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<?> addUsers(@RequestBody User user) {

        return  new ResponseEntity<>(userService.addUser(user), HttpStatus.OK);
    }

    @PutMapping("/{username}")
    public ResponseEntity<?>  updateUsers(@PathVariable(name = "username") String username,@RequestBody User user) {

        return  new ResponseEntity<>(userService.updateUser(username,user), HttpStatus.OK);
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<?>  deleteUsers(@PathVariable(name = "username") String username) {

        return new ResponseEntity<>(userService.deleteUser(username), HttpStatus.OK);
    }

    @PutMapping("/{username}/giveAdmin")
    public ResponseEntity<?>  giveAdminToUsers(@PathVariable(name = "username") String username) {

        return new ResponseEntity<>(userService.giveAdminToUser(username), HttpStatus.OK);
    }

    @PutMapping("/{username}/takeAdmin")
    public ResponseEntity<?>  takeAdminToUsers(@PathVariable(name = "username") String username) {

        return new ResponseEntity<>(userService.takeAdminToUser(username), HttpStatus.OK);
    }


}
