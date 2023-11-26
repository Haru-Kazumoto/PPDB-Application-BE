package dev.pack.modules.registration_batch;

import java.util.Date;

public interface GetAllStudentsByBatch {
    Integer getId();
    String getName();
    String getPhone();
    Date getRegistration_date();
    String getStatus();
}

