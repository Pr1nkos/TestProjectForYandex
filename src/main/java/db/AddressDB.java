package db;

import entity.Address;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Getter
public class AddressDB {
    private final List<Address> addressList;
}
