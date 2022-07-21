/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ucamp.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.ucamp.myapp.entities.Categorie;
import com.ucamp.myapp.entities.Materiels;
import com.ucamp.myapp.services.ServiceCategorie;
import com.ucamp.myapp.services.ServiceMateriel;
import java.util.ArrayList;
import java.util.List;

/**
 * GUI builder created Form
 *
 * @author Dell
 */
public class AddMaterielForm extends com.codename1.ui.Form {
/* private int id;
    private String nom;
    private String description;
    private String Reference;
    private String photo;
    private String etat;
    private float prix;
    private int idCat;*/
    
    public AddMaterielForm(Form previous) {
        setTitle("Add a new Materiel");
        setLayout(BoxLayout.y());
        
        TextField nom = new TextField("","nom");
        Label desclabel = new Label("description");
        TextArea description = new TextArea();
       
        
        TextField Reference = new TextField("","reference");
        TextField Etat = new TextField("","Etat");
        TextField Prix = new TextField("","prix");
        ComboBox<Categorie> combo = new ComboBox();
        
        List<Categorie>Categories = new ArrayList();
        Categories=ServiceCategorie.getInstance().getAll();
        
        for(int i=0;i<Categories.size();i++){
            combo.addItem(Categories.get(i));
        }
        Container formContainer= new Container(BoxLayout.y());
                Button btnValider = new Button("Add Materiel");

        formContainer.addAll(nom,desclabel,description,Reference,Etat,Prix,combo,btnValider);
        
        
        

        

       
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
               if ((nom.getText().length()==0)||(description.getText().length()==0)
                       ||(Reference.getText().length()==0)||(Etat.getText().length()==0)||
                       (Prix.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {Categorie c = combo.getSelectedItem();
                          try {

                        
                       Materiels m = new Materiels(0,nom.getText(),description.getText(), Reference.getText()
                               ,"Desktop.PNG",Etat.getText(),Float.valueOf(Prix.getText()),c.getId());
                       
                        if( ServiceMateriel.getInstance().Add(m))
                        {
                           Dialog.show("Success","Connection accepted",new Command("OK"));
                        }else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                   
                }
               
                
            }
        });
        
        addAll(formContainer);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
                    }
    
   
}
