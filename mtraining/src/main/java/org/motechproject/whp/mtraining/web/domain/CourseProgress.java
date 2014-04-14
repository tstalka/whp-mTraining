package org.motechproject.whp.mtraining.web.domain;

import org.codehaus.jackson.annotate.JsonProperty;

import java.util.Collection;
import java.util.List;

import static org.apache.commons.lang.StringUtils.isBlank;
import static org.motechproject.mtraining.constants.CourseStatus.UNKNOWN;
import static org.motechproject.mtraining.constants.CourseStatus.enumFor;
import static org.motechproject.whp.mtraining.web.domain.ResponseStatus.INVALID_COURSE_STATUS;
import static org.motechproject.whp.mtraining.web.domain.ResponseStatus.MISSING_NODE;
import static org.motechproject.whp.mtraining.web.domain.ResponseStatus.MISSING_TIME;

public class CourseProgress {
    @JsonProperty
    Bookmark bookmark;
    @JsonProperty
    private String courseStartTime;
    @JsonProperty
    private int timeLeftToCompleteCourse;
    @JsonProperty
    private String courseStatus;

    public CourseProgress() {
    }

    public CourseProgress(String courseStartTime, Bookmark bookmark, int timeLeftToCompleteCourse, String courseStatus) {
        this.courseStartTime = courseStartTime;
        this.bookmark = bookmark;
        this.timeLeftToCompleteCourse = timeLeftToCompleteCourse;
        this.courseStatus = courseStatus;
    }


    public String getCourseStartTime() {
        return courseStartTime;
    }

    public Bookmark getBookmark() {
        return bookmark;
    }

    public int getTimeLeftToCompleteCourse() {
        return timeLeftToCompleteCourse;
    }

    public String getCourseStatus() {
        return courseStatus;
    }

    public Collection<? extends ValidationError> validate() {
        List<ValidationError> validationErrors = bookmark.validate();
        if (isBlank(courseStatus) || enumFor(courseStatus).equals(UNKNOWN))
            validationErrors.add(new ValidationError(INVALID_COURSE_STATUS));
        if (isBlank(courseStartTime))
            validationErrors.add(new ValidationError(MISSING_TIME.getCode(), MISSING_TIME.getMessage().concat(" for: Course Start Time")));
        if (bookmark == null) {
            validationErrors.add(new ValidationError(MISSING_NODE.getCode(), MISSING_NODE.getMessage().concat(" for: Bookmark")));
            return validationErrors;
        }
        return validationErrors;

    }
}
