package org.openjfx.model;

import org.openjfx.helper.CSVConvertHelper;
import org.openjfx.model.interfaces.CSVConvertable;

import java.util.UUID;

public class Review implements CSVConvertable<Review> {
    private String id;
    private String reviewContent;
    private String reviewerIdentifiedName;
    private String reviewerDisplayName;
    private String paperId;

    public Review(String paperId, String reviewContent, String reviewerIdentifiedName, String reviewerDisplayName) {
        this.id = UUID.randomUUID().toString();
        this.paperId = paperId;
        this.reviewContent = reviewContent;
        this.reviewerIdentifiedName = reviewerIdentifiedName;
        this.reviewerDisplayName = reviewerDisplayName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Review{" +
                "id='" + id + '\'' +
                ", reviewContent='" + reviewContent + '\'' +
                ", reviewerIdentifiedName='" + reviewerIdentifiedName + '\'' +
                ", reviewerDisplayName='" + reviewerDisplayName + '\'' +
                ", paperId='" + paperId + '\'' +
                '}';
    }

    @Override
    public String convertToCSVLine() {
        return CSVConvertHelper.convertClassToCSVStringLine(this);
    }
}
