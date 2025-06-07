package controller;

import dto.AddressDTO;
import exception.DeliveryException;
import exception.NotEnoughStockException;
import lombok.RequiredArgsConstructor;
import service.OrderService;

@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    public String placeOrder(Long productId, Integer quantity, AddressDTO address) throws DeliveryException, NotEnoughStockException {
        return orderService.placeOrder(productId, quantity, address);
    }
}
