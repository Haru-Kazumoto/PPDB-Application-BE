package dev.pack.modules.registration_batch;

import java.util.*;

public interface GetAllStudentForExport {
    Integer getId();
    String getFormulir_Id();
    String getLast_Inserted_Number();
    String getName();
    String getReligion();
    String getPhone();
    String getAddress();
    String getGender();
    String getSchool_Origin();
    String getMajor();
    Date getRegistration_Date();
}