package dev.pack.modules.admin.kegiatan;

import dev.pack.exception.DataNotFoundException;
import dev.pack.modules.admin.gelombang_ppdb.Gelombang;
import dev.pack.modules.admin.gelombang_ppdb.GelombangRepository;
import dev.pack.utils.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class KegiatanServiceImpl implements KegiatanService {

    private final KegiatanRepository kegiatanRepository;
    private final GelombangRepository gelombangRepository;
    private final Validator validate;

    @Override
    public Kegiatan store(Kegiatan bodyCreate) {
        Gelombang data = this.gelombangRepository.findById(bodyCreate.getGelombang_id())
                .orElseThrow(() -> new DataNotFoundException("Id not found."));

        bodyCreate.setGelombangId(data);
        bodyCreate.setGelombang_id(bodyCreate.getGelombang_id());

        Date waktuDibuka = bodyCreate.getWaktuDibuka();
        Date waktuDitutup = bodyCreate.getWaktuDitutup();

        this.validate.dateValidate(waktuDibuka, waktuDitutup);

        return this.kegiatanRepository.save(bodyCreate);
    }

    @Override
    public List<Kegiatan> index(Integer id) {
        return null;
    }

    @Override
    public Kegiatan update(Kegiatan bodyUpdate) {
        return null;
    }
}
