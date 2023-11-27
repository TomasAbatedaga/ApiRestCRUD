package com.api.crud.services;

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

    public UserModel saveUser(UserModel user) {
        return userRepository.save(user);
    }

    public Optional<UserModel> getById(int id) {
        return userRepository.findById(id);
    }


    public Optional<UserModel> updateById(UserModel request, int id){
        Optional<UserModel> user = userRepository.findById(id);

        if (user.isPresent()){
            UserModel userModel = user.get();
            userModel.setNombre(request.getNombre());
            userModel.setApellido(request.getApellido());
            userModel.setEmail(request.getEmail());

            userRepository.save(userModel);
            return Optional.of(userModel);
        } else {
            return Optional.empty();
        }
    }
    /*public UserModel updateById(UserModel request, int id){
        UserModel user = userRepository.findById(id).get();

        user.setNombre(request.getNombre());
        user.setApellido(request.getApellido());
        user.setEmail(request.getEmail());

        return user;
    }*/

    public Boolean deleteUser(int id){
        try{
            userRepository.deleteById(id);
            return true;
        } catch (Exception e){
            return false;
        }
    }
}
