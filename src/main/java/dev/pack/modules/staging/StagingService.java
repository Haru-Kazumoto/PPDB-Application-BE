package dev.pack.modules.staging;

import dev.pack.modules.enums.FormPurchaseType;

import java.util.List;

public interface StagingService {
    List<GetAllStaging> getAllByStudent(FormPurchaseType type);

}