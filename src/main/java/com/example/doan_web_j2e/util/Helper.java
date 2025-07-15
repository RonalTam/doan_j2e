package com.example.doan_web_j2e.util;

import com.example.doan_web_j2e.data.model.OrderItem;

import java.util.List;

public class Helper {

    // Tính tổng tiền của giỏ hàng
    public static double total(List<OrderItem> cart) {
        double total = 0;

        if (cart != null) {
            for (OrderItem item : cart) {
                total += item.getPrice() * item.getQuantity();
            }
        }

        return total;
    }

}
