package com.springsecuritydata1.springsecurity.service;

import com.springsecuritydata1.springsecurity.dto.AddressDTO;

import java.util.List;

public interface AddressService {
    public AddressDTO SaveAddress(AddressDTO addressDTO);

    public List<AddressDTO> getAllAddress();

    public AddressDTO getAddressById(Integer id);

    public AddressDTO updateAddress(AddressDTO addressDTO, Integer id) ;
}
