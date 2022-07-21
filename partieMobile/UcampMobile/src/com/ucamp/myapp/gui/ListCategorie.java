/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ucamp.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.ucamp.myapp.entities.Categorie;
import com.ucamp.myapp.services.ServiceCategorie;
import java.util.ArrayList;

/**
 * GUI builder created Form
 *
 * @author Dell
 */
public class ListCategorie extends Form {
Form current;

    public ListCategorie(Form previous) {
        current=this;
        setTitle("List Categorie");
                setLayout(BoxLayout.y());
         ArrayList<Categorie> listeCat =   ServiceCategorie.getInstance().getAll();
      for (int i = 0 ; i <listeCat.size();i++){
          
          this.add(setElements(listeCat.get(i)));
          
          
      } 
      
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }
    
    
    public Container setElements (Categorie c){
    Container cnt = new Container(BoxLayout.y());
    Label id = new Label(String.valueOf(c.getId()));
    Label l = new Label(c.getLabel());
    
    Container cntdata= new Container(BoxLayout.x());
    Button edit = new Button ("Edit");
    Button supprimer = new Button ("supprimer");
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

    cntdata.addAll(id,l);
    Container cntButtons = new Container(BoxLayout.x());
    cntButtons.addAll(supprimer,edit);
    
    
    cnt.addAll(cntdata,cntButtons); 
    
   
    
    return cnt ;
    
    }

}