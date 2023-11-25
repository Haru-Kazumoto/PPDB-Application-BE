package dev.pack.modules.registration_batch;

import java.util.Date;

public interface GetAllRegistrationBatch {

    public Long getId();

    public String getName();

    public Date getStart_date();

    public Date getEnd_date();

    public Long getCountStudent();
}
