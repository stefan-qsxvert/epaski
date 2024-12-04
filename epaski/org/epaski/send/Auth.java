
package org.epaski.send;

import jakarta.mail.Authenticator;
import jakarta.mail.PasswordAuthentication;

public class Auth extends Authenticator {

    private String login;
    private String haslo;
    public Auth(String login, String haslo){
        this.login = login;
        this.haslo = haslo;
    }
    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(login, haslo);
    }
}