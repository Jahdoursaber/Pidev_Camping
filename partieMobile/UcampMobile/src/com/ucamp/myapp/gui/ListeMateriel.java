/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ucamp.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.ucamp.myapp.entities.Categorie;
import com.ucamp.myapp.entities.Materiels;
import com.ucamp.myapp.services.ServiceCategorie;
import com.ucamp.myapp.services.ServiceMateriel;
import java.util.ArrayList;

/**
 * GUI builder created Form
 *
 * @author Dell
 */
public class ListeMateriel extends Form{
    
Form current;

    public ListeMateriel(Form previous) {
        current=this;
        setTitle("List Categorie");
                setLayout(BoxLayout.y());
         ArrayList<Materiels> ListeMat =   ServiceMateriel.getInstance().getAll();
      for (int i = 0 ; i <ListeMat.size();i++){
          
          this.add(setElements(ListeMat.get(i)));
          
          
      } 
      
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }
    
    
    public Container setElements (Materiels m){
        /*private int id;
    private String nom;
    private String description;
    private String Reference;
    private String etat;
    private float prix;
    private int idCat;*/
        
    Container cnt = new Container(BoxLayout.y());
    Label id = new Label("Id : " + String.valueOf(m.getId()));
    Label nom = new Label("Nom: " + m.getNom());
    Label description= new Label("Description : "+ m.getDescription());
        Label Reference= new Label("Reference : " + m.getReference());
        Label etat= new Label("Etat : "+ m.getEtat());
        Label prix= new Label("Prix : "+ String.valueOf(m.getPrix()));
        Label idCat= new Label("Id CAT  : " + String.valueOf(m.getIdCat()));


    Container cntdata= new Container(BoxLayout.y());
    Button edit = new Button ("Edit");
    Button supprimer = new Button ("supprimer");
    /*
      supprimer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                
                    try {
                        if( ServiceCategorie.getInstance().Sup(c.getId()))
                        {
                           Dialog.show("Success","",new Command("OK"));
                        }else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                    
                
                
                
            }
        });
    edit.addActionListener(e-> new EditCategorieForm(current,c).show());
*/
    cntdata.addAll(id,nom,description,Reference ,etat ,prix , idCat );
    Container cntButtons = new Container(BoxLayout.x());
    cntButtons.addAll(supprimer,edit);
    
    
    cnt.addAll(cntdata,cntButtons); 
    
   
    
    return cnt ;
    
    }

   
}
