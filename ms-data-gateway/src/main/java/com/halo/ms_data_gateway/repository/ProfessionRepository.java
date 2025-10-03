package com.halo.ms_data_gateway.repository;

import com.halo.ms_data_gateway.entity.Profession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProfessionRepository extends JpaRepository<Profession, Long> {

    Optional<Profession> findByCode(String code);

    boolean existsByCode(String code);

    List<Profession> findAllByOrderByLabelAsc();
}
