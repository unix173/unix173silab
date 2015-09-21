package com.zuehlke.sistemzaizdavanjevozila.service.impl;

import com.zuehlke.sistemzaizdavanjevozila.dao.KorisnikDao;
import com.zuehlke.sistemzaizdavanjevozila.form.IzmenaLozinkeForm;
import com.zuehlke.sistemzaizdavanjevozila.form.RegistracijaKorisnikaForm;
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
    public void addUser(RegistracijaKorisnikaForm registracijaKorisnikaForm, String confirmationId) {
        Korisnik korisnik = new Korisnik();
        korisnik.setId(registracijaKorisnikaForm.getId());
        korisnik.setUsername(registracijaKorisnikaForm.getUsername());
        korisnik.setName(registracijaKorisnikaForm.getName());
        korisnik.setLastName(registracijaKorisnikaForm.getLastName());
        korisnik.setEmail(registracijaKorisnikaForm.getEmail());
        korisnik.setEnabled(false);
        korisnik.setPassword(bCryptPasswordEncoder.encode(registracijaKorisnikaForm.getPassword()));
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
    public void changePassword(IzmenaLozinkeForm izmenaLozinkeForm) {
        Korisnik korisnik = new Korisnik();
        korisnik.setId(izmenaLozinkeForm.getId());
        korisnik.setUsername(izmenaLozinkeForm.getUsername());
        korisnik.setName(izmenaLozinkeForm.getName());
        korisnik.setLastName(izmenaLozinkeForm.getLastName());
        korisnik.setEmail(izmenaLozinkeForm.getEmail());
        korisnik.setPassword(bCryptPasswordEncoder.encode(izmenaLozinkeForm.getNewPassword()));
        korisnik.setEnabled(true);
        korisnik.setIsAdmin(false);
        korisnikDao.setUser(korisnik);
    }


    @Override
    public boolean checkIfUnique(RegistracijaKorisnikaForm registracijaKorisnikaForm) {
        boolean usernameExists = korisnikDao.getUserByUsername(registracijaKorisnikaForm.getUsername()) != null;
        boolean emailExists = korisnikDao.getUserByEmail(registracijaKorisnikaForm.getEmail()) != null;
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
