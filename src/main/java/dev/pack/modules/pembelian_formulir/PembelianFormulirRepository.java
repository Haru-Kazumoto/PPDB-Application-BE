package dev.pack.modules.pembelian_formulir;

import dev.pack.modules.pengembalian_formulir.PengembalianFormulir;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PembelianFormulirRepository extends JpaRepository<PembelianFormulir, Integer> {
}
