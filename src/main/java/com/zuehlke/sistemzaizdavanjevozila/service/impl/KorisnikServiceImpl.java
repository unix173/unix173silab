package com.zuehlke.sistemzaizdavanjevozila.service.impl;

import com.zuehlke.sistemzaizdavanjevozila.dao.KorisnikDao;
import com.zuehlke.sistemzaizdavanjevozila.form.ChangePasswordForm;
import com.zuehlke.sistemzaizdavanjevozila.form.UserRegistrationForm;
import com.zuehlke.sistemzaizdavanjevozila.model.Korisnik;
import com.zuehlke.sistemzaizdavanjevozila.service.KorisnikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KorisnikServiceImpl implements KorisnikService {

    @Autowired
    private KorisnikDao korisnikDao;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void addUser(UserRegistrationForm userRegistrationForm, String confirmationId) {
        Korisnik korisnik = new Korisnik();
        korisnik.setId(userRegistrationForm.getId());
        korisnik.setUsername(userRegistrationForm.getUsername());
        korisnik.setName(userRegistrationForm.getName());
        korisnik.setLastName(userRegistrationForm.getLastName());
        korisnik.setEmail(userRegistrationForm.getEmail());
        korisnik.setEnabled(false);
        korisnik.setPassword(bCryptPasswordEncoder.encode(userRegistrationForm.getPassword()));
        korisnik.setIsAdmin(false);
        korisnik.setConfirmationId(confirmationId);
        korisnikDao.addUser(korisnik);
    }

    @Override
    public List<Korisnik> getUsers() {
        return korisnikDao.getUsers();
    }

    @Override
    public Korisnik getUserById(Long id) {
        return korisnikDao.getUserById(id);
    }

    @Override
    public Korisnik getUserByUsername(String username) {
        return korisnikDao.getUserByUsername(username);
    }

    @Override
    public void deleteUser(Korisnik korisnik) {
        korisnikDao.deleteUser(korisnik);
    }

    @Override
    public void changePassword(ChangePasswordForm changePasswordForm) {
        Korisnik korisnik = new Korisnik();
        korisnik.setId(changePasswordForm.getId());
        korisnik.setUsername(changePasswordForm.getUsername());
        korisnik.setName(changePasswordForm.getName());
        korisnik.setLastName(changePasswordForm.getLastName());
        korisnik.setEmail(changePasswordForm.getEmail());
        korisnik.setPassword(bCryptPasswordEncoder.encode(changePasswordForm.getNewPassword()));
        korisnik.setEnabled(true);
        korisnik.setIsAdmin(false);
        korisnikDao.setUser(korisnik);
    }


    @Override
    public boolean checkIfUnique(UserRegistrationForm userRegistrationForm) {
        boolean usernameExists = korisnikDao.getUserByUsername(userRegistrationForm.getUsername()) != null;
        boolean emailExists = korisnikDao.getUserByEmail(userRegistrationForm.getEmail()) != null;
        return !usernameExists && !emailExists;
    }

    @Override
    public boolean checkIfPasswordIsCorrect(Long id, String oldPassword) {
        Korisnik korisnik = korisnikDao.getUserById(id);
        return bCryptPasswordEncoder.matches(oldPassword, korisnik.getPassword());
    }

    @Override
    public List<Korisnik> getUsersByUsername(String username) {
        return korisnikDao.getUsersByUsername(username);
    }

    @Override
    public boolean confirmRegistration(String confirmationId) {

        Korisnik korisnik = korisnikDao.getUserByConfirmationId(confirmationId);
        if (korisnik != null) {
            korisnik.setEnabled(true);
            korisnikDao.setUser(korisnik);
            return true;
        }
        return false;
    }
}
