package dev.pack.modules.registration_batch.interfaces;

import java.util.Date;

public interface GetAllStudentsByBatch {
    Integer getId();
    String getName();
    String getPhone();
    String getFormulir_Id();
    Date getRegistration_Date();
    String getStatus();
}

