package dev.pack.modules.admin.struk_pembayaran;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StrukPembayaranRepository extends JpaRepository<StrukPembayaran, Integer> {
}