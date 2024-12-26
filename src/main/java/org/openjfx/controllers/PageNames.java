package org.openjfx.controllers;

import org.openjfx.controllers.page.interfaces.PageNameDescriber;

public enum PageNames implements PageNameDescriber {
    LOG_IN("log_in.fxml"),
    SIGN_UP("sign_up.fxml"),
    CONFERENCE_MANAGEMENT("conference_management.fxml"),
    CONFERENCE_CELL("conference_cell.fxml"),
    CREATE_CONFERENCE("create_conference.fxml"),
    PRE_DEFINE_KEYWORD_CELL("pre_define_list_cell.fxml"),
    ADMIN_PAGE("admin_page.fxml"),
    ADMIN_CONFERENCE_CELL("admin_conference_cell.fxml"),
    ADMIN_USER_CELL("admin_user_cell.fxml"),
    GENERAL_ALERT_VIEW("general_alert_view.fxml"),
    ;
    private final String pageName;

    public String getPageName() {
        return pageName;
    }

    PageNames(String pageName) {
        this.pageName = pageName;
    }
}
