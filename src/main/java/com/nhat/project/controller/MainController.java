package com.nhat.project.controller;

import ch.qos.logback.core.pattern.util.RegularEscapeUtil;
import com.nhat.project.dto.LoginRequest;
import com.nhat.project.dto.post.PostDto;
import com.nhat.project.dto.post.PostShowDto;
import com.nhat.project.dto.user.UserCreateDto;
import com.nhat.project.entity.User;
import com.nhat.project.exception.UsernameAlreadyTaken;
import com.nhat.project.login.JwtTokenProvider;
import com.nhat.project.service.CommentService;
import com.nhat.project.service.PostService;
import com.nhat.project.service.UserService;
import com.nhat.project.user.CustomUserDetails;
import com.nhat.project.user.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MainController {
    @Autowired
    private UserService userService;
    @Autowired
    private PostService postService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    private AuthenticationManager authenticationManager;
    @GetMapping("")
    public String greet(){
        return "hi";
    }
    @GetMapping("/home")
    public String home(@RequestParam String a,
                       @RequestParam String b){
        return a + b;
    }
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserCreateDto userCreateDto){
        try{
            userService.register(userCreateDto);
            return ResponseEntity.status(HttpStatus.OK).body(userCreateDto);
        }
        catch (UsernameAlreadyTaken usernameAlreadyTaken){
            return ResponseEntity.status(HttpStatus.OK).body(usernameAlreadyTaken);
        }
    }
    @GetMapping("/post/view/all")
    public ResponseEntity<?> showPost(@RequestParam int page){
        List<PostShowDto> postDtoList = postService.showPost(page);
        return ResponseEntity.status(HttpStatus.OK).body(postDtoList);
    }
    @PostMapping("/login")
    public ResponseEntity authenticateUser( @RequestBody LoginRequest loginRequest) {

        // Xác thực từ username và password.
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtTokenProvider.generateToken(
                (CustomUserDetails) authentication.getPrincipal()
        );
        return ResponseEntity.status(HttpStatus.OK).body(jwt);
    }
}
