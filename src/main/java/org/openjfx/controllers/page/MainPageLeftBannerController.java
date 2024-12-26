package org.openjfx.controllers.page;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import org.openjfx.controllers.PageNames;
import org.openjfx.helper.SceneHelper;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * The universal left sidebar of this application allows the user to switch to different pages by clicking on the different tabs in the sidebar
 */
public class MainPageLeftBannerController extends BorderPane implements Initializable {
    @FXML
    private HBox conferenceManagement;

    @FXML
    private Label conferenceManagementText;

    @FXML
    private Pane conferenceManagementIndicator;

    private int index;

    private static final int CONFERENCE_MANAGEMENT = 0;

    public MainPageLeftBannerController(){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("main_page_left_banner.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try{
            fxmlLoader.load();
        }catch (IOException exception){
            throw  new RuntimeException(exception);
        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initSelectedState();
    }

    @FXML
    void onItemClick(MouseEvent event) throws IOException {
        String id = ((HBox) event.getSource()).getId();
        switch (id) {
            case "conferenceManagement":
                SceneHelper.startPage(getClass(),event, PageNames.CONFERENCE_MANAGEMENT,true);
                break;
            default:
                break;
        }
    }

    private void initSelectedState(){
        restoreAllState();
        switch (index){
            case CONFERENCE_MANAGEMENT:
                setSelectedState(conferenceManagementIndicator,conferenceManagementText);
                break;
            default:
                break;
        }
    }

    private void setSelectedState(Pane pane,Label label){
        pane.setVisible(true);
        label.setTextFill(Paint.valueOf("#0C7CBA"));
        label.setFont(Font.font("System",FontWeight.BOLD,18));
    }

    /**
     * The selection status of the sidebar needs to be refreshed each time a page switch is made
     */
    private void restoreAllState(){
        conferenceManagementIndicator.setVisible(false);
        conferenceManagementText.setTextFill(Paint.valueOf("#000000"));
        conferenceManagementText.setFont(Font.font("System", FontWeight.NORMAL,18));
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
        initSelectedState();
    }
}
