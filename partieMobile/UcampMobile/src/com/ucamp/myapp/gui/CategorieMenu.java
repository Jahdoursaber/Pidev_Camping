/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ucamp.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;

/**
 * GUI builder created Form
 *
 * @author Dell
 */
public class CategorieMenu extends com.codename1.ui.Form {
  Form current;  
    public CategorieMenu(Form previous) {
            current=this; //Back 
        setTitle("Categorie ");
        setLayout(BoxLayout.y());
        
        add(new Label("Choose an option"));
        Button btnAddCategorie = new Button("Add Categorie");
        Button btnListCategorie = new Button("List Categorie");
        
        btnAddCategorie.addActionListener(e-> new AddCategorieForm(current).show());
                btnListCategorie.addActionListener(e-> new ListCategorie(current).show());
          
        addAll(btnAddCategorie,btnListCategorie);
                getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());

    }
    
   
}
