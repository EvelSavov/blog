package com.savov.blog.web.RestControllers;


import com.savov.blog.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class TestControllers {

    @Autowired
    private PostRepository postRepository;

    @PostMapping("/test/login")
    public ResponseEntity<?> testLogin(@RequestBody String a, HttpSession session){
        session.setAttribute("name",a);

        return new ResponseEntity<>(a, HttpStatus.OK);
    }

    @GetMapping("/test/home")
    public ResponseEntity<?> testHome(HttpSession session){

        if(session.getAttribute("name")!=null) {
            return new ResponseEntity<>("there is login user", HttpStatus.OK);
        }else return new ResponseEntity<>("no login user", HttpStatus.OK);
    }
    @PostMapping("/test/logout")
    public ResponseEntity<?> testLogout(HttpSession session){
        session.removeAttribute("name");
        return new ResponseEntity<>("logout", HttpStatus.OK);
    }
}
