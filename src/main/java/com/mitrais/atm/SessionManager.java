package com.mitrais.atm;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class SessionManager {
	private static Map<String,Session> sessions = new HashMap<>();

    public static String createSession(LoginUser user) {
        String sessionId = UUID.randomUUID().toString();
        Session session = new Session(sessionId, user);
        sessions.put(sessionId, session);
        return sessionId;
    }

    public static Session getSession(String sessionId) {
        return sessions.get(sessionId);
    }

    public static void invalidateSession(String sessionId) {
        sessions.remove(sessionId);
    }
    
}