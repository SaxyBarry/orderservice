package edu.iu.c322.orderservice.model;

import jakarta.validation.constraints.NotEmpty;

public class UpdateRequest {
    @NotEmpty(message = "Reason cannot be empty")
    private String reason;
    private int orderId;
    private int itemId;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }
}
