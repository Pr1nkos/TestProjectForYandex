package service;

import dto.AddressDTO;
import entity.Address;
import lombok.RequiredArgsConstructor;
import repository.AddressRepository;

import java.util.Optional;

@RequiredArgsConstructor
public class DeliveryService {
    private final AddressRepository addressRepository;
    public boolean scheduleDelivery(AddressDTO address){
        Optional<Address> addressByStreetAndTownAndHouse = addressRepository.findAddressByStreetAndTownAndHouse(address);
        return addressByStreetAndTownAndHouse.isPresent();
    }
}
