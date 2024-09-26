package com.system.pos_system.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderReqDTO {
    private List<OrderItemDTO> orderItems;

    @Getter
    @Setter
    public static class OrderItemDTO {

        private Long itemId;
        private Integer quantity;
    }
}
