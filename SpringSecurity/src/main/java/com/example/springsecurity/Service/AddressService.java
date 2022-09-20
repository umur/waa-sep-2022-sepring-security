package com.example.springsecurity.Service;

import com.example.springsecurity.DTO.AddressDto;
import com.example.springsecurity.Model.Address;

import java.util.List;

public interface AddressService {
    public List<AddressDto> findAllAddress();
    public AddressDto findAddressByID();
    public void addAddress();
    public void updateAddress();
    public void deleteAddress();

}
