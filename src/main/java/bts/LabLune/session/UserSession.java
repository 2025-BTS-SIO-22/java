package bts.LabLune.session;

import bts.LabLune.modelo.UserDTO;

public class UserSession {

    private static UserDTO userSession;

    public static void startSession(UserDTO user) {
        userSession = user;
    }

    public static UserDTO getUserSession() {
        return userSession;
    }
}