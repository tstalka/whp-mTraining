package org.motechproject.whp.mtraining.web.domain;

import org.hamcrest.core.Is;
import org.joda.time.DateTime;
import org.junit.Test;
import org.motechproject.mtraining.util.ISODateTimeUtil;
import org.motechproject.whp.mtraining.csv.domain.CallLogRecordType;

import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class CallLogRecordTest {

    @Test
    public void shouldValidateValidCallLogRecord() {
        DateTime now = ISODateTimeUtil.nowInTimeZoneUTC();
        DateTime tenMinutesAddedToNow = now.plusMinutes(10);
        CallLogRecord callLogRecord = new CallLogRecord(UUID.randomUUID(), 1, CallLogRecordType.MESSAGE.name(), now.toString(), tenMinutesAddedToNow.toString());
        List<ValidationError> validationErrors = callLogRecord.validate();
        assertThat(validationErrors.size(), Is.is(0));
    }

    @Test
    public void shouldValidateValidInvalidCallLogRecord() {
        CallLogRecord invalidCallLogRecord = new CallLogRecord(null, null, "INVALID", null, null);
        List<ValidationError> validationErrors = invalidCallLogRecord.validate();
        assertThat(validationErrors.size(), Is.is(4));
        assertTrue(validationErrors.contains(new ValidationError(ResponseStatus.MISSING_CONTENT_ID.getCode())));
        assertTrue(validationErrors.contains(new ValidationError(ResponseStatus.MISSING_VERSION.getCode())));
        assertTrue(validationErrors.contains(new ValidationError(ResponseStatus.INVALID_CALL_LOG_TYPE.getCode())));
        assertTrue(validationErrors.contains(new ValidationError(ResponseStatus.MISSING_TIME.getCode())));
    }

    @Test
    public void shouldValidateValidInvalidCallLogRecordWithInvalidTime() {
        DateTime now = ISODateTimeUtil.nowInTimeZoneUTC();
        CallLogRecord invalidCallLogRecord = new CallLogRecord(UUID.randomUUID(), 1, "MESSAGE", now.toString(), "31-31-2014T00:00:00.000Z");
        List<ValidationError> validationErrors = invalidCallLogRecord.validate();
        assertThat(validationErrors.size(), Is.is(1));
        assertTrue(validationErrors.contains(new ValidationError(ResponseStatus.INVALID_DATE_TIME.getCode())));
    }

}
