package dto;

import lombok.Builder;

@Builder
public record AddressDTO(
        String town,
        String street,
        Integer house
        ) {
}
