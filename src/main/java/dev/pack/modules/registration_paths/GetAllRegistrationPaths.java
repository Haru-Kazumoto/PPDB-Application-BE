package dev.pack.modules.registration_paths;

import java.util.Date;

public interface GetAllRegistrationPaths {
    Long getId();
    String getName();
    String getType();

    Date getStart_date();
    Date getEnd_date();

    Double getPrice();

    Long getTotal_pendaftar();
}
