package dev.pack.modules.additional_prices;

import java.util.List;

public interface AdditionalPricesService {

    AdditionalPrices create(AdditionalPrices bodyCreate);
    List<AdditionalPrices> index(Integer regisPathsId);
    AdditionalPrices update(Integer id, AdditionalPrices bodyUpdate);
    void delete(Integer id);

}
