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
    @CrossOrigin // can be used at the class level as well.
    // https://developer.mozilla.org/en-US/docs/Web/HTTP/CORS
    // https://spring.io/blog/2015/06/08/cors-support-in-spring-framework
    // default max-age is 5secs, OPTIONS response can be cached as well.
    // Browsers also have a upper bound that is used when max-age is given a very high value.
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

    // Using @RequestParam per field works.
    @PostMapping(path = "/upload")
    public int uploadUsers(@RequestParam MultipartFile multipartFile, @RequestParam String name) {
        System.out.println(name);
        return userService.uploadUsers(multipartFile);
    }

    // When submitting a form containing a file, do NOT use @RequestBody annotation.
    @PostMapping(path = "/uploadForm", consumes = "multipart/form-data")
    public int upload(UserUploadForm userUploadForm) {
        return userService.uploadForm(userUploadForm);
    }

    @GetMapping
    public String get() {
        return "Hello";
    }
}
