package service;

import dto.AddressDTO;
import exception.DeliveryException;
import exception.NotEnoughStockException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class OrderService {
    private final WarehouseService warehouseService;
    private final DeliveryService deliveryService;

    public String placeOrder(Long productId, int quantity, AddressDTO address) throws NotEnoughStockException, DeliveryException {
        boolean productQuantityEnoughForOrder = warehouseService.isProductQuantityEnoughForOrder(productId, quantity);
        if (productQuantityEnoughForOrder){
            warehouseService.reserveStock(productId, quantity);
        } else{
            throw new NotEnoughStockException("Недостаточно товара");
        }
        boolean orderDelivered = deliveryService.scheduleDelivery(address);
        if (orderDelivered){
            log.info("Order sucessfully delivered");
            return "Order sucessfully delivered";
        } else {
            throw new DeliveryException("Адрес не найден");
        }
    }
}
