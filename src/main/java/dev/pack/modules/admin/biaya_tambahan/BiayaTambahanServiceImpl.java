package dev.pack.modules.admin.biaya_tambahan;

import dev.pack.exception.DataNotFoundException;
import dev.pack.modules.admin.jalur_pendaftaran.JalurPendaftaran;
import dev.pack.modules.admin.jalur_pendaftaran.JalurPendaftaranRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BiayaTambahanServiceImpl implements BiayaTambahanService{

    public final BiayaTambahanRepository biayaTambahanRepository;
    public final JalurPendaftaranRepository jalurPendaftaranRepository;

    //TODO : BUAT 1 SERVICE UNTUK PEMBUATAN 2 MODEL BIAYA TAMBAHAN SERTA BIAYA.

    @Override
    public BiayaTambahan store(BiayaTambahan bodyCreate) {
        JalurPendaftaran data = this.jalurPendaftaranRepository.findById(bodyCreate.getJalurPendaftaran_id())
                .orElseThrow(() -> new DataNotFoundException("Data not found."));

        bodyCreate.setJalurPendaftaran_id(bodyCreate.getJalurPendaftaran_id());
        bodyCreate.setJalurPendaftaranId(data);

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
