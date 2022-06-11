package dao;

import meserreurs.MonException;
import domain.Utilisateur;
import persistance.DialogueBd;

import java.util.List;

public class ServiceLogin {


    public Utilisateur getUtilistateur(String nom) throws MonException
    {
        List<Object> rs;
        Utilisateur unUti = null;
        String mysql = "SELECT numUtil,nomUtil, MotPasse, role, salt  FROM utilisateur  " +
                " where nomUtil = " + "'" + nom +"'";
        int index = 0;
        try {
            DialogueBd unDialogueBd = DialogueBd.getInstance();
            rs =unDialogueBd.lecture(mysql);
            while (index < rs.size()) {
                unUti = new Utilisateur();
                // il faut redecouper la liste pour retrouver les lignes
                unUti.setNumUtil(Integer.parseInt(rs.get(index + 0).toString()));
                unUti.setNomUtil(rs.get(index + 1).toString());
                unUti.setMotPasse(rs.get(index + 2).toString());
                unUti.setRole(rs.get(index + 3).toString());
                unUti.setSalt(rs.get(index + 4).toString());
                // On incrémente tous les 5 champs
                index = index + 5;
            }
            return unUti;
        } catch (MonException e) {
            throw e;
        }
        catch (Exception exc) {
            throw new MonException(exc.getMessage(), "systeme");
        }
    }

}



