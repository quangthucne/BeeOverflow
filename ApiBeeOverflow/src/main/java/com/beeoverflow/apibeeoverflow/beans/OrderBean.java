package com.beeoverflow.apibeeoverflow.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderBean {
    private int id;
    private int orderId;
    private int accountId;
    private int docId;
    
}
