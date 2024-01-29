package dev.pack.modules.registration_batch.interfaces;

import java.util.*;

public interface GetAllStudentForExport {
    Integer getId();
    String getFormulir_Id();
    String getName();
    String getReligion();
    String getPhone();
    String getAddress();
    String getGender();
    String getSchool_Origin();
    String getMajor();
    Date getRegistration_Date();
}