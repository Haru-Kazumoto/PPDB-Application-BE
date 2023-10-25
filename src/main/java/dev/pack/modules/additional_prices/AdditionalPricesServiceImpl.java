package dev.pack.modules.additional_prices;

import dev.pack.exception.DataNotFoundException;
import dev.pack.modules.prices.Prices;
import dev.pack.modules.prices.PricesRepository;
import dev.pack.modules.registration_paths.RegistrationPaths;
import dev.pack.modules.registration_paths.RegistrationPathsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdditionalPricesServiceImpl implements AdditionalPricesService{

    private final AdditionalPricesRepository additionalPricesRepository;
    private final RegistrationPathsRepository registrationPathsRepository;
    private final PricesRepository pricesRepository;

    @Override
    public AdditionalPrices create(AdditionalPrices bodyCreate) {
        RegistrationPaths registrationPaths = this.registrationPathsRepository.findById(bodyCreate.getPath_id())
                .orElseThrow(() -> new DataNotFoundException("Id regis path not found."));

        List<Prices> prices = new ArrayList<>();

        AdditionalPrices additionalPrices = AdditionalPrices.builder()
                .namePrice(bodyCreate.getNamePrice())
                .price(bodyCreate.getPrice())
                .index(bodyCreate.getIndex())
                .build();

        bodyCreate.setRegistrationPaths(registrationPaths);

        additionalPrices = this.additionalPricesRepository.save(additionalPrices);

        for(Prices pricesModel : bodyCreate.getPrices()){
            Prices pricesData = Prices.builder()
                    .subTitle(pricesModel.getSubTitle())
                    .price(pricesModel.getPrice())
                    .additionalPrices(additionalPrices)
                    .build();

            prices.add(pricesData);
        }

        this.pricesRepository.saveAll(prices);

        return additionalPrices;
    }

    @Override
    public List<AdditionalPrices> index(Integer regisPathsId) {
        this.registrationPathsRepository.findById(regisPathsId)
                .orElseThrow(() -> new DataNotFoundException("Registration Paths id not found."));

        return this.additionalPricesRepository.findAllByRegistrationPathsId(regisPathsId);
    }

    @Override
    public AdditionalPrices update(Integer id, AdditionalPrices bodyUpdate) {
        AdditionalPrices data = this.additionalPricesRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Additional prices id not found."));



        return null;
    }

    @Override
    public void delete(Integer id) {

    }
}
