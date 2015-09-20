package com.zuehlke.sistemzaizdavanjevozila.dao;

import com.zuehlke.sistemzaizdavanjevozila.model.Korisnik;

import java.util.List;

public interface KorisnikDao {

    void addUser(Korisnik korisnik);

    List<Korisnik> getUsers();

    Korisnik getUserById(Long id);

    Korisnik getUserByUsername(String username);

    Korisnik getUserByEmail(String email);

    void deleteUser(Korisnik korisnik);

    void setUser(Korisnik korisnik);

    List<Korisnik> getUsersByUsername(String username);

    Korisnik getUserByConfirmationId(String confirmationId);
}
