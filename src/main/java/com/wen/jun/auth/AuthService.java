package com.wen.jun.auth;

import com.wen.jun.auth.user.User;

public interface AuthService {
    User register(User userToAdd);
    String login(String username, String password);
    String refresh(String oldToken);
}
