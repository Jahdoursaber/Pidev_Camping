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
public class MaterielsMenu extends com.codename1.ui.Form {
 Form current;
    public MaterielsMenu(Form previous) {
            current=this; //Back 
        setTitle("Materiels ");
        setLayout(BoxLayout.y());
        
        add(new Label("Choose an option"));
        Button btnAddMateriel = new Button("Add Materiel");
        Button btnListMateriels = new Button("List Materiels");
       btnAddMateriel.addActionListener(e-> new AddMaterielForm(current).show());
btnListMateriels.addActionListener(e->new ListeMateriel(current).show());
       
          
        addAll(btnAddMateriel,btnListMateriels);
                getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());

    }
    
    
}
