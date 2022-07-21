/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Dell
 */
public class Panier {
   private static Map<Materiels,Integer>Map_Panier = new HashMap();
   public static  void ajouterPanier(Materiels m ,int quantite)
   {Panier.Map_Panier.put(m,quantite);
   }
   public static void supprimerPanier(Materiels m){
       Panier.Map_Panier.remove(m);
   }
   public static void afficher(){
       System.out.println("Panier ----");
       Panier.Map_Panier.forEach((key, value) -> {

      // decrease value by 10%
     System.out.print(key.toString());
     System.out.print("   "+value);
     
       System.out.println("Fin Panier ----");
     
    });
    
       
   }
   public static void upQuantite(Materiels m ){
        int quantite = Panier.Map_Panier.get(m);
        quantite++;
        Panier.Map_Panier.put(m, quantite);
    }
   public static void downQuantite(Materiels m ){
        int quantite = Panier.Map_Panier.get(m);
        quantite--;
        Panier.Map_Panier.put(m, quantite);
    }

    public static Map<Materiels, Integer> getMap_Panier() {
        return Map_Panier;
    }

    public static void setMap_Panier(Map<Materiels, Integer> Map_Panier) {
        Panier.Map_Panier = Map_Panier;
    }
   
   
}
