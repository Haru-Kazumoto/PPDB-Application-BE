package dev.pack.modules.additional_prices;

import dev.pack.exception.DataNotFoundException;
import dev.pack.modules.prices.PriceDetails;
import dev.pack.modules.prices.PricesRepository;
import dev.pack.modules.registration_paths.RegistrationPaths;
import dev.pack.modules.registration_paths.RegistrationPathsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static dev.pack.constraint.ErrorMessage.*;

@Service
@RequiredArgsConstructor
public class AdditionalPricesServiceImpl implements AdditionalPricesService{

    private final AdditionalPricesRepository additionalPricesRepository;
    private final RegistrationPathsRepository registrationPathsRepository;
    private final PricesRepository pricesRepository;

    @Override
    public AdditionalPrices create(AdditionalPrices bodyCreate) {
        RegistrationPaths registrationPaths = this.registrationPathsRepository.findById(bodyCreate.getPath_id())
                .orElseThrow(() -> new DataNotFoundException(REGISTRATION_PATHS_ID_NOT_FOUND));

        List<PriceDetails> prices = new ArrayList<>();

        AdditionalPrices additionalPrices = AdditionalPrices.builder()
                .namePrice(bodyCreate.getNamePrice())
                .path_id(bodyCreate.getPath_id())
                .priceDetails(prices)
                .registrationPaths(registrationPaths)
                .build();

        additionalPrices = this.additionalPricesRepository.save(additionalPrices);

        for(PriceDetails priceDetailsModel : bodyCreate.getPriceDetails()){
            PriceDetails priceDetailsData = PriceDetails.builder()
                    .subTitle(priceDetailsModel.getSubTitle())
                    .price(priceDetailsModel.getPrice())
                    .additionalPrices(additionalPrices)
                    .build();

            prices.add(priceDetailsData);
        }

        this.pricesRepository.saveAll(prices);

        return additionalPrices;
    }

    @Override
    public AdditionalPrices update(Integer id, AdditionalPrices bodyUpdate) {
        AdditionalPrices dataUpdate = this.additionalPricesRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException(ADDITIONAL_PRICE_ID_NOT_FOUND));

        List<PriceDetails> pricesUpdate = dataUpdate.getPriceDetails();
        pricesUpdate.clear(); //delete existing and replace with updated field.

        dataUpdate.setNamePrice(bodyUpdate.getNamePrice());
        dataUpdate.setPriceDetails(pricesUpdate);

        dataUpdate = this.additionalPricesRepository.save(dataUpdate);

        for(PriceDetails priceDetailsModel : bodyUpdate.getPriceDetails()){
            PriceDetails priceDetailsData = PriceDetails.builder()
                    .subTitle(priceDetailsModel.getSubTitle())
                    .price(priceDetailsModel.getPrice())
                    .additionalPrices(dataUpdate)
                    .build();

            pricesUpdate.add(priceDetailsData);
        }

        this.pricesRepository.saveAll(pricesUpdate);


        return dataUpdate;
    }

    @Override
    public List<AdditionalPrices> index(Integer regisPathsId) {
        this.registrationPathsRepository.findById(regisPathsId)
                .orElseThrow(() -> new DataNotFoundException(REGISTRATION_PATHS_ID_NOT_FOUND));

        return this.additionalPricesRepository.findAllByRegistrationPathsId(regisPathsId);
    }


    @Override
    public void delete(Integer id) {
        AdditionalPrices data = this.additionalPricesRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException(ADDITIONAL_PRICE_ID_NOT_FOUND));

        this.additionalPricesRepository.delete(data);
    }
}
