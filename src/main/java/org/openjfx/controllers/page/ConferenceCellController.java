package org.openjfx.controllers.page;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import org.openjfx.controllers.PageNames;
import org.openjfx.helper.SceneHelper;
import org.openjfx.helper.TimeHelper;
import org.openjfx.model.Conference;

import java.net.URL;
import java.util.ResourceBundle;

public class ConferenceCellController implements Initializable {
    @FXML
    private Label conferenceName;

    @FXML
    private Label topic;

    @FXML
    private Label deadline;

    @FXML
    private Button edit;

    private Conference conference;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initCell();
    }

    public Conference getConference() {
        return conference;
    }

    public void setConference(Conference conference) {
        this.conference = conference;
        initCell();
    }

    private void initCell() {
        if (this.conference == null) {
            return;
        }
        this.conferenceName.setText(conference.getName());
        this.topic.setText(conference.getTopic());
        this.deadline.setText(TimeHelper.convertToDisplayTime(conference.getDeadline()));

    }

    public void setConferenceFinishedLayout() {
        this.edit.setVisible(false);
    }
}
