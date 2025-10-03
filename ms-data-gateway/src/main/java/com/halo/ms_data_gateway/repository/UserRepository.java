package com.halo.ms_data_gateway.repository;

import com.halo.ms_data_gateway.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByUsername(String username);

    boolean existsByUsername(String username);

    boolean existsByCustomer_Id(Long customerId);
}