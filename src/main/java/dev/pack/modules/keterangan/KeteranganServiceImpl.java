package dev.pack.modules.keterangan;

import dev.pack.exception.DataNotFoundException;
import dev.pack.exception.ErrorSoftDelete;
import dev.pack.modules.jalur_pendaftaran.JalurPendaftaran;
import dev.pack.modules.jalur_pendaftaran.JalurPendaftaranRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class KeteranganServiceImpl implements KeteranganService{

    private final KeteranganRepository keteranganRepository;
    private final JalurPendaftaranRepository jalurPendaftaranRepository;

    @Override
    public Keterangan store(Keterangan bodyCreate) {
        JalurPendaftaran data = this.jalurPendaftaranRepository.findById(bodyCreate.getJalurPendaftaran_id())
                .orElseThrow(() -> new DataNotFoundException("Id not found."));

        bodyCreate.setJalurPendaftaran_id(bodyCreate.getJalurPendaftaran_id());
        bodyCreate.setJalurPendaftaranId(data);

        return this.keteranganRepository.save(bodyCreate);
    }

    @Override
    public List<Keterangan> index(Integer id) {
        var data = this.jalurPendaftaranRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Id not found."));

        return this.keteranganRepository.findAllByJalurPendaftaranId(data.getId());
    }

    @Override
    public Keterangan update(Integer id, Keterangan bodyUpdate) {
        Keterangan data = this.keteranganRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Data not found."));

        if(data.getDeletedAt() != null) throw new ErrorSoftDelete("Data has been deleted, cannot update.");

        data.setNamaKeterangan(bodyUpdate.getNamaKeterangan());
        data.setDeskripsiKeterangan(bodyUpdate.getDeskripsiKeterangan());

        return this.keteranganRepository.save(data);
    }

    @Override
    public void softDelete(Integer id) {
        Keterangan data = this.keteranganRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Id not found."));
        if(data.getDeletedAt() != null) throw new IllegalArgumentException(String.format("Data has been deleted since %s", data.getDeletedAt()));

        this.keteranganRepository.softDeleteById(data.getId());
    }

    /**
     * TODO : jadi ada issue nih, kan kita make softdelete dan kasusnya adalah unique field tidak bisa
     *        duplikasi. Jadi gimana kalo misal ada data yang punya unique field dan sudah dihapus (deletedAt) dan
     *        mau di restore? otomatis data tersebut terdapat duplikasi atau mmenag nanti akan di tolak oleh sistem.
     * @param id
     * @return
     */

    @Override
    public Keterangan restore(Integer id) {
        Keterangan data = this.keteranganRepository.findById(id).orElseThrow(() -> new DataNotFoundException("Id not found."));
        if(data.getDeletedAt() == null) throw new IllegalArgumentException("Data is active or already restored.");
        return this.keteranganRepository.restoreData(data.getId());
    }

    @Override
    public List<Keterangan> indexDeleted(Integer id) {
        Keterangan data = this.keteranganRepository.findById(id).orElseThrow(() -> new DataNotFoundException("Id not found."));
        return this.keteranganRepository.findAllDataHasBeenDeleted(data.getId());
    }
}
