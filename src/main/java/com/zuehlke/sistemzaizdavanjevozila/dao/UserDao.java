package com.zuehlke.sistemzaizdavanjevozila.dao;

import com.zuehlke.sistemzaizdavanjevozila.model.User;

import java.util.List;

public interface UserDao {

    void addUser(User user);

    List<User> getUsers();

    User getUserById(Long id);

    User getUserByUsername(String username);

    User getUserByEmail(String email);

    void deleteUser(User user);

    void setUser(User user);

    List<User> getUsersByUsername(String username);

    User getUserByConfirmationId(String confirmationId);
}
