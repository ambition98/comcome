package com.gr.comcome.pd_order.model;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PdOrderVO {
	private int pdOrderNo;
	private int accountNo;
	private int saleProductNo;
	private Timestamp orderDate;
	private int price;
	private Timestamp deliveryDate;
	private String isArrived;
}
