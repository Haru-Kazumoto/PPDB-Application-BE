package dev.pack.modules.admin.pengunguman;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PengungumanRepository extends JpaRepository<Pengunguman, Integer> {



}
