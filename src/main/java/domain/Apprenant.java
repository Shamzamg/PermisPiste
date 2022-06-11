package domain;

import java.io.Serializable;


/**
 * The persistent class for the Apprenant database table.
 * 
 */

public class Apprenant implements Serializable {
	private static final long serialVersionUID = 1L;
	private int numApprenant;
	private String nomApprenant;
	private String prenomApprenant;
	
	public Apprenant(int numApprenant, String nomApprenant, String prenomApprenant) {
		super();
		this.numApprenant = numApprenant;
		this.nomApprenant = nomApprenant;
		this.prenomApprenant = prenomApprenant;
	}

	public Apprenant() {
	}

	public int getNumApprenant() {
		return this.numApprenant;
	}

	public void setNumApprenant(int idApprenant) {
		this.numApprenant = idApprenant;
	}

	public String getNomApprenant() {
		return this.nomApprenant;
	}

	public void setNomApprenant(String nomApprenant) {
		this.nomApprenant = nomApprenant;
	}

	public String getPrenomApprenant() {
		return this.prenomApprenant;
	}

	public void setPrenomApprenant(String prenomApprenant) {
		this.prenomApprenant = prenomApprenant;
	}

}