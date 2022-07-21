/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ucamp.myapp.services;


import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.ucamp.myapp.entities.Categorie;
import com.ucamp.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.List;

/**
 *
 * @author Dell
 */
public class ServiceCategorie {
     public ArrayList<Categorie> Categories;
    
    public static ServiceCategorie instance=null;
    public boolean resultOK;
    private ConnectionRequest reqGet;
    private ConnectionRequest reqPost;

    private ServiceCategorie() {
         reqGet = new ConnectionRequest();
         reqPost= new ConnectionRequest();
         //conflit mabin l get w el post ki yabdaw fard page 
         
    }

    public static ServiceCategorie getInstance() {
        if (instance == null) {
            instance = new ServiceCategorie();
        }
        return instance;
    }

    public boolean AddCategorie(Categorie c) {
       //
       String url = Statics.BASE_URL + "categorie/create/"+c.getLabel();
     
       reqPost.setUrl(url);
      
       //
       //
       reqPost.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = reqPost.getResponseCode() == 200; //Code HTTP 200 OK
              
                reqPost.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(reqPost);
        return resultOK;
    }
    
    public ArrayList<Categorie> parseCategorie(String jsonText){
        try {
            Categories=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = 
               j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                Categorie c = new Categorie();
                float id = Float.parseFloat(obj.get("id").toString());
                c.setId((int)id);
                if (obj.get("label")==null)
              c.setLabel("null");
                else
              c.setLabel(obj.get("label").toString());
                Categories.add(c);
            }
            
            
        } catch (IOException ex) {
            
        }
        return Categories;
    }
    
    public ArrayList<Categorie> getAll(){
        //String url = Statics.BASE_URL+"/tasks/";
        String url = Statics.BASE_URL+"categorie/findAll";
        //System.out.println("===>"+url);
        reqGet.setUrl(url);
        reqGet.setPost(false);
        reqGet.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Categories = parseCategorie(new String(reqGet.getResponseData()));
                reqGet.removeResponseListener(this);
                
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(reqGet);
        return Categories;
    }

    public boolean EditCat(int id, String text) {
          String url = Statics.BASE_URL + "categorie/edit/"+id;
          
     reqPost.addArgument("label", text);
       reqPost.setUrl(url);
     
       reqPost.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = reqPost.getResponseCode() == 200; //Code HTTP 200 OK
              
                reqPost.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(reqPost);
        return resultOK;

    }
    public boolean Sup(int id) {
          String url = Statics.BASE_URL + "categorie/delete/"+id;
          
       reqPost.setUrl(url);
     
       reqPost.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = reqPost.getResponseCode() == 200; //Code HTTP 200 OK
              
                reqPost.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(reqPost);
        return resultOK;

    }
    
}
