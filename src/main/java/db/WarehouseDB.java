package db;

import entity.Product;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Getter
public class WarehouseDB {
    final List<Product> productsInWarehouse;
}
