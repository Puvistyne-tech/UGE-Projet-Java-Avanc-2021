package com.friday.fridayback.user;

import com.friday.fridayback.handler.ResponseHandler;
import com.friday.fridayback.user.registration.UserRegistration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<User>> getUsers() {
//        return ResponseEntity.ok(userService.getUsers());
        return new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
//        return ResponseHandler.generateResponse(
//                "All the Users",
//                HttpStatus.OK,
//                userService.getUsers().stream().toList()
//        );
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> registerNewUser(@RequestBody UserRegistration registration) {
        return ResponseHandler.generateResponse(
                "User registration successful",
                HttpStatus.OK,
                registration
        );
//        userService.registerNewUser(registration);
    }

    @DeleteMapping(path = "{userId}")
    public ResponseEntity<Object> deleteUser(@PathVariable("userId") Long userId) {
        userService.deleteUserById(userId);
        return ResponseHandler.generateResponse(
                "User with id " + userId + " is deleted succesfully",
                HttpStatus.OK,
                null
        );
    }

    @GetMapping(path = "{userMail}")
    public ResponseEntity<User> getUserByMail(@PathVariable("userMail") String userMail) {
        return ResponseEntity.ok(userService.getUserByMail(userMail));
    }


//    @PutMapping(path = "{userMail}")
//    public void updateUser(
//            @PathVariable("userMail") Long userMail,
//            @RequestParam(required = false) String email,
//            @RequestParam(required = false) String firstname,
//            @RequestParam(required = false) String lastname
//    ){
//        //TODO
//        userService.updateUser()
//    }


//
//    @GetMapping("/{mail}")
//    public List<User> getUsers(@PathVariable String mail) {
//        return userService.getUsers();
//    }
//
//    @PostMapping("/create")
//    public ResponseEntity<User> create(@RequestBody User user) throws URISyntaxException {
//        User createdUser = user.create(user);
//
//        if (createdUser == null) {
//            return ResponseEntity.notFound().build();
//        } else {
//            URI uri= ServletUriComponentsBuilder.fromCurrentRequestUri()
//                    .path("");
//        }
//    }


}
