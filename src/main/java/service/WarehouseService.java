package service;

import entity.Product;
import lombok.RequiredArgsConstructor;
import repository.WarehouseRepository;

import java.util.Optional;

@RequiredArgsConstructor
public class WarehouseService {
    private final WarehouseRepository warehouseRepository;

    boolean isProductQuantityEnoughForOrder(Long productId, Integer quantity){
        Optional<Product> productById = warehouseRepository.getProductById(productId);
        return productById.filter(product -> product.getAvailableQuantuty() >= quantity).isPresent();
    }

    public void reserveStock(Long productId, Integer quantity) {
        Optional<Product> productById = warehouseRepository.getProductById(productId);
        productById.ifPresent(product -> product.setAvailableQuantuty(product.getAvailableQuantuty() - quantity));
    }
}
