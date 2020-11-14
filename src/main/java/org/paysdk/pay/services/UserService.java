package org.paysdk.pay.services;

import org.paysdk.pay.models.User;

import java.util.List;

public interface UserService {

    List<User> findAll();
}
