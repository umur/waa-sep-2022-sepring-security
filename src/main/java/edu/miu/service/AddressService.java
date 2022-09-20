package edu.miu.service;


import edu.miu.dto.AddressDto;
import edu.miu.entity.Address;

import java.util.List;

public interface AddressService {
    List<AddressDto> findAll();
    AddressDto findAddressById(int id);
    void deleteAddressById(int id);
    void save(Address address);
    AddressDto updateAddress(int id, AddressDto addressDto);

}
