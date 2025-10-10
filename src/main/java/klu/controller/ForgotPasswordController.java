package klu.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/forgotpassword")
@CrossOrigin(origins = "http://localhost:3000") // Allow requests from frontend
public class ForgotPasswordController {

    @GetMapping("/{username}")
    public String forgotPassword(@PathVariable String username) {
        // Simulate sending reset link (you can replace with real email logic)
        return "Reset link sent to " + username;
    }
}
