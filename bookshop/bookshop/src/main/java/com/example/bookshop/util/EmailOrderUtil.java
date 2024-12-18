package com.example.bookshop.util;

import com.example.bookshop.entity.Order;
import com.example.bookshop.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

public class EmailOrderUtil {

    public static String getText(String Subject,String name, String orderId, LocalDateTime orderDate, double orderTotal,String cancelReason) {
        if (Subject.equals("Xác nhận duyêt đơn hàng")) {
            return "Xin chào " + name + ",\n\n" +
                    "Cảm ơn bạn đã đặt hàng tại BookShop. Đơn hàng của bạn đã được xác nhận.\n" +
                    "Mã đơn hàng: " + orderId + "\n" +
                    "Ngày đặt hàng: " + orderDate + "\n" +
                    "Tổng tiền: " + orderTotal + " VND\n\n" +
                    "Chúng tôi sẽ giao hàng trong thời gian sớm nhất.\n" +
                    "Nếu có bất kỳ thắc mắc nào, vui lòng liên hệ với chúng tôi qua email:xuanhuynh254@gmail.com " +
                    "hoặc số điện thoại 0865070736.\n\n"+
                    "Thân ái,\n" +
                    "BookShop";

    }
        else if (Subject.equals("Xác nhận hủy đơn hàng")) {
            return "Xin chào " + name + ",\n\n" +
                    "Cảm ơn bạn đã đặt hàng tại BookShop. Đơn hàng của bạn đã bị hủy.\n" +
                    "Mã đơn hàng: " + orderId + "\n" +
                    "Ngày đặt hàng: " + orderDate + "\n" +
                    "Tổng tiền: " + orderTotal + " VND\n\n" +
                    "Lý do hủy: " + cancelReason + "\n\n" +
                    "Nếu có bất kỳ thắc mắc nào, vui lòng liên hệ với chúng tôi qua email: xuanhuynh254@gmail.com " +
                    "hoặc số điện thoại 0865070736.\n\n"+
                    "Thân ái,\n" +
                    "BookShop";
        }
        else {
            return "Xin chào " + name + ",\n\n" +
                    "Đây là thông tin đơn hàng của bạn:\n" +
                    "Mã đơn hàng: " + orderId + "\n" +
                    "Ngày đặt hàng: " + orderDate + "\n" +
                    "Tổng tiền: " + orderTotal + " VND\n\n" +
                    "Chúng tôi hiện không thể liên lạc với bạn để giao hàng nhưng không thành công.\n" +
                    "Vui lòng kiểm tra lại thông tin đơn hàng và liên hệ với chúng tôi để được hỗ trợ. Nếu không, đơn hàng sẽ bị hủy sau 48h.\n" +
                    "Vui lòng liên hệ với chúng tôi qua email:xuanhuynh254@gmail.com " +
                    "hoặc số điện thoại 0865070736.\n\n"+
                    "Thân ái,\n" +
                    "BookShop";
        }
    }


    // xacs nhan huy don

}
