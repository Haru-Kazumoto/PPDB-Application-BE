package dev.pack.modules.biaya;

import dev.pack.exception.DataNotFoundException;
import dev.pack.exception.DuplicateDataException;
import dev.pack.exception.ErrorSoftDelete;
import dev.pack.modules.biaya_tambahan.BiayaTambahan;
import dev.pack.modules.biaya_tambahan.BiayaTambahanRepository;
import dev.pack.utils.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BiayaServiceImpl implements BiayaService {

    private final BiayaRepository biayaRepository;
    private final BiayaTambahanRepository biayaTambahanRepository;
    private final Validator validator;

    //TODO : CRUD MANAGEMENT BIAYAx

    @Override
    public Biaya store(Biaya bodyCreate) {
        BiayaTambahan data = this.biayaTambahanRepository.findById(bodyCreate.getBiayaTambahan_id())
                .orElseThrow(() -> new DataNotFoundException("Id not found."));

        this.biayaRepository.findByNamaBiayaTambahan(bodyCreate.getNamaBiayaTambahan())
                .ifPresent((nama) -> {
                    throw new DuplicateDataException("Nama biaya tambahan has already exists.");
                });

        bodyCreate.setBiayaTambahan_id(bodyCreate.getBiayaTambahan_id());
        bodyCreate.setBiayaTambahanId(data);

        return this.biayaRepository.save(bodyCreate);
    }

    @Override
    public List<Biaya> index(Integer biayaTambahanId) {
        var data = this.biayaTambahanRepository.findById(biayaTambahanId)
                .orElseThrow(() -> new DataNotFoundException("Id not found."));

        return this.biayaRepository.findAllByBiayaTambahanId(data.getId());
    }

    @Override
    public Biaya update(Integer id, Biaya bodyUpdate) {
        Biaya data = this.biayaRepository.findById(id).orElseThrow(() -> new DataNotFoundException("Id not found."));

        this.biayaRepository.findByNamaBiayaTambahan(data.getNamaBiayaTambahan())
                .ifPresent((name) -> {
                    throw new DuplicateDataException("Nama biaya tambahan has already exists.");
                });

        validator.isDataHasDeleted(bodyUpdate.getDeletedAt());

        data.setNamaBiayaTambahan(bodyUpdate.getNamaBiayaTambahan());
        data.setJumlahBiayaTambahan(bodyUpdate.getJumlahBiayaTambahan());

        return data;
    }
}
