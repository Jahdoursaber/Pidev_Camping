/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ucamp.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
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

    public ListCategorie(Form previous) {
        setTitle("List Categorie");

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
    
    Container cnt2= new Container(BoxLayout.y());
    
    
    cnt2.addAll(id,l);
    
    cnt.addAll(cnt2);    
   
    
    return cnt ;
    
    }

}