package henry.springsecurity.controller;

import henry.springsecurity.dto.AddressDto;
import henry.springsecurity.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/addresses")
public class AddressController {
    private final AddressService addressService;

    @PostMapping
    public void save(@RequestBody AddressDto addressDto) {
        addressService.save(addressDto);
    }

    @GetMapping
    public List<AddressDto> getAll() {
        return addressService.findAll();
    }

    @PutMapping("/{addressId}")
    public void update(@PathVariable int addressId, @RequestBody AddressDto addressDto) {
        addressService.update(addressId, addressDto);
    }

    @DeleteMapping("/{addressId}")
    public AddressDto delete(@PathVariable int addressId) {
        return addressService.delete(addressId);
    }
}
