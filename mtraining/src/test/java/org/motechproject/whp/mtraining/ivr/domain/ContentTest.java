package org.motechproject.whp.mtraining.ivr.domain;

import org.junit.Test;
import org.motechproject.whp.mtraining.csv.domain.Content;

import static com.google.common.collect.Lists.newArrayList;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ContentTest {
    @Test
    public void shouldSetStatus() {
        Content contentWithActiveStatus = new Content("name", "module", "active", "desc", "fileName", 0, newArrayList("1", "2"), "1", "answerFilename", 90.0, "Content Author");
        assertTrue(contentWithActiveStatus.isActive());

        Content contentWithInActiveStatus = new Content("name", "module", "inActive", "desc", "fileName", 0, newArrayList("1", "2"), "1", "answerFilename", 90.0, "Content Author");
        assertFalse(contentWithInActiveStatus.isActive());

        Content contentWithBlankStatus = new Content("name", "module", "", "desc", "fileName", 0, newArrayList("1", "2"), "1", "answerFilename", 90.0, "Content Author");
        assertTrue(contentWithBlankStatus.isActive());
    }
}
