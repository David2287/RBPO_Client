package org.openjfx.controllers.page;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.util.Pair;
import org.openjfx.MainApp;
import org.openjfx.controllers.PageNames;
import org.openjfx.controllers.dialog.AddInterestingAreaDialog;
import org.openjfx.helper.AutoTrimTextField;
import org.openjfx.helper.DialogHelper;
import org.openjfx.helper.InputValidation;
import org.openjfx.helper.SceneHelper;
import org.openjfx.model.RegisterdUser;
import org.openjfx.service.UserService;

import java.io.IOException;
import java.net.URL;
import java.util.*;

/**
 * This page is used to complete user registration, all textfields need to be populated with content
 */
public class SignUpController implements Initializable {

    @FXML
    private AutoTrimTextField firstName;

    @FXML
    private AutoTrimTextField lastName;

    @FXML
    private AutoTrimTextField email;

    @FXML
    private TextField password;

    @FXML
    private TextField confirmedPassword;

    @FXML
    private AutoTrimTextField mobileNumber;

    @FXML
    private AutoTrimTextField employerDetails;

    private List<TextField> allTextFields;

    private final UserService userService = UserService.getDefaultInstance();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        allTextFields = new ArrayList<>();
        allTextFields.add(firstName);
        allTextFields.add(lastName);
        allTextFields.add(password);
        allTextFields.add(confirmedPassword);
        allTextFields.add(email);
        allTextFields.add(mobileNumber);
        allTextFields.add(employerDetails);
    }

// Removed addInterestingAreaClicked method since interestArea is deleted.

    @FXML
    public void sign_up(ActionEvent event) throws IOException {
        List<TextField> emptyField = InputValidation.findTextFieldIsEmpty(allTextFields);
        if(emptyField.size() > 0) {
            DialogHelper.showErrorDialog("You need to fill up all fields");
            Collections.reverse(emptyField);
            emptyField.forEach(InputValidation::setFocusAndSetErrorStyle);
            return;
        }
        if (!InputValidation.isEmailFormat(email.getTrimText())) {
            DialogHelper.showErrorDialog("The email is not in a correct format");
            InputValidation.setFocusAndSetErrorStyle(email);
            return;
        }
        if (!InputValidation.checkPasswordFormat(password.getText())) {
            DialogHelper.showErrorDialog("The password must have at least 1 digit number 1 upper case and 1 lower case");
            InputValidation.setFocusAndSetErrorStyle(password);
            return;
        }
        RegisterdUser newUser = new RegisterdUser(
                email.getTrimText(),
                password.getText(),
                firstName.getTrimText(),
                lastName.getTrimText(),
                employerDetails.getTrimText()
        ) {
            @Override
            public Map<String, Integer> getInterestAreas() {
                return Map.of();
            }
        };
        try {
            if (userService.searchAUser(email.getTrimText()) != null) {
                DialogHelper.showErrorDialog("The email has been already registered in the system");
                return;
            }
            userService.addANewUser(newUser);
            MainApp.getInstance().setUser(newUser);
        } catch (IOException e) {
            e.printStackTrace();
            DialogHelper.showErrorDialog("The system could not handle the database");
        }
        SceneHelper.startPage(getClass(), event, PageNames.CONFERENCE_MANAGEMENT, true);
    }

    @FXML
    public void back(MouseEvent event) throws IOException {
        SceneHelper.startPage(getClass(), event, PageNames.LOG_IN, true);
    }

}
