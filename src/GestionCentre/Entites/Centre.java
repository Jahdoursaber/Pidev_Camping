/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestioncentre.Entites;

import javafx.collections.ObservableList;

/**
 *
 * @author DELL
 */
public class Centre {

    public static void setItems(ObservableList<Centre> list) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    private int Id ; 
    private String Name ; 
    private String description ;
    private String photo;
    private String addresse ;

    public Centre() {
    }

    public Centre(int Id, String Name, String description, String photo, String addresse) {
        this.Id = Id;
        this.Name = Name;
        this.description = description;
        this.photo = photo;
        this.addresse = addresse;
    }

    public Centre(String Name, String description, String photo, String addresse) {
        this.Name = Name;
        this.description = description;
        this.photo = photo;
        this.addresse = addresse;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getAddresse() {
        return addresse;
    }

    public void setAddresse(String addresse) {
        this.addresse = addresse;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Centre other = (Centre) obj;
        if (this.Id != other.Id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Utilisateur{" + "Id=" + Id + ", Name=" + Name + ", description=" + description + ", photo=" + photo + ", addresse=" + addresse + '}';
    }
    
    
    
    
     
    
}
