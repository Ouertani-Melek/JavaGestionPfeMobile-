/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompagny.Service;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.codename1.util.StringUtil;
import com.mycompany.Entities.DemandesPDF;
import java.io.IOException;
import java.util.Date;

import java.util.ArrayList;

import java.util.List;
import java.util.Map;

/**
 *
 * @author yahia
 */
public class DemandesServices {

    public ArrayList<DemandesPDF> AfficherToutesLesDemandes() {

        ArrayList<DemandesPDF> listDemandes = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/gestionpfe/web/app_dev.php/AfficherDemandeMobile");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp = new JSONParser();

                try {
                    Map<String, Object> demandes = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));

                    //System.out.println(tasks);
                    Object o = new Object();
                    o = null;

                    List<Map<String, Object>> list = (List<Map<String, Object>>) demandes.get("root");
                    for (Map<String, Object> obj : list) {

                        if (obj.get("etatDemande") == o) {

                            DemandesPDF DemandesAafficher = new DemandesPDF();

                            float id = Float.parseFloat(obj.get("id").toString());

                            DemandesAafficher.setMatriculeDemande((int) id);
                            String nomPrenom;
                            String idEtudiant;
                            String TitreOffre;
                            String NomEntreprise;
                            String ImageCandidat;
                            String string = obj.get("idUser").toString();
                            String off = obj.get("idOffre").toString();

                            List<String> parts = StringUtil.tokenize(string, ",");
                            List<String> partOffre = StringUtil.tokenize(off, ",");

                            nomPrenom = parts.get(2).substring(5) + " " + parts.get(3).substring(8);
                            idEtudiant = parts.get(8).substring(4);
                            TitreOffre = partOffre.get(35).substring(7);
                            NomEntreprise = partOffre.get(2).substring(5);
                            DemandesAafficher.setNomEntreprise(NomEntreprise);
                            DemandesAafficher.setNomEntreprise(NomEntreprise);
                            DemandesAafficher.setNomEtudiant(nomPrenom);
                            DemandesAafficher.setOffreDemande(TitreOffre);
                            DemandesAafficher.setIdEtudiant((int) Float.parseFloat(idEtudiant)); 
                            DemandesAafficher.setImageUSr(parts.get(9).substring(13));
                            DemandesAafficher.setEmailEtu(parts.get(13).substring(7));
                            
                            DemandesAafficher.setAdresseEtudiant( "Tunisie,"+ parts.get(4).substring(7) );
                            DemandesAafficher.setNumTelEtudiant((int) Float.parseFloat(parts.get(6).substring(11)));
                              listDemandes.add(DemandesAafficher);
                        }

                    }
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);

        return listDemandes;

    }
    public void FixerDateEntretien(String d,String methode,int id) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/gestionpfe/web/app_dev.php/FixerDateEntretienMobile/" + d + "/" + methode + "/"+id;
        con.setUrl(Url);

        System.out.println("tt");

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }

}
