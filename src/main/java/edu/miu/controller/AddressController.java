package edu.miu.controller;

import edu.miu.dto.AddressDto;
import edu.miu.entity.Address;
import edu.miu.service.imp.AddressServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/address")
@RequiredArgsConstructor
public class AddressController {
    @Autowired
    private final AddressServiceImp addressService;
    @GetMapping("")
    public List<AddressDto> findAll() {
        return addressService.findAll();
    }


    @GetMapping("/{id}/")
    public AddressDto findAddressById(@PathVariable int id) {
        return addressService.findAddressById(id);
    }

    @PostMapping
    public void save(@RequestBody Address address) {
         addressService.save(address);
    }
    @DeleteMapping("/{id}")
    public void deleteAddress(@PathVariable int id){
         addressService.deleteAddressById(id);
    }
    @PutMapping("/{id}")
    public Address updateAddress(int id, Address address) {
        AddressDto addressDto1 = addressService.findAddressById(id);
        addressService.deleteAddressById(id);
        addressService.save(address);
        return address;
    }
}
