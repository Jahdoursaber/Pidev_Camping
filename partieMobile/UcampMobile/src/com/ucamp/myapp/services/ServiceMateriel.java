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
import com.ucamp.myapp.entities.Materiels;
import com.ucamp.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.List;

/**
 *
 * @author Dell
 */
public class ServiceMateriel {
     public ArrayList<Materiels> Materiels;
    
    public static ServiceMateriel instance=null;
    public boolean resultOK;
    private ConnectionRequest reqGet;
    private ConnectionRequest reqPost;

    private ServiceMateriel() {
         reqGet = new ConnectionRequest();
         reqPost= new ConnectionRequest();
         //conflit mabin l get w el post ki yabdaw fard page 
         
    }

    public static ServiceMateriel getInstance() {
        if (instance == null) {
            instance = new ServiceMateriel();
        }
        return instance;
    }

    public boolean Add(Materiels M) {
      
       String url = Statics.BASE_URL + "material/create";
       reqPost.addArgument("id", "0");
       reqPost.addArgument("nom", M.getNom());
       reqPost.addArgument("description", M.getDescription());
       reqPost.addArgument("refrence", M.getReference());
       reqPost.addArgument("photo", "desktop.PNG");
       reqPost.addArgument("etat", M.getEtat());
       reqPost.addArgument("prix",String.valueOf(M.getPrix()));
       reqPost.addArgument("idCat",String.valueOf(M.getIdCat()));

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
        reqPost.removeAllArguments();
        
        return resultOK;
    }
    
    public ArrayList<Materiels> parseMateriels(String jsonText){
           
        try {
            Materiels=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = 
               j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                float id = Float.parseFloat(obj.get("id").toString());
             float prix = Float.parseFloat(obj.get("prix").toString());
             float idCat=Float.parseFloat(obj.get("idCat").toString());
   
                Materiels m =  new Materiels((int)id,obj.get("nom").toString(), obj.get("description").toString(),  obj.get("refrence").toString(), 
                       obj.get("photo").toString(),  obj.get("etat").toString(), prix,(int)idCat);
              Materiels.add(m);
            }
            
            
        } catch (IOException ex) {
            
        }
        return Materiels;
        
    }
    
    public ArrayList<Materiels> getAll(){
        String url = Statics.BASE_URL+"material/findAll";
        reqGet.setUrl(url);
        reqGet.setPost(false);
        reqGet.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Materiels = parseMateriels(new String(reqGet.getResponseData()));
                reqGet.removeResponseListener(this);
                
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(reqGet);
        return Materiels;
    }

    public boolean EditCat(Materiels M) {
       reqPost.addArgument("nom", M.getNom());
       reqPost.addArgument("description", M.getDescription());
       reqPost.addArgument("refrence", M.getReference());
       reqPost.addArgument("photo", "desktop.PNG");
       reqPost.addArgument("etat", M.getEtat());
       reqPost.addArgument("prix",String.valueOf(M.getPrix()));
       reqPost.addArgument("idCat",String.valueOf(M.getIdCat()));
          String url = Statics.BASE_URL + "material/edit/"+M.getId();
      
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
          String url = Statics.BASE_URL + "material/delete/"+id;
          
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
