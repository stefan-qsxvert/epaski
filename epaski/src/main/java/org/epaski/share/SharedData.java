package org.epaski.share;

public class SharedData {
	
	private String mailUser;
	private String mailPass;
	private String mailServer;
	private String mailServerPort;
	
	private String dbUser;
	private String dbUserPass;
	private String dbBaseName;
	private String dbServer;
	private String dbServerPort;
	
	public SharedData() {
		
	}

	public String getMailUser() {
		return mailUser;
	}

	public void setMailUser(String mailUser) {
		this.mailUser = mailUser;
	}

	public String getMailPass() {
		return mailPass;
	}

	public void setMailPass(String mailPass) {
		this.mailPass = mailPass;
	}

	public String getMailServer() {
		return mailServer;
	}

	public void setMailServer(String mailServer) {
		this.mailServer = mailServer;
	}

	public String getMailServerPort() {
		return mailServerPort;
	}

	public void setMailServerPort(String mailServerPort) {
		this.mailServerPort = mailServerPort;
	}

	public String getDbUser() {
		return dbUser;
	}

	public void setDbUser(String dbUser) {
		this.dbUser = dbUser;
	}

	public String getDbUserPass() {
		return dbUserPass;
	}

	public void setDbUserPass(String dbPass) {
		this.dbUserPass = dbPass;
	}

	public String getDbServer() {
		return dbServer;
	}

	public void setDbServer(String dbServer) {
		this.dbServer = dbServer;
	}

	public String getDbServerPort() {
		return dbServerPort;
	}

	public void setDbServerPort(String dbServerPort) {
		this.dbServerPort = dbServerPort;
	}

	public String getDbBaseName() {
		return dbBaseName;
	}

	public void setDbBaseName(String dbBase) {
		this.dbBaseName = dbBase;
	}

}
