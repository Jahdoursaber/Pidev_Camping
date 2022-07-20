/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author Khalil ZRIBI
 */
public class Utilisateur {
    
   private int idU ;
   private String nom;
   private String prenom;
   private String email;
   private int age ;
   private int telephone ;
   private  String password;
   private  String genre;
   private String type;
   private String role;

    public Utilisateur() {
    }

    public Utilisateur(int idU, String nom, String prenom, String email, int age, int telephone, String password, String genre, String type, String role) {
        this.idU = idU;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.age = age;
        this.telephone = telephone;
        this.password = password;
        this.genre = genre;
        this.type = type;
        this.role = role;
    }

    public Utilisateur(String nom, String prenom, String email, int age, int telephone, String password, String genre, String type, String role) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.age = age;
        this.telephone = telephone;
        this.password = password;
        this.genre = genre;
        this.type = type;
        this.role = role;
    }

    public Utilisateur(int idU, String nom, String prenom, String email, int age, int telephone, String genre, String type, String role) {
        this.idU = idU;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.age = age;
        this.telephone = telephone;
        this.genre = genre;
        this.type = type;
        this.role = role;
    }

    public Utilisateur(int idU, String nom, String prenom, String email, int age, int telephone, String genre, String type) {
        this.idU = idU;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.age = age;
        this.telephone = telephone;
        this.genre = genre;
        this.type = type;
    }
    
    

    public Utilisateur(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public Utilisateur(String nom, String prenom, String email, int age, int telephone, String genre, String type, String role) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.age = age;
        this.telephone = telephone;
        this.genre = genre;
        this.type = type;
        this.role = role;
    }

    

    public int getIdU() {
        return idU;
    }

    public void setIdU(int idU) {
        this.idU = idU;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getTelephone() {
        return telephone;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String pass) {
        this.password = pass;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Utilisateur{" + "idU=" + idU + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", age=" + age + ", telephone=" + telephone + ", password=" + password + ", genre=" + genre + ", type=" + type + ", role=" + role + '}';
    }
   
   
}
