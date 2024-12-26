package org.openjfx.model;

import org.openjfx.helper.CSVConvertHelper;
import org.openjfx.model.abstracts.User;
import org.openjfx.model.factory.DataModelFactory;
import org.openjfx.model.interfaces.Author;
import org.openjfx.model.interfaces.CSVConvertable;
import org.openjfx.model.interfaces.Chair;
import org.openjfx.model.interfaces.Reviewer;
import org.openjfx.service.DatabaseService;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Map;

public class RegisterdUser extends User implements Author, Chair, Reviewer, CSVConvertable<RegisterdUser> {
    private final String firstName;
    private final String lastName;
    private final String employerDetails;
    private final String creationTime;

    public RegisterdUser(String userName, String password, String firstName, String lastName, String employerDetails) {
        super(userName, password);
        this.firstName = firstName;
        this.lastName = lastName;
        this.employerDetails = employerDetails;
        this.creationTime = LocalDateTime.now().toString();
    }

    @Override
    public boolean isAdmin() {
        return false;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getCreationTime() {
        return creationTime;
    }

    @Override
    public String convertToCSVLine() {
        return CSVConvertHelper.convertClassToCSVStringLine(this);
    }

    @Override
    public String toString() {
        return "RegisterdUser{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", employerDetails='" + employerDetails + '\'' +
                ", creationTime='" + creationTime + '\'' +
                '}';
    }

    @Override
    public String getAuthorName() {
        return firstName + " " + lastName;
    }

    @Override
    public String getAuthorIdentifiedName() {
        return getUserName();
    }

    @Override
    public String getChairName() {
        return getUserName();
    }

    @Override
    public String getReviewerName() {
        return getFirstName() + "." + getLastName().charAt(0);
    }

    @Override
    public String getReviewerIdentifiedName() {
        return getUserName();
    }

    @Override
    public Map<String, Integer> getInterestAreas() {
        return Map.of();
    }


}
