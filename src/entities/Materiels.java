/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author Dell
 */
public class Materiels {
    private int id;
    private String nom;
    private String description;
    private String Reference;
    private String photo;
    private String etat;
    private float prix;
    private int idCat;

    public Materiels() {
    }

    public Materiels(int id, String nom, String description, String Reference, String photo, String etat, float prix) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.Reference = Reference;
        this.photo = photo;
        this.etat = etat;
        this.prix = prix;
    }

    public Materiels(int id, String nom, String description, String Reference, String photo, String etat, float prix, int idCat) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.Reference = Reference;
        this.photo = photo;
        this.etat = etat;
        this.prix = prix;
        this.idCat = idCat;
    }

    public int getIdCat() {
        return idCat;
    }

    public void setIdCat(int idCat) {
        this.idCat = idCat;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReference() {
        return Reference;
    }

    public void setReference(String Reference) {
        this.Reference = Reference;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

  

    @Override
    public String toString() {
        return "Materiels{" + "id=" + id + ", nom=" + nom + ", description=" + description + ", Reference=" + Reference + ", photo=" + photo + ", etat=" + etat + ", prix=" + prix + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + this.id;
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
        final Materiels other = (Materiels) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

   
    
    
}

     
   


