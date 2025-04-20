package com.beeoverflow.apibeeoverflow.beans;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultPaymentBean {
    private String vnp_Amount;
    private String vnp_TxnRef;
    private String vnp_ResponseCode;
    private String vnp_TransactionStatus;
    private String vnp_PayDate;
    private LocalDateTime dateTime;
    private String status;
}
