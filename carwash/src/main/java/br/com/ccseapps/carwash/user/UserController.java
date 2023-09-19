package br.com.ccseapps.carwash.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.ccseapps.carwash.util.Validation;

@RestController
public class UserController {

    private final String URL_PREFIX = "/auth";

    @Autowired
    private UserService service;

    @PostMapping(value = URL_PREFIX + "/register")
    public Validation register(@RequestBody User user) {
        return service.register(user);
    }

}