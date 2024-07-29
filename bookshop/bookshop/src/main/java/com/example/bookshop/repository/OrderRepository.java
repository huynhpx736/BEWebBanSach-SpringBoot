package com.example.bookshop.repository;

import com.example.bookshop.entity.Order;
import com.example.bookshop.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    //hàm tìm đơn hàng theo id người dùng và trạng thái
    Optional<Order> findByUserIdAndStatus(Integer userId, String status);

    //hàm tìm đơn hàng theo id người dùng
    Optional<Order> findByUserId(Integer userId);
    Optional<Order> findActiveOrderByUserId(Integer userId);
    //hàm tìm đơn hàng theo trạng thái

}
