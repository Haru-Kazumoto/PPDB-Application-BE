package dev.pack.modules.registerPath.publicInformation.aditionalCost.cost;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CostRepository extends JpaRepository<Cost, Integer> {
}
