package com.halo.ms_data_gateway.repository;

import com.halo.ms_data_gateway.entity.Customer;
import com.halo.ms_data_gateway.entity.Request;
import com.halo.ms_data_gateway.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Comparator;
import java.util.List;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {

    List<Request> findAllByUser(User user);

    List<Request> findAllByUser_Customer(Customer customer);

    default List<Request> findAllByUserOrderByCreatedDesc(User user) {
        return findAllByUser(user).stream()
                .sorted(Comparator.comparing(Request::getCreatedAt, Comparator.nullsLast(Comparator.naturalOrder())).reversed())
                .toList();
    }

    default List<Request> findAllByCustomerOrderByCreatedDesc(Customer customer) {
        return findAllByUser_Customer(customer).stream()
                .sorted(Comparator.comparing(Request::getCreatedAt, Comparator.nullsLast(Comparator.naturalOrder())).reversed())
                .toList();
    }

    List<Request> findAllByUser_CustomerId(Long customerId);

    List<Request> findAllByUser_Customer_Id(Long customerId);

    List<Request> findAllByUser_IdOrderByCreatedAtDesc(Long userId);

    List<Request> findAllByUser_Customer_IdOrderByCreatedAtDesc(Long customerId);

    boolean existsByProfession_Id(Long professionId);

    boolean existsByUser_Id(Long userId);

    boolean existsByUser_Customer_Id(Long customerId);
}
