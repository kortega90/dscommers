package com.kortega.dscommerce.services;

import com.kortega.dscommerce.entities.User;
import com.kortega.dscommerce.services.exceptions.ForbidenException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UserService userService;

    public void validateSelfOrAdmin(Long userId){
        User me = userService.authenticated();
        if (!me.hasRole("ROLE_ADMIN")&& !me.getId().equals(userId)){
            throw new ForbidenException("Access denied");
        }
    }
}
