package com.api.crud.services;

import com.api.crud.api.UserRequestDTO;
import com.api.crud.models.UserModel;
import com.api.crud.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    IUserRepository userRepository;

    public ArrayList<UserModel> getUsers() {
        return (ArrayList<UserModel>) userRepository.findAll();
    }

    public UserModel saveUser(UserRequestDTO request) {
        var userModel = new UserModel();
        userModel.setNombre(request.nombre());
        userModel.setApellido(request.apellido());
        userModel.setEmail(request.email());
        return userRepository.save(userModel);
    }

    public Optional<UserModel> getById(int id) {
        return userRepository.findById(id);
    }


    public Optional<UserModel> updateById(UserRequestDTO request, int id) {
        Optional<UserModel> user = userRepository.findById(id);

        if (user.isPresent()) {
            if (request.nombre() != null) {
                user.get().setNombre(request.nombre());
            }
            if (request.apellido() != null) {
                user.get().setApellido(request.apellido());
            }
            if (request.email() != null) {
                user.get().setEmail(request.email());
            }
            return Optional.of(userRepository.save(user.get()));
        } else {
            return Optional.empty();
        }
    }

        public Boolean deleteUser (int id){
            try {
                userRepository.deleteById(id);
                return true;
            } catch (Exception e) {
                return false;
            }
        }
    }

