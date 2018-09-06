package com.cihan.service;

import com.cihan.domain.RandomCity;
import com.cihan.domain.Role;
import com.cihan.domain.User;
import com.cihan.domain.dao.UserDao;
import com.cihan.repository.RandomCityRepository;
import com.cihan.repository.RoleRepository;
import com.cihan.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GenericService {
    private final UserRepository userRepository;
    private final RandomCityRepository randomCityRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Autowired
    public GenericService(UserRepository userRepository, RandomCityRepository randomCityRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.randomCityRepository = randomCityRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    //refactor
    public Boolean saveUser(UserDao user) {
        if (userRepository.findByUsername(user.getUsername()) == null) {
            List<Role> roles = new ArrayList<>();
            roles.add(roleRepository.findOne(new Long(1)));
            userRepository.save(User.builder()
                    .firstName(user.getFirstName())
                    .lastName(user.getLastName())
                    .username(user.getUsername())
                    .password(passwordEncoder.encode(user.getPassword()))
                    .roles(roles).build());
            return true;
        }
        return false;
    }

    public List<RandomCity> findAllRandomCities() {
        return (List<RandomCity>) randomCityRepository.findAll();
    }
}
