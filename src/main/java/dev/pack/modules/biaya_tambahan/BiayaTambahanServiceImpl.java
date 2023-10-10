package dev.pack.modules.biaya_tambahan;

import dev.pack.exception.DataNotFoundException;
import dev.pack.modules.jalur_pendaftaran.JalurPendaftaran;
import dev.pack.modules.jalur_pendaftaran.JalurPendaftaranRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BiayaTambahanServiceImpl implements BiayaTambahanService{

    public final BiayaTambahanRepository biayaTambahanRepository;
    public final JalurPendaftaranRepository jalurPendaftaranRepository;

    @Override
    public BiayaTambahan store(BiayaTambahan bodyCreate) {
        return this.biayaTambahanRepository.save(bodyCreate);
    }

    @Override
    public List<BiayaTambahan> index(Integer biayaTambahanId) {
        JalurPendaftaran data = this.jalurPendaftaranRepository
                .findById(biayaTambahanId).orElseThrow(() -> new DataNotFoundException("Id not found."));

        return this.biayaTambahanRepository.findAllByJalurPendaftaranId(data.getId());
    }

    @Override
    public List<BiayaTambahan> indexDeleted() {
        return null;
    }

    @Override
    public BiayaTambahan update(Integer id, BiayaTambahan bodyUpdate) {
        BiayaTambahan data = this.biayaTambahanRepository.findById(id).orElseThrow(() -> new DataNotFoundException("Id not found."));
        data.setJudulBiaya(bodyUpdate.getJudulBiaya());
        return data;
    }

    @Override
    public void softDelete(Integer id) {
        //Not implemented yet.
    }
}
