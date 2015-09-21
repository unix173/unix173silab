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
    public void sacuvajKorisnika(RegistracijaKorisnikaForm registracijaKorisnikaForm, String confirmationId) {
        Korisnik korisnik = new Korisnik();
        korisnik.setId(registracijaKorisnikaForm.getId());
        korisnik.setUsername(registracijaKorisnikaForm.getUsername());
        korisnik.setIme(registracijaKorisnikaForm.getName());
        korisnik.setPrezime(registracijaKorisnikaForm.getLastName());
        korisnik.setEmail(registracijaKorisnikaForm.getEmail());
        korisnik.setEnabled(false);
        korisnik.setPassword(bCryptPasswordEncoder.encode(registracijaKorisnikaForm.getPassword()));
        korisnik.setIsAdmin(false);
        korisnik.setConfirmationId(confirmationId);
        korisnikDao.sacuvajKorisnika(korisnik);
    }

    @Override
    public List<Korisnik> vratiKorisnike() {
        return korisnikDao.vratiKorisnike();
    }

    @Override
    public Korisnik ucitajKorisnikaID(Long id) {
        return korisnikDao.ucitajKorisnikaID(id);
    }

    @Override
    public Korisnik ucitajKorisnikaUsername(String username) {
        return korisnikDao.ucitajKorisnikaUsername(username);
    }

    @Override
    public void obrisiKorisnika(Korisnik korisnik) {
        korisnikDao.obrisiKorisnika(korisnik);
    }

    @Override
    public void izmeniKorisnika(IzmenaLozinkeForm izmenaLozinkeForm) {
        Korisnik korisnik = new Korisnik();
        korisnik.setId(izmenaLozinkeForm.getId());
        korisnik.setUsername(izmenaLozinkeForm.getUsername());
        korisnik.setIme(izmenaLozinkeForm.getName());
        korisnik.setPrezime(izmenaLozinkeForm.getLastName());
        korisnik.setEmail(izmenaLozinkeForm.getEmail());
        korisnik.setPassword(bCryptPasswordEncoder.encode(izmenaLozinkeForm.getNewPassword()));
        korisnik.setEnabled(true);
        korisnik.setIsAdmin(false);
        korisnikDao.izmeniKorisnika(korisnik);
    }


    @Override
    public boolean checkIfUnique(RegistracijaKorisnikaForm registracijaKorisnikaForm) {
        boolean usernameExists = korisnikDao.ucitajKorisnikaUsername(registracijaKorisnikaForm.getUsername()) != null;
        boolean emailExists = korisnikDao.ucitajKorisnikaEmail(registracijaKorisnikaForm.getEmail()) != null;
        return !usernameExists && !emailExists;
    }

    @Override
    public boolean checkIfPasswordIsCorrect(Long id, String oldPassword) {
        Korisnik korisnik = korisnikDao.ucitajKorisnikaID(id);
        return bCryptPasswordEncoder.matches(oldPassword, korisnik.getPassword());
    }

    @Override
    public List<Korisnik> pretraziKorisnikeUserName(String username) {
        return korisnikDao.pretraziKorisnikeUsername(username);
    }

    @Override
    public boolean confirmRegistration(String confirmationId) {

        Korisnik korisnik = korisnikDao.ucitajKorisnikaConfID(confirmationId);
        if (korisnik != null) {
            korisnik.setEnabled(true);
            korisnikDao.izmeniKorisnika(korisnik);
            return true;
        }
        return false;
    }
}
