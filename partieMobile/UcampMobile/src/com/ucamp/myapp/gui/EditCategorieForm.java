/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ucamp.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.ucamp.myapp.entities.Categorie;
import com.ucamp.myapp.services.ServiceCategorie;

/**
 * GUI builder created Form
 *
 * @author Dell
 */
public class EditCategorieForm extends Form {

    public EditCategorieForm(Form previous,Categorie editCat) {
         setTitle("EditCategorie");
        setLayout(BoxLayout.y());
        
        TextField tfLabel = new TextField(editCat.getLabel());
       
        Button btnValider = new Button("Edit Categorie");
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfLabel.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    try {
                        Categorie c = new Categorie(tfLabel.getText());
                        if( ServiceCategorie.getInstance().EditCat(editCat.getId(),tfLabel.getText()))
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
        
        addAll(tfLabel,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
                
    }
    
 }
