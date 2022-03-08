package com.it.rs.ResSysMVC.service;

import com.it.rs.ResSysMVC.entity.UserEntity;
import com.it.rs.ResSysMVC.exception.UserIdAlreadyPresentException;
import com.it.rs.ResSysMVC.model.User;
import com.it.rs.ResSysMVC.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {

    @Autowired
    private UserRepository userRepository;

    public void registerUser(User user) throws UserIdAlreadyPresentException {
        boolean ue = userRepository.existsById(user.getUserId());
        if(ue) {
            throw new UserIdAlreadyPresentException("RegistrationService.USERID_PRESENT");
        }
        UserEntity userEntity = new UserEntity();
        userEntity.setUserId(user.getUserId());
        userEntity.setCity(user.getCity());
        userEntity.setEmail(user.getEmail());
        userEntity.setPassword(user.getPassword());
        userEntity.setPhone(user.getPhone());
        userEntity.setName(user.getName());
        userRepository.saveAndFlush(userEntity);
    }
}
