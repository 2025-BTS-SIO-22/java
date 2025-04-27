package bts.lab_lune.session;

import bts.lab_lune.model.User;

public class UserSession {
    private static User userSession;

    public static User getUserSession() {
        return userSession;
    }

    public static void setUserSession(User userSession) {
        UserSession.userSession = userSession;
    }
}
