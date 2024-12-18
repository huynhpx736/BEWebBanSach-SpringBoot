package com.example.bookshop.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EmailDetails {
    private String to;
    private String subject;
    private String text;
    private String attachment;
    private Integer orderId;

//    public EmailDetails(String to, String subject, String text) {
//        this.to = to;
//        this.subject = subject;
//        this.text = text;
//    }

}
