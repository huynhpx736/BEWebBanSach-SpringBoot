package com.example.bookshop.service.impl;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import com.example.bookshop.entity.Order;
import com.example.bookshop.payload.EmailDetails;
import com.example.bookshop.repository.OrderRepository;
import com.example.bookshop.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.mail.javamail.MimeMessageHelper;
@Service
public class EmailServiceImpl implements EmailService {
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    OrderRepository orderRepository;
    @Value("${spring.mail.username}") private String sender;
    @Override
    public void sendSimpleMessage(String to, String subject, String text) {
        return;
    }

    @Override
    public String sendSimpleMail(EmailDetails details) {
        Order order = orderRepository.findById(details.getOrderId()).orElse(null);


        try {
//            SimpleMailMessage mailMessage = new SimpleMailMessage();
//            mailMessage.setFrom(sender);
//            mailMessage.setTo(details.getTo());
//            mailMessage.setSubject(details.getSubject());
////            mailMessage.setText(details.getText());
//            mailMessage.setText("Chao "+order.getUser().getFullname()+", </p>\n" +
//                    "<p>Th&ocirc;ng b&aacute;o từ của h&agrave;ng s&aacute;ch về đơn h&agrave;ng m&atilde; {{order_id}}.</p>\n" +
//                    "<p>Thời gian đặt h&agrave;ng: {{order_date}}.</p>\n" +
//                    "<p>Gi&aacute; trị đơn h&agrave;ng: <span style=\"color: #e03e2d;\">{{order_total}}</span></p>\n" +
//                    "<p style=\"padding: 12px; border-left: 4px solid #d0d0d0; font-style: italic;\">{{message}}</p>\n" +
//                    "<p>Th&acirc;n &aacute;i,<br>Bookshop</p>");
//            javaMailSender.send(mailMessage);
//            return "Email sent successfully";
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
            mimeMessageHelper.setFrom(sender, "BookShop");
            mimeMessageHelper.setTo(order.getUser().getEmail());
            mimeMessageHelper.setText("""
                            <div>             
                                <p>Chào %s,</p>
                                <p>Thông báo từ cửa hàng sách về đơn hàng mã %d.</p>
                                <p>Thời gian đặt hàng: %s.</p>
                                <p>Giá trị đơn hàng: %f VND</p>
                                <p style="padding: 12px; border-left: 4px solid #d0d0d0; font-style: italic;">%s</p>
                                <p>Số điện thoại liên hệ: 0865070736</p>
                                <p>Email liên hệ: xuanhuynh254@gmail.com</p>
                                <p>Thân ái,<br>BookShop</p>
                            </div>
                            """.formatted(order.getUser().getFullname(), order.getId(), order.getOrderDate(), order.getTotal(), details.getText()), true
                    );
            mimeMessageHelper.setSubject("Thông báo từ cửa hàng sách BookShop");
            javaMailSender.send(mimeMessage);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            return "Email sending failed";
        }
        return "Email sent successfully";
    }


    @Override
    public String sendMailWithAttachment(EmailDetails details) {
        return null;
    }

    @Override
    public String sendMailToCustomer(Integer orderId, String subject) {
        try {
            Order order = orderRepository.findById(orderId).orElse(null);
            if (order == null) {
                return "Order not found";
            }
//            SimpleMailMessage mailMessage = new SimpleMailMessage();
//            mailMessage.setFrom(sender);
//            mailMessage.setTo(order.getUser().getEmail());
//            mailMessage.setSubject("Thông báo từ cửa hàng sách BookShop");
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
            mimeMessageHelper.setFrom(sender, "BookShop");
            mimeMessageHelper.setTo(order.getUser().getEmail());
            mimeMessageHelper.setSubject("Thông báo từ cửa hàng sách BookShop");
            switch (subject) {
                case "CONFIRMED":
                    mimeMessage.setText("Xin chào " + order.getUser().getFullname() + ",\n\n" +
                            "Cảm ơn bạn đã đặt hàng tại BookShop. Đơn hàng của bạn đã được phê duyệt và đang chuẩn bị hàng.\n" +
                                    "Mã đơn hàng: " + orderId + "\n" +
                                    "Ngày đặt hàng: " + order.getOrderDate() + "\n" +
                                    "Tổng tiền: " + order.getTotal() + " VND\n\n" +
                                    "Chúng tôi sẽ giao hàng trong thời gian sớm nhất.\n" +
                                    "Nếu có bất kỳ thắc mắc nào, vui lòng liên hệ với chúng tôi qua email:xuanhuynh254@gmail.com " +
                                    "hoặc số điện thoại 0865070736.\n\n"+
                                    "Thân ái,\n" +
                                    "BookShop");
                    break;
                case "SHIPPING":
                    mimeMessage.setText("Xin chào " + order.getUser().getFullname() + ",\n\n" +
                            "Đơn hàng của bạn đang được vận chuyển.\n" +
                            "Mã đơn hàng: " + orderId + "\n" +
                            "Ngày đặt hàng: " + order.getOrderDate() + "\n" +
                            "Tổng tiền: " + order.getTotal() + " VND\n\n" +
                            "Chúng tôi sẽ giao hàng trong thời gian sớm nhất. Hãy mở điện thoại và kiểm tra email thường xuyên để cập nhật tình trạng đơn hàng.\n" +
                            "Nếu có bất kỳ thắc mắc nào, vui lòng liên hệ với chúng tôi qua email: xuanhuynh254@gmail.com " +
                            "hoặc số điện thoại 0865070736.\n\n"+
                            "Thân ái,\n" +
                            "BookShop");
                    break;
                case "NOTIFY-MAY-CANCEL":
                    mimeMessage.setText("Xin chào " + order.getUser().getFullname() + ",\n\n" +
                            "Chúng tôi hiện không thể liên lạc với bạn để giao hàng nhưng không thành công.\n" +
                            "Vui lòng kiểm tra lại thông tin đơn hàng và liên hệ với chúng tôi để được hỗ trợ. Nếu không, đơn hàng sẽ bị hủy sau 48h.\n" +
                            "Vui lòng liên hệ với chúng tôi qua email: xuanhuynh254@gmail.com " +
                            "hoặc số điện thoại 0865070736.\n\n"+
                            "Thân ái,\n" +
                            "BookShop");

                    break;
                case "CANCELLED":
                    mimeMessage.setText(
                            "Xin chào " + order.getUser().getFullname() + ",\n\n" +
                            "Cảm ơn bạn đã đặt hàng tại BookShop. Đơn hàng của bạn đã bị hủy.\n" +
                            "Mã đơn hàng: " + orderId + "\n" +
                            "Ngày đặt hàng: " + order.getOrderDate() + "\n" +
                            "Tổng tiền: " + order.getTotal() + " VND\n\n" +
                            "Lý do hủy: " + order.getCancelReason() + "\n\n" +
                            "Nếu có bất kỳ thắc mắc nào, vui lòng liên hệ với chúng tôi qua email: xuanhuynh254@gmail.com " +
                            "hoặc số điện thoại 0865070736.\n\n"+
                            "Thân ái,\n" +
                            "BookShop");
                    break;
                case "COMPLETED":
                    mimeMessage.setText(" Xin chào " + order.getUser().getFullname() + ",\n\n" +
                            "Cảm ơn bạn đã đặt hàng tại BookShop. Đơn hàng của bạn đã được giao thành công.\n" +
                            "Mã đơn hàng: " + orderId + "\n" +
                            "Ngày đặt hàng: " + order.getOrderDate() + "\n" +
                            "Tổng tiền: " + order.getTotal() + " VND\n\n" +
                            "Nếu có bất kỳ thắc mắc nào, vui lòng liên hệ với chúng tôi qua email:xuanhuynh254@gmail.com " +
                            "hoặc số điện thoại 0865070736.\n\n"+
                            "Thân ái,\n" +
                            "BookShop");
                    break;

                default:
                    mimeMessage.setText(" Xin chào " + order.getUser().getFullname() + ",\n\n" +
                            "Cảm ơn bạn đã đặt hàng tại BookShop. Trạng thái đơn hàng của bạn đã được câp nhật.\n" +
                            "Mã đơn hàng: " + orderId + "\n" +
                            "Ngày đặt hàng: " + order.getOrderDate() + "\n" +
                            "Tổng tiền: " + order.getTotal() + " VND\n\n" +
                            "Nếu có bất kỳ thắc mắc nào, vui lòng liên hệ với chúng tôi qua email: xuanhuynh254@gmail.com " +
                            "hoặc số điện thoại 0865070736.\n\n"+
                            "Thân ái,\n" +
                            "BookShop");
                    break;
            }

            javaMailSender.send(mimeMessage);
            return "Email sent successfully";
    } catch (Exception e) {
        e.printStackTrace();
        System.out.println(e.getMessage());
        return "Email sending failed";
    }
}

    @Override
    public String sendMailOTPTRegester(String email, String otp) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
            mimeMessageHelper.setFrom(sender, "BookShop");
            mimeMessageHelper.setTo(email);
            mimeMessageHelper.setSubject("Xác nhận OTP đăng ký tài khoản");
            mimeMessageHelper.setText("""
                    <div>
                        <h1> OTP: %s  </h1>
                        <h3>Bookshop</h3>
                        <br/>
                    </div>
                    """.formatted(otp), true);
            javaMailSender.send(mimeMessage);
            return "Email sent successfully";
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            return "Email sending failed";

        }
    }

    @Override
    public String sendMailOTPForgotPassword(String email, String otp) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
            mimeMessageHelper.setFrom(sender, "BookShop");
            mimeMessageHelper.setTo(email);
            mimeMessageHelper.setSubject("Xác nhận OTP quên mật khẩu");
            mimeMessageHelper.setText("""
                    <div>
                        <h1> OTP: %s  </h1>
                        <h3>Bookshop</h3>
                        <br/>
                    </div>
                    """.formatted(otp), true);
            javaMailSender.send(mimeMessage);
            return "Email sent successfully";
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            return "Email sending failed";
        }
    }


}
