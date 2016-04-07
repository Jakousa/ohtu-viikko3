/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.data_access;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import ohtu.domain.User;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

public class FileUserDao implements UserDao{
    File tiedot;
    
    public FileUserDao(String tiedosto) throws Exception {
        tiedot = new File(tiedosto);
    }
    
    private void kirjoita(String rivi) throws Exception{
        FileWriter kirjoittaja = new FileWriter(tiedot,true);
        kirjoittaja.append(rivi);
        kirjoittaja.close();
    }
    
    @Override
    public List<User> listAll() {
        Scanner lukija = null;
        try {
            lukija = new Scanner(tiedot);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileUserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        List<User> kayttajat = new ArrayList<User>(); 
        while (lukija.hasNextLine()) {
            String rivi = lukija.nextLine();
            String[] osat = rivi.split("\\:");
            User lisattava = new User(osat[0], osat[1]);
            kayttajat.add(lisattava);
        }
        return kayttajat;
    }

    @Override
    public User findByName(String name) {
        Scanner lukija = null;
        try {
            lukija = new Scanner(tiedot);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileUserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        while (lukija.hasNextLine()) {
            String rivi = lukija.nextLine();
            String[] osat = rivi.split("\\:");
            if (osat[0].equals(name)) {
                return new User(osat[0], osat[1]);
            }
        }
        return null;
    }

    @Override
    public void add(User user) {
        String rivi = user.getUsername() + ":" + user.getPassword() + "\n";
        try {
            this.kirjoita(rivi);
        } catch (Exception ex) {
            System.out.println("Kirjoitus epäonnistui");
        }
    }
}
