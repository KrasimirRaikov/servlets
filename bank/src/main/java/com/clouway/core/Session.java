package com.clouway.core;

import java.util.Date;

/**
 * @author Slavi Dichkov (slavidichkof@gmail.com)
 */
public class Session {
    public final String ID;
    public final String userEmail;
    public final long sessionExpiresOn;
    public static final long sessionExpiresTime = 1000 * 60 * 60 * 5;

    public Session(String ID, String userEmail, long sessionExpiresOn) {
        this.ID = ID;
        this.userEmail = userEmail;
        this.sessionExpiresOn = sessionExpiresOn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Session session = (Session) o;

        if (sessionExpiresOn != session.sessionExpiresOn) return false;
        if (ID != null ? !ID.equals(session.ID) : session.ID != null) return false;
        return userEmail != null ? userEmail.equals(session.userEmail) : session.userEmail == null;

    }

    @Override
    public int hashCode() {
        int result = ID != null ? ID.hashCode() : 0;
        result = 31 * result + (userEmail != null ? userEmail.hashCode() : 0);
        result = 31 * result + (int) (sessionExpiresOn ^ (sessionExpiresOn >>> 32));
        return result;
    }
}