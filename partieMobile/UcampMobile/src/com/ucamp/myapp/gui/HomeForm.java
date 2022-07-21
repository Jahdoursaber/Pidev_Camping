/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ucamp.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
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
public class HomeForm extends Form{
Form current;
    public HomeForm() {
        current=this; //Back 
        setTitle("Home");
        setLayout(BoxLayout.y());
        
        add(new Label("Choose an option"));
        Button btngestionCategorie = new Button("Gestion Categorie");
        Button btngestionMateriels = new Button("Gestion Materiels");

        btngestionCategorie.addActionListener(e-> new CategorieMenu(current).show());
          btngestionMateriels.addActionListener(e-> new MaterielsMenu(current).show());
        addAll(btngestionCategorie,btngestionMateriels);
        
        
    }
    
    
}