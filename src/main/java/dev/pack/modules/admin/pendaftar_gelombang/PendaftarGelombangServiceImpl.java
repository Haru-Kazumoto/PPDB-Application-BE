package dev.pack.modules.admin.pendaftar_gelombang;

import dev.pack.modules.admin.gelombang_ppdb.GelombangRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PendaftarGelombangServiceImpl implements PendaftarGelombangService {

    private final PendaftarGelombangRepository pendaftarGelombangRepository;
    private final GelombangRepository gelombangRepository;

    @Override
    public PendaftarGelombang store(PendaftarGelombang bodyCreate) {
        return null;
    }

    @Override
    public List<PendaftarGelombang> index(Integer modelId) {
        return null;
    }
}
