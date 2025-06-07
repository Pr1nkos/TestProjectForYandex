package repository;

import db.WarehouseDB;
import entity.Product;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
public class WarehouseRepository {
    private final WarehouseDB warehouseDB;

    public Optional<Product> getProductById(Long productId) {
        return warehouseDB.getProductsInWarehouse().stream()
                .filter(product -> Objects.equals(product.getId(), productId))
                .findFirst();
    }

    public List<Product> getProducts() {
        return warehouseDB.getProductsInWarehouse();
    }

    public void addProduct(Product product, Integer quantity) {
        product.setAvailableQuantuty((product.getAvailableQuantuty() != null ? product.getAvailableQuantuty() : 0) + quantity);
        warehouseDB.getProductsInWarehouse().add(product);
    }
}
