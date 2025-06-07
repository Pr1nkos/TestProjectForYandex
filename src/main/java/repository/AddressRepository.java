package repository;

import db.AddressDB;
import dto.AddressDTO;
import entity.Address;
import lombok.RequiredArgsConstructor;

import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
public class AddressRepository {
    private final AddressDB addressDB;

    public Optional<Address> findAddressByStreetAndTownAndHouse(AddressDTO addressDTO){
        return addressDB.getAddressList().stream()
                .filter(address1 -> Objects.equals(addressDTO.house(), address1.getHouse()))
                .filter(address1 -> Objects.equals(addressDTO.street(), address1.getStreet()))
                .filter(address1 -> Objects.equals(addressDTO.town(), address1.getTown()))
                .findFirst();
    }


}
