import controller.OrderController;
import db.AddressDB;
import db.WarehouseDB;
import dto.AddressDTO;
import entity.Address;
import entity.Product;
import exception.DeliveryException;
import exception.NotEnoughStockException;
import repository.AddressRepository;
import repository.WarehouseRepository;
import service.DeliveryService;
import service.OrderService;
import service.WarehouseService;

import java.util.List;

public class Main {
    public static void main(String[] args) throws DeliveryException, NotEnoughStockException {
        Product product1 = Product.builder()
                .id(1L)
                .name("Килька")
                .availableQuantuty(10)
                .build();
        Product product2 = Product.builder()
                .id(2L)
                .name("Вишня")
                .availableQuantuty(15)
                .build();
        Product product3 = Product.builder()
                .id(3L)
                .name("Свинья")
                .availableQuantuty(20)
                .build();
        Product product4 = Product.builder()
                .id(4L)
                .name("Помидор")
                .availableQuantuty(30)
                .build();
        List<Product> products = List.of(product1, product2, product3, product4);
        WarehouseDB warehouseDB = new WarehouseDB(products);

        Address address1 = Address.builder()
                .house(2)
                .town("Санкт-Петербург")
                .street("Невский проспект")
                .build();
        Address address2 = Address.builder()
                .house(7)
                .town("Москва")
                .street("Советская")
                .build();
        Address address3 = Address.builder()
                .house(123)
                .town("Новгород")
                .street("Проспект Ленина")
                .build();
        Address address4 = Address.builder()
                .house(7)
                .town("Санкт-Петербург")
                .street("Садовая улица")
                .build();
        List<Address> addressList = List.of(address4, address3, address2, address1);
        OrderController orderController = getOrderController(addressList, warehouseDB);

        AddressDTO addressDTO = AddressDTO.builder()
                .house(2)
                .town("Санкт-Петербург")
                .street("Невский проспект")
                .build();

        orderController.placeOrder(1L, 5, addressDTO);
    }

    private static OrderController getOrderController(List<Address> addressList, WarehouseDB warehouseDB) {
        AddressDB addressDB = new AddressDB(addressList);
        WarehouseRepository warehouseRepository = new WarehouseRepository(warehouseDB);
        AddressRepository addressRepository = new AddressRepository(addressDB);
        WarehouseService warehouseService = new WarehouseService(warehouseRepository);
        DeliveryService deliveryService = new DeliveryService(addressRepository);
        OrderService orderService = new OrderService(warehouseService, deliveryService);
        OrderController orderController = new OrderController(orderService);
        return orderController;
    }
}
