/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.List;


/**
 *
 * @author LENEVO
 */
//<t> type generique ey type nhotou lena najm nest3mlou en tant que objet
public interface Iservice<T> {
 public void ajouter (T t);
 public List<T> afficher();
 public void modifier(T t);
 public void supprimer(T t );
 
}
