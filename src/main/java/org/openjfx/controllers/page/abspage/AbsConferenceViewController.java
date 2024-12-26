package org.openjfx.controllers.page.abspage;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import org.openjfx.controllers.PageNames;
import org.openjfx.controllers.dialog.GeneralAlertView;
import org.openjfx.helper.AutoTrimTextField;
import org.openjfx.helper.DialogHelper;
import org.openjfx.helper.InputValidation;
import org.openjfx.helper.SceneHelper;
import java.io.IOException;
import java.util.List;

/**
 * The Creation Conference and Modify Conference pages share the same UI, so a base class was created for this operation UI
 */
public abstract class AbsConferenceViewController implements Initializable {
    @FXML
    protected Parent rootView;

    @FXML
    protected AutoTrimTextField conferenceName;

    @FXML
    protected GeneralAlertView confirmCreateConferenceView;

    protected List<AutoTrimTextField> AutoTrimTextFields;


    @FXML
    void cancelButtonClicked(MouseEvent event) throws IOException {
        SceneHelper.startPage(getClass(), event, PageNames.CONFERENCE_MANAGEMENT, true);
    }

    @FXML
    void positiveButtonClicked(MouseEvent event) throws IOException {
        if (InputValidation.checkTextFiledIsEmpty(AutoTrimTextFields)) {
            DialogHelper.showErrorDialog("You need to fill up all fields");
            return;
        }
    }
}
