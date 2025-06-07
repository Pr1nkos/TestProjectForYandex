import controller.OrderController;
import dto.AddressDTO;
import exception.DeliveryException;
import exception.NotEnoughStockException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.OrderService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class MainTest {

    private OrderService orderService;
    private OrderController orderController;

    @BeforeEach
    void setUp() {

        orderService = mock(OrderService.class);
        orderController = new OrderController(orderService);
    }


    @Test
    void testPlaceOrder_Successful() throws DeliveryException, NotEnoughStockException {
        // Arrange
        Long productId = 1L;
        int quantity = 5;

        AddressDTO addressDTO = AddressDTO.builder()
                .house(2)
                .town("Санкт-Петербург")
                .street("Невский проспект")
                .build();

        when(orderService.placeOrder(productId, quantity, addressDTO))
                .thenReturn("Order sucessfully delivered");

        // Act
        String result = orderController.placeOrder(productId, quantity, addressDTO);
        assertEquals("Order sucessfully delivered", result);

        //Assert
        verify(orderService, times(1)).placeOrder(productId, quantity, addressDTO);
    }
}
