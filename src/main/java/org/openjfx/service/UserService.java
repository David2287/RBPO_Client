package org.openjfx.service;

import org.openjfx.model.Admin;
import org.openjfx.model.RegisterdUser;
import org.openjfx.model.abstracts.User;
import org.openjfx.model.interfaces.Author;
import org.openjfx.model.interfaces.Reviewer;

import java.io.IOException;
import java.util.List;

public interface UserService extends DatabaseController {
    void addANewUser(RegisterdUser newUser) throws IOException;

    RegisterdUser searchAUser(String userName) throws IOException;

    RegisterdUser checkUserCredential(String userName, String password) throws IOException;

    Admin adminLogin(String userName, String password);

    static UserService getDefaultInstance() {
        return UserServiceImpl.getInstance();
    }
}
