/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import entities.Utilisateur;
import service.ServiceUtilisateur;

/**
 *
 * @author Khalil ZRIBI
 */
public class LoginU {
    private static LoginU instance;
    private static Utilisateur userconnect;

    private LoginU(String email, String password) {
        ServiceUtilisateur s = new ServiceUtilisateur();
        userconnect = s.login(email, password);
    }

    public static LoginU getInstance(String email, String password) {
        if (instance == null) {
            instance = new LoginU(email, password);
        }
        return instance;
    }

    public static void setInstance(LoginU instance) {
        LoginU.instance = instance;
    }

    public static Utilisateur getUserconnect() {
        return userconnect;
    }

    
}
