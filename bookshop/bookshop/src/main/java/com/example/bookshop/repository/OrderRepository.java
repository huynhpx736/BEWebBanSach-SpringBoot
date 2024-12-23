package com.example.bookshop.repository;

import com.example.bookshop.entity.Order;
import com.example.bookshop.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    //viet hàm tìm đơn hàng theo id người dùng và trạng thái la PENDING
    @Query("SELECT o FROM Order o WHERE o.user.id = :userId AND o.status = 'PENDING'")
    <Optional> Order FindOrderIsPending(Integer userId);
//    Order FindOrderIsPending(Integer userId);

//    <List> Order findById(Integer id);


//    Optional<Order> findByUserIdAndStatus(Integer userId, String status);
    //ham tim danh sach don hang theo id nguoi dung va trang thai
    List<Order> findByUserIdAndStatus(Integer userId, String status);

    //hàm tìm đơn hàng theo id người dùng biet trong don hang co nguoi dung va nguoi dung co id
    List<Order> findByUserId(Integer userId);
    //ham tra ve danh sach tat ca don hang theo id nguoi dung va trang thai don hang
    @Query("SELECT o FROM Order o WHERE o.user.id = :userId AND o.status = :status")
    List<Order> findAllByUserIdAndStatus(Integer userId, String status);

    List<Order> findByStatus(String status);
//    @Query("SELECT COUNT(o) FROM Order o WHERE o.user.id = :id AND o.status = 'COMPLETED'")
//    int countCompleteByUserId(Integer id);
//    @Query("SELECT SUM(o.total) FROM Order o WHERE o.user.id = :id AND o.status = 'COMPLETED'")
//    float sumTotaCompletedlByUserId(Integer id);

    //hàm đếm số lượng đơn hàng trong tháng hiện tại của người dùng theo username
//    @Query("SELECT COUNT(o) FROM Order o WHERE o.user.username = :username AND MONTH(o.createdAt) = MONTH(CURRENT_DATE())")
//
//    int countOrderCurrentMonthByUsername(String username);

    //hàm đếm số lượng đơn hàng trong tháng hiện tại đã hoàn thành của người dùng COMPLETED
    @Query("SELECT COUNT(o) FROM Order o WHERE o.user.id = :id AND o.status = 'COMPLETED' AND MONTH(o.orderDate) = MONTH(CURRENT_DATE())")
    Integer countCompleteByUserId(Integer id);
    @Query("SELECT SUM(o.total) FROM Order o WHERE o.user.id = :id AND o.status = 'COMPLETED' AND MONTH(o.orderDate) = MONTH(CURRENT_DATE())")
    Float sumTotaCompletedlByUserId(Integer id);

    List<Order> findByStatusInAndShipperIsNull(List<String> pending);

    List<Order> findByShipperIdAndStatus(Integer shipperId, String status);

    List<Order> findAllByShipperId(Integer shipperId);

    List<Order> findAllByShipperIdAndStatus(Integer shipperId, String status);
//    Collection<Object> findAllByUserIdAndStatus(Integer userId, String status);
//    Optional<Order> findByUserId(Integer userId);
//    Optional<Order> findActiveOrderByUserId(Integer userId);
    //hàm tìm đơn hàng theo trạng thái
//giai thich Optional la kieu the nao

}
