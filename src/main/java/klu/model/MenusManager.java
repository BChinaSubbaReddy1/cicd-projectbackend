package klu.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.GsonBuilder;

import klu.repository.MenusRepository;
import klu.repository.UsersRepository;

@Service
public class MenusManager {

    @Autowired
    MenusRepository MR;

    @Autowired
    UsersRepository UR;

    @Autowired
    JWTManager JWT;

    // Get all menus
    public String getMenus() {
        List<Menus> menuList = MR.findAll();
        return new GsonBuilder().create().toJson(menuList);
    }

    // Get menus by user role
    public String getMenusByRole(String token) {
        String email = JWT.validateToken(token);
        if ("401".equals(email)) {
            return "401::Invalid Token";
        }

        Users U = UR.findById(email).orElse(null);
        if (U == null) {
            return "401::User not found";
        }

        List<Menus> menuList = MR.findByRole(U.getRole());
        return new GsonBuilder().create().toJson(menuList);
    }
}
