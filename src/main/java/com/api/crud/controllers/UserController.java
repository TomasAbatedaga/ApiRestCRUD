package com.api.crud.controllers;

import com.api.crud.models.UserModel;
import com.api.crud.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping(path = "/list")
    public ArrayList<UserModel> getUsers() {
        return userService.getUsers();
    }

    @PostMapping
    public UserModel saveUser(@RequestBody UserModel user) {
        return userService.saveUser(user);
    }

    @GetMapping(path = "/{id}")
    public Optional<UserModel> getUserById(@PathVariable int id) {
        return userService.getById(id);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<UserModel> updateUserById(@RequestBody UserModel request, @PathVariable int id) {
        Optional<UserModel> optionalUpdateUser = userService.updateById(request,id);

        return optionalUpdateUser.map(userModel -> ResponseEntity.ok().body(userModel)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping(path = "/{id}")
    public String deleteUserById(@PathVariable int id) {
        boolean ok = userService.deleteUser(id);
        if (ok) {
            return "Usuario con id " + id + " borrado";
        } else {
            return "Error, no se pudo borrar";
        }
    }

}
