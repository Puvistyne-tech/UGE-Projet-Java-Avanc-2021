package com.friday.fridayback.user;

import com.friday.fridayback.user.registration.EmailValidator;
import com.friday.fridayback.user.registration.UserRegistration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final EmailValidator emailValidator;

    @Autowired
    public UserService(UserRepository userRepository, EmailValidator emailValidator) {
        this.userRepository = userRepository;
        this.emailValidator = emailValidator;
    }

    public List<User> getUsers() {
        // this will return a list of users
        //dont la liste est dans le format JSON
        return userRepository.findAll();
    }

    public void registerNewUser(UserRegistration user) {
        var isValidEmail = emailValidator.test(user.email());
        if (!isValidEmail) {
            throw new IllegalArgumentException("Email is not valid");
        }
        Optional<User> userOptional = userRepository.findUserByEmail(user.email());
        if (userOptional.isPresent()) {
            throw new IllegalStateException("Email Taken");
        }
        //TODO password bCrypt
        userRepository.save(new User(
                user.firstname(),
                user.lastname(),
                user.email(),
                user.password()
        ));
    }

    public void deleteUserById(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new IllegalStateException("User id " + userId + "not found");
        }
        userRepository.deleteById(userId);
    }

    public User getUserByMail(String userMail) {
        //TODO exception
        return userRepository.findUserByEmail(userMail).orElseThrow(
                () -> {
                    throw new IllegalStateException("No user found with this mail " + userMail);
                });
    }


//    @Transactional
//    public void updateUser(User)
}
