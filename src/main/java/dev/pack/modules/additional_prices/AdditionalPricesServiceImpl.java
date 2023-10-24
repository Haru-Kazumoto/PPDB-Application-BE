package dev.pack.modules.additional_prices;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdditionalPricesServiceImpl implements AdditionalPricesService{

    private final AdditionalPricesRepository additionalPricesRepository;

    @Override
    public AdditionalPrices create(AdditionalPrices bodyCreate) {
        return null;
    }

    @Override
    public List<AdditionalPrices> index() {
        return null;
    }

    @Override
    public AdditionalPrices update(Integer id, AdditionalPrices bodyUpdate) {
        return null;
    }

    @Override
    public void delete(Integer id) {

    }
}
