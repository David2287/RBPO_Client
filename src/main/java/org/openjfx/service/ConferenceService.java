package org.openjfx.service;

import org.openjfx.model.Conference;
import org.openjfx.model.interfaces.Chair;

import java.io.IOException;
import java.util.List;

public interface ConferenceService extends DatabaseController {
    Conference searchConferenceById(String id) throws IOException;
    List<Conference> searchUsersConference(Chair chair) throws IOException;

    static ConferenceService getDefaultInstance(){
        return ConferenceServiceImpl.getInstance();
    }
}
