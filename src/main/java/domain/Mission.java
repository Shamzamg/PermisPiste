package domain;

import java.io.Serializable;


/**
 * The persistent class for the oeuvrevente database table.
 * 
 */

public class Mission implements Serializable {
	private static final long serialVersionUID = 1L;
	private int idMission;
	private String wordingMission;

	public Mission(int idMission, String wordingMission) {
		super();
		this.idMission = idMission;
		this.wordingMission = wordingMission;
	}

	public Mission() {
	}

	public int getIdMission() {
		return this.idMission;
	}

	public void setIdMission(int idMission) {
		this.idMission = idMission;
	}

	public String getWordingMission() {
		return this.wordingMission;
	}

	public void setWordingMission(String wordingMission){ this.wordingMission = wordingMission; }

}