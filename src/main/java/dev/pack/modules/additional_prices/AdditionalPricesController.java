package dev.pack.modules.additional_prices;

import dev.pack.payloads.HttpResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v${application.version}/admin/additional-prices")
public class AdditionalPricesController {

    private final AdditionalPricesService additionalPricesService;
    private final HttpResponse http;
    private final ModelMapper modelMapper;

    @PostMapping(path = "/post")
    public ResponseEntity<?> create(@RequestBody @Valid AdditionalPricesDto.onCreate dto){
        AdditionalPrices entity = this.modelMapper.map(dto, AdditionalPrices.class);

        return this.http.response(HttpStatus.CREATED.value(), new Date(), this.additionalPricesService.create(entity));
    }

    @GetMapping(path = "/index")
    public ResponseEntity<?> index(@RequestParam("pathId") Integer pathId){
        return this.http.response(HttpStatus.OK.value(), new Date(), this.additionalPricesService.index(pathId));
    }
}
