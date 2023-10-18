package dev.pack.modules.admin.gelombang_ppdb;

import dev.pack.exception.DataNotFoundException;
import dev.pack.modules.admin.jalur_pendaftaran.JalurPendaftaran;
import dev.pack.modules.admin.jalur_pendaftaran.JalurPendaftaranRepository;
import dev.pack.utils.Validator;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GelombangServiceImpl implements GelombangService {

    private final GelombangRepository gelombangRepository;
    private final JalurPendaftaranRepository jalurPendaftaranRepository;

    private final Validator validator;

    @Override
    public Gelombang store(Gelombang bodyCreate) {
        JalurPendaftaran data = this.jalurPendaftaranRepository.findById(bodyCreate.getJalurPendaftaran_id())
                .orElseThrow(() -> new DataNotFoundException("Id not found."));

        Date waktuDibuka = bodyCreate.getWaktuPendaftaranDibuka();
        Date waktuDitutup = bodyCreate.getWaktuPendaftaranDitutup();

        validator.dateValidate(waktuDibuka, waktuDitutup);

        bodyCreate.setJalurPendaftaran_id(bodyCreate.getJalurPendaftaran_id());
        bodyCreate.setJalurPendaftaranId(data);

        return this.gelombangRepository.save(bodyCreate);
    }

    @Override
    public List<Gelombang> index(Integer jalurPendaftaranId) {
        var data = this.jalurPendaftaranRepository.findById(jalurPendaftaranId)
                .orElseThrow(() -> new DataNotFoundException("Id not found."));

        return this.gelombangRepository.findAllByJalurPendaftaranId(data.getId());
    }

    @Override
    @Transactional
    public Gelombang update(Integer id, Gelombang bodyUpdate) {
        Gelombang data = this.gelombangRepository.findById(id).orElseThrow(() -> new DataNotFoundException("Id not found."));

        Date waktuDibuka = bodyUpdate.getWaktuPendaftaranDibuka();
        Date waktuDitutup = bodyUpdate.getWaktuPendaftaranDitutup();

        validator.dateValidate(waktuDibuka, waktuDitutup);

        data.setNamaGelombang(bodyUpdate.getNamaGelombang());
        data.setDiskonGelombang(bodyUpdate.getDiskonGelombang());
        data.setWaktuPendaftaranDibuka(waktuDibuka);
        data.setWaktuPendaftaranDitutup(waktuDitutup);
        data.setNamaBank(bodyUpdate.getNamaBank());
        data.setNomorRekening(bodyUpdate.getNomorRekening());
        data.setNamaPemilikRekening(bodyUpdate.getNamaPemilikRekening());
        data.setBiayaPendaftaran(bodyUpdate.getBiayaPendaftaran());

        return this.gelombangRepository.save(data);
    }
}
