package klu.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import klu.repository.UsersRepository;

@Service
public class UsersManager {

    @Autowired
    UsersRepository UR;

    @Autowired
    EmailManager EM;

    @Autowired
    JWTManager JWT;

    // Register user
    public String addUser(Users U) {
        if (UR.validateEmail(U.getEmail()) > 0) {
            return "401::Email already exists";
        }
        UR.save(U);
        return "200::User Registered Successfully";
    }

    // Password recovery
    public String recoverPassword(String email) {
        Users U = UR.findById(email).orElse(null);
        if (U == null) {
            return "401::Email not found";
        }
        String message = String.format("Dear %s,\n\nYour password is: %s", 
                                       U.getFullname(), U.getPassword());
        return EM.sendEmail(U.getEmail(), "JobPortal: Password Recovery", message);
    }

    // Validate login
    public String validateCredentials(String email, String password) {
        if (UR.validateCredentials(email, password) > 0) {
            String token = JWT.generateToken(email);
            return "200::" + token;
        }
        return "401::Invalid Credentials";
    }

    // Get user fullname
    public String getFullname(String token) {
        String email = JWT.validateToken(token);
        if ("401".equals(email)) {
            return "401::Token Expired!";
        }

        Users U = UR.findById(email).orElse(null);
        if (U == null) {
            return "401::User not found";
        }
        return U.getFullname();
    }
}
