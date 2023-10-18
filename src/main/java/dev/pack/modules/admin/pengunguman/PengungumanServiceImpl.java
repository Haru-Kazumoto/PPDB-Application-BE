package dev.pack.modules.admin.pengunguman;

import dev.pack.exception.DataNotFoundException;
import dev.pack.modules.admin.gelombang_ppdb.Gelombang;
import dev.pack.modules.admin.gelombang_ppdb.GelombangRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.DateTimeException;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PengungumanServiceImpl implements PengungumanService {

    private final PengungumanRepository pengungumanRepository;
    private final GelombangRepository gelombangRepository;

    @Override
    public Pengunguman store(Pengunguman bodyCreate) {
        Gelombang gelombang = this.gelombangRepository.findById(bodyCreate.getGelombang_id())
                .orElseThrow(() -> new DataNotFoundException("Id not found."));

        bodyCreate.setGelombangId(gelombang);
        bodyCreate.setGelombang_id(bodyCreate.getGelombang_id());

        Date waktuSaatIni = new Date();
        Date waktuPengunguman = bodyCreate.getTanggalPengunguman();

        if(waktuPengunguman.before(waktuSaatIni)){
            throw new DateTimeException("Waktu pengunguman tidak bisa kurang dari waktu saat ini.");
        }

        return this.pengungumanRepository.save(bodyCreate);
    }

    @Override
    public List<Pengunguman> index(Integer gelombangId) {
        return null;
    }
}
