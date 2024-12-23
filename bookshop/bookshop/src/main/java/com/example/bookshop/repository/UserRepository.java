package com.example.bookshop.repository;

import com.example.bookshop.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Collection;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
    User findByEmail(String email);

    List<User> findAllByRole(int role);
    @Query("SELECT COUNT(o) FROM Order o WHERE o.user.username = :username AND MONTH(o.orderDate) = MONTH(CURRENT_DATE())")

    int countOrderCurrentMonthByUsername(String username);


}

