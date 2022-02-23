package com.example.accessingdatamysql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "/all")
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @PostMapping
    public String addUser(@RequestParam String name, @RequestParam String email) {
        return userService.createUser(name, email);
    }

    @PostMapping(path = "/addNew")
    public String addNew(@RequestBody @Valid UserForm userForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuffer sb = new StringBuffer();
            List<ObjectError> allErrors = bindingResult.getAllErrors();
            for (ObjectError error : allErrors) {
                sb.append("\n");
                sb.append(error.getDefaultMessage());
            }
            return sb.toString();
        }
        return userService.createUser(userForm.getName(), userForm.getEmail());
    }

    @PostMapping(path = "/upload")
    public int uploadUsers(@RequestParam MultipartFile multipartFile) {
        return userService.uploadUsers(multipartFile);
    }

    @GetMapping
    public String get() {
        return "Hello";
    }
}
