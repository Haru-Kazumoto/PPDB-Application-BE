package dev.pack.modules.staging;

import dev.pack.modules.enums.Grade;

public interface GetAllStaging {
    Integer getId();

    String getName();

    Integer getIndex();

    Grade getGrade();

    Integer getIs_done();
}
