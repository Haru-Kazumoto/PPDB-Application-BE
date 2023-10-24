package dev.pack.modules.additional_prices;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdditionalPricesRepository extends JpaRepository<AdditionalPrices, Integer> {
}
