package com.bookStore.bookStore.service;

import com.bookStore.bookStore.model.user;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface userService {
    user createUser(user p);
    List<user>getUsers();

    user addMoneyToWallet(Integer userid, Double amount);

    user suspendUser(Integer userId);
    user updateUser(user p);
}
