
package com.example.bookshop.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data

public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "shipper_id", referencedColumnName = "id")
    private User shipper;
    @Column(name = "order_date",  columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime orderDate;

    private String status;

    private Float total;

    @Column(name = "shipping_fee")
    private Float shippingFee;

    private Float discount;

    @Column(name = "receiver_phone")
    private String receiverPhone;

    @Column(name = "receiver_address")
    private String receiverAddress;

    @Column(name = "receiver_name")
    private String receiverName;

    @Column(name = "created_at", insertable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", insertable = false, updatable = false)
    private LocalDateTime updatedAt;

    @Column(name = "cancel_reason")
    private String cancelReason;
    @Column(name = "failure_reason")
    private String failureReason;
    @Column(name = "shipper_note")
    private String shipperNote;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
//    @ToString.Exclude // Ngăn vòng lặp
    private List<OrderDetail> orderDetails;
}
