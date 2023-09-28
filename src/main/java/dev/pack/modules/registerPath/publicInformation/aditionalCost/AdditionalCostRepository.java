package dev.pack.modules.registerPath.publicInformation.aditionalCost;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdditionalCostRepository extends JpaRepository<AdditionalCost, Integer> {
}
