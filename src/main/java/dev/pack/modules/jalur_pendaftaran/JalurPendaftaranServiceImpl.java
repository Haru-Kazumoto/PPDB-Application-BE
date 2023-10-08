package dev.pack.modules.jalur_pendaftaran;

import dev.pack.exception.DataNotFoundException;
import dev.pack.exception.DuplicateDataException;
import dev.pack.modules.user.User;
import dev.pack.modules.user.UserRepository;
import dev.pack.utils.Validator;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class JalurPendaftaranServiceImpl implements JalurPendaftaranService{

    private final JalurPendaftaranRepository jalurPendaftaranRepository;
    private final UserRepository userRepository;

    private final Validator validator;

    @Override
    public JalurPendaftaran store(JalurPendaftaran bodyCreate) {
        this.jalurPendaftaranRepository.findByNamaJalurPendaftaran(bodyCreate.getNamaJalurPendaftaran())
                .ifPresent((namaJalur) -> {
                    throw new DuplicateDataException("Jalur pendaftaran name has been created.");
                });

        User user = this.userRepository.findById(bodyCreate.getUser_id())
                .orElseThrow(() -> new DataNotFoundException("Id user not found."));

        bodyCreate.setUser_id(bodyCreate.getUser_id());
        bodyCreate.setUserId(user);

        Date waktuDibuka = bodyCreate.getWaktuDibuka();
        Date waktuDitutup = bodyCreate.getWaktuDitutup();

        validator.dateValidate(waktuDibuka, waktuDitutup);

        return this.jalurPendaftaranRepository.save(bodyCreate);
    }

    @Override
    public List<JalurPendaftaran> index(Integer id) {
        var data = this.userRepository
                .findById(id)
                .orElseThrow(() -> new DataNotFoundException("Id not found."));

        return this.jalurPendaftaranRepository.findAllByUserId(data.getId());
    }

    @Override
    public JalurPendaftaran update(Integer id, JalurPendaftaran bodyUpdate) {
        JalurPendaftaran data = this.jalurPendaftaranRepository.findById(id).orElseThrow(() -> new DataNotFoundException("Id not found."));

        this.jalurPendaftaranRepository.findByNamaJalurPendaftaran(bodyUpdate.getNamaJalurPendaftaran())
                        .ifPresent((jalur) -> {
                            throw new DuplicateDataException("Nama jalur pendaftaran sudah ada.");
                        });

        validator.dateValidate(bodyUpdate.getWaktuDibuka(), bodyUpdate.getWaktuDitutup());

        data.setTipePembelian(bodyUpdate.getTipePembelian());
        data.setNamaJalurPendaftaran(bodyUpdate.getNamaJalurPendaftaran());
        data.setWaktuDibuka(bodyUpdate.getWaktuDibuka());
        data.setWaktuDitutup(bodyUpdate.getWaktuDitutup());
        data.setBiayaPendaftaran(bodyUpdate.getBiayaPendaftaran());

        return this.jalurPendaftaranRepository.save(data);
    }
}
