package com.cihan.controller;

import com.cihan.domain.RandomCity;
import com.cihan.domain.User;
import com.cihan.domain.dao.UserDao;
import com.cihan.service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/springjwt")
public class ResourceController {

    private final GenericService userService;

    @Autowired
    public ResourceController(GenericService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/app/cities")
    @PreAuthorize("hasAuthority('ADMIN_USER') or hasAuthority('STANDARD_USER')")
    public List<RandomCity> getCities() {
        return userService.findAllRandomCities();
    }

    @PostMapping(value = "/save/user")
    public Boolean saveUser(@RequestBody UserDao user) {
        return userService.saveUser(user);
    }

    @GetMapping(value = "/app/users")
    @PreAuthorize("hasAuthority('ADMIN_USER')")
    public List<User> getUsers() {
        return userService.findAllUsers();
    }
}
