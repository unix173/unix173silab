package com.zuehlke.sistemzaizdavanjevozila.service.impl;

import com.zuehlke.sistemzaizdavanjevozila.dao.UserDao;
import com.zuehlke.sistemzaizdavanjevozila.form.ChangePasswordForm;
import com.zuehlke.sistemzaizdavanjevozila.form.UserRegistrationForm;
import com.zuehlke.sistemzaizdavanjevozila.model.User;
import com.zuehlke.sistemzaizdavanjevozila.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void addUser(UserRegistrationForm userRegistrationForm, String confirmationId) {
        User user = new User();
        user.setId(userRegistrationForm.getId());
        user.setUsername(userRegistrationForm.getUsername());
        user.setName(userRegistrationForm.getName());
        user.setLastName(userRegistrationForm.getLastName());
        user.setEmail(userRegistrationForm.getEmail());
        user.setEnabled(false);
        user.setPassword(bCryptPasswordEncoder.encode(userRegistrationForm.getPassword()));
        user.setIsAdmin(false);
        user.setConfirmationId(confirmationId);
        userDao.addUser(user);
    }

    @Override
    public List<User> getUsers() {
        return userDao.getUsers();
    }

    @Override
    public User getUserById(Long id) {
        return userDao.getUserById(id);
    }

    @Override
    public User getUserByUsername(String username) {
        return userDao.getUserByUsername(username);
    }

    @Override
    public void deleteUser(User user) {
        userDao.deleteUser(user);
    }

    @Override
    public void changePassword(ChangePasswordForm changePasswordForm) {
        User user = new User();
        user.setId(changePasswordForm.getId());
        user.setUsername(changePasswordForm.getUsername());
        user.setName(changePasswordForm.getName());
        user.setLastName(changePasswordForm.getLastName());
        user.setEmail(changePasswordForm.getEmail());
        user.setPassword(bCryptPasswordEncoder.encode(changePasswordForm.getNewPassword()));
        user.setEnabled(true);
        user.setIsAdmin(false);
        userDao.setUser(user);
    }


    @Override
    public boolean checkIfUnique(UserRegistrationForm userRegistrationForm) {
        boolean usernameExists = userDao.getUserByUsername(userRegistrationForm.getUsername()) != null;
        boolean emailExists = userDao.getUserByEmail(userRegistrationForm.getEmail()) != null;
        return !usernameExists && !emailExists;
    }

    @Override
    public boolean checkIfPasswordIsCorrect(Long id, String oldPassword) {
        User user = userDao.getUserById(id);
        return bCryptPasswordEncoder.matches(oldPassword, user.getPassword());
    }

    @Override
    public List<User> getUsersByUsername(String username) {
        return userDao.getUsersByUsername(username);
    }

    @Override
    public boolean confirmRegistration(String confirmationId) {

        User user = userDao.getUserByConfirmationId(confirmationId);
        if (user != null) {
            user.setEnabled(true);
            userDao.setUser(user);
            return true;
        }
        return false;
    }
}
