package dao;

import meserreurs.MonException;
import domain.Apprenant;
import domain.Utilisateur;
import persistance.DialogueBd;

import java.util.ArrayList;
import java.util.List;

public class ServiceApprenant {

	// Mise à jour des caractéristiques d'un adhérent
	// Le booleen indique s'il s'agit d'un nouvel adhérent, auquel cas on fait
	// une création

	public void insererApprenant(Apprenant unApprenant) throws MonException {
		String mysql;

		DialogueBd unDialogueBd = DialogueBd.getInstance();
		try {
			mysql = "insert into Apprenant  (nom_Apprenant,prenom_Apprenant,ville_Apprenant)  " + "values ('"
					+ unApprenant.getNomApprenant();
			mysql += "'" + ",'" + unApprenant.getPrenomApprenant() + "')";

			unDialogueBd.insertionBD(mysql);
		} catch (MonException e) {
			throw e;
		}
		catch (Exception exc) {
			throw new MonException(exc.getMessage(), "systeme");
		}
	}

	// Modification d'un adhérent
	public void modifierApprenant(Apprenant unApprenant) throws MonException {
		String mysql;

		DialogueBd unDialogueBd = DialogueBd.getInstance();
		try {
			mysql = "update Apprenant set nom_Apprenant='";
			mysql += unApprenant.getNomApprenant();
			mysql += "', prenom_Apprenant= '";
			mysql += unApprenant.getPrenomApprenant();
			mysql += "' where id_Apprenant ='";
			mysql += unApprenant.getNumApprenant();
			mysql += "'";

			unDialogueBd.insertionBD(mysql);
		} catch (MonException e) {
			throw e;
		}
		catch (Exception exc) {
			throw new MonException(exc.getMessage(), "systeme");
		}
	}

	// Suppression d'un adhérent
	public void supprimerApprenant(int idApprenant) throws MonException {
		String mysql;
		String mysql2;
		String mysql3;

		DialogueBd unDialogueBd = DialogueBd.getInstance();
		try {
			//Requête pour supprimer les réservations auquel est rattaché l'adhérent
			mysql3 = "delete from reservation where id_Apprenant='";
			mysql3 += idApprenant;
			mysql3 += "'";

			//Requête pour supprimer les emprunts auquel est rattaché l'adhérent
			mysql2 = "delete from emprunt where id_Apprenant='";
			mysql2 += idApprenant;
			mysql2 += "'";

			//Requête pour supprimer l'adhérent
			mysql = "delete from Apprenant where id_Apprenant='";
			mysql += idApprenant;
			mysql += "'";

			unDialogueBd.insertionBD(mysql3);
			unDialogueBd.insertionBD(mysql2);
			unDialogueBd.insertionBD(mysql);
		} catch (MonException e) {
			throw e;
		}
		catch (Exception exc) {
			throw new MonException(exc.getMessage(), "systeme");
		}
	}

	// gestion des Apprenants
	// Consultation d'un adhérent par son numéro
	// Fabrique et renvoie un objet adhérent contenant le résultat de la requête
	// BDD
	public Apprenant consulterApprenant(int numero) throws MonException {
	  try
	  {
		String mysql = "select * from Apprenant where id_Apprenant="+ numero;
		List<Apprenant> mesAdh = consulterListeApprenants(mysql);
		if (mesAdh.isEmpty())
			return null;
		else {
			return mesAdh.get(0);
		}
	  } catch (MonException e)
		{
			throw e;
		}
	}

	// Consultation des adhérents
	// Fabrique et renvoie une liste d'objets adhérent contenant le résultat de
	// la requête BDD
	public List<Apprenant> consulterListeApprenants() throws MonException {
		String mysql = "select * from Apprenant order by nom_Apprenant";
		return consulterListeApprenants(mysql);
	}

	private List<Apprenant> consulterListeApprenants(String mysql) throws MonException {
		List<Object> rs;
		List<Apprenant> mesApprenants = new ArrayList<Apprenant>();
		int index = 0;
		try {
			DialogueBd unDialogueBd = DialogueBd.getInstance();
			rs =unDialogueBd.lecture(mysql);
			while (index < rs.size()) {
				// On crée un stage
				Apprenant unA = new Apprenant();
				// il faut redecouper la liste pour retrouver les lignes
				unA.setNumApprenant(Integer.parseInt(rs.get(index + 0).toString()));
				unA.setNomApprenant(rs.get(index + 1).toString());
				unA.setPrenomApprenant(rs.get(index + 2).toString());
				// On incrémente tous les 3 champs
				index = index + 3;
				mesApprenants.add(unA);
			}

			return mesApprenants;
		} catch (MonException e) {
			throw e;
		}
		catch (Exception exc) {
			throw new MonException(exc.getMessage(), "systeme");
		}
	}

	public Utilisateur getUtilistateur(String nom) throws MonException
	{
		List<Object> rs;
		Utilisateur unUti = null;
		String mysql = "SELECT numUtil,nomUtil, MotPasse, role  FROM utilisateur  " +
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
				// On incrémente tous les 3 champs
				index = index + 4;
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
