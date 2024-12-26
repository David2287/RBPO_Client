package org.openjfx.service;

import org.openjfx.model.Conference;
import org.openjfx.model.factory.DataModelFactory;
import org.openjfx.model.interfaces.Chair;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

final class ConferenceServiceImpl implements ConferenceService {
    private static final ConferenceServiceImpl Instance = new ConferenceServiceImpl();
    private static final String CONFERENCE_DATABASE_FILE_NAME = "conference_table.csv";

    private final DatabaseService databaseService = DatabaseService.getDefaultInstance();

    static ConferenceServiceImpl getInstance() {
        return Instance;
    }

    private ConferenceServiceImpl() {

    }

    @Override
    public Conference searchConferenceById(String id) throws IOException {
        return databaseService.searchARecord(this, new String[]{id}, this::searchConferenceById, DataModelFactory::convertConferenceFromCSVLine);
    }

    @Override
    public List<Conference> searchUsersConference(Chair chair) throws IOException {
        return databaseService.searchRecords(this, new String[]{chair.getChairName()}, this::searchConferenceBelongToUser, DataModelFactory::convertConferenceFromCSVLine);
    }

    private boolean searchConferenceById(String[] id, Conference conference) {
        return conference.getId().equals(id[0]);
    }

    private boolean searchConferenceBelongToUser(String[] usernames, Conference conference) {
        return Arrays.binarySearch(usernames, conference.getChairName()) >= 0;
    }


    @Override
    public String getDatabaseName() {
        return CONFERENCE_DATABASE_FILE_NAME;
    }
}
