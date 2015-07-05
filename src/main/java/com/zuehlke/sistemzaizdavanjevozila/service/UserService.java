package com.zuehlke.sistemzaizdavanjevozila.service;

import com.zuehlke.sistemzaizdavanjevozila.form.ChangePasswordForm;
import com.zuehlke.sistemzaizdavanjevozila.form.UserRegistrationForm;
import com.zuehlke.sistemzaizdavanjevozila.model.User;

import java.util.List;

public interface UserService {

    void addUser(UserRegistrationForm userRegistrationForm, String confirmationId);

    List<User> getUsers();

    User getUserById(Long id);

    User getUserByUsername(String username);

    void deleteUser(User user);

    void changePassword(ChangePasswordForm changePasswordForm);

    boolean checkIfUnique(UserRegistrationForm userRegistrationForm);

    boolean checkIfPasswordIsCorrect(Long id, String oldPassword);

    List<User> getUsersByUsername(String username);

    boolean confirmRegistration(String confirmationId);
}
