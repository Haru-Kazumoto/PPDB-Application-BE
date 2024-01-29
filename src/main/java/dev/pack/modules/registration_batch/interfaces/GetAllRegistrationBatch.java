package dev.pack.modules.registration_batch.interfaces;

import java.util.Date;

public interface GetAllRegistrationBatch {
    Long getId();
    String getName();
    Integer getMax_quota();
    String getBatch_code();
    Date getStart_date();
    Date getEnd_date();
    String getBank_name();
    String getBank_user();
    Double getPrice();
    String getBank_account();
    Integer getPath_id();
    Long getCountStudent();

}