package org.openjfx.service;

import org.openjfx.MainApp;
import org.openjfx.model.Admin;
import org.openjfx.model.RegisterdUser;
import org.openjfx.model.abstracts.User;
import org.openjfx.model.factory.DataModelFactory;
import org.openjfx.model.interfaces.Author;
import org.openjfx.model.interfaces.Reviewer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

final class UserServiceImpl implements UserService {


    private static final UserServiceImpl Instance = new UserServiceImpl();
    private final DatabaseService databaseService = DatabaseService.getDefaultInstance();

    private final String ADMIN_NAME = "Admin";

    private final String USER_DATABASE_FILE_NAME = "user_table.csv";

    private UserServiceImpl() {

    }


    public static UserServiceImpl getInstance() {
        return Instance;
    }

    @Override
    public void addANewUser(RegisterdUser newUser) throws IOException {
        databaseService.addNewRecord(this, newUser);
    }

    @Override
    public RegisterdUser searchAUser(String userName) throws IOException {
        return databaseService.searchARecord(this, new String[]{userName}, this::findUserRecordFromLine, DataModelFactory::convertUserFromCSVLine);
    }

    @Override
    public RegisterdUser checkUserCredential(String userName, String password) throws IOException {
        return databaseService.searchARecord(this, new String[]{userName, password}, this::checkUserCredential, DataModelFactory::convertUserFromCSVLine);
    }

    @Override
    public Admin adminLogin(String userName, String password) {
        if (userName.equalsIgnoreCase("admin") && "Admin".equals(password)) {
            return new Admin(userName, password);
        }
        return null;
    }

    private boolean findUserRecordFromLine(String[] usernames, RegisterdUser dataBaseUser) {
        return dataBaseUser.getUserName().equalsIgnoreCase(usernames[0]);
    }

    private boolean checkUserCredential(String[] credential, RegisterdUser dataBaseUser) {
        String userName = credential[0];
        String password = credential[1];
        return dataBaseUser.getUserName().equalsIgnoreCase(userName) && dataBaseUser.getPassword().equals(password);
    }

    @Override
    public String getDatabaseName() {
        return USER_DATABASE_FILE_NAME;
    }
}
