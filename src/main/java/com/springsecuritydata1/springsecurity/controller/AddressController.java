package com.springsecuritydata1.springsecurity.controller;

import com.springsecuritydata1.springsecurity.dto.AddressDTO;
import com.springsecuritydata1.springsecurity.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/addresses")


public class AddressController {
    private final AddressService addressService;

    @PostMapping
    public AddressDTO save(@RequestBody AddressDTO addressDTO){
        return addressService.SaveAddress(addressDTO);
    }

    @GetMapping
    public List<AddressDTO> findAll(){
        return addressService.getAllAddress();
    }

    @GetMapping("/{id}")
    public  AddressDTO find(@PathVariable Integer id){
        return addressService.getAddressById(id);
    }

    @PutMapping("/{id}")
    public AddressDTO update(@PathVariable Integer id, @RequestBody AddressDTO addressDTO){
        return addressService.updateAddress(addressDTO, id);
    }



}
