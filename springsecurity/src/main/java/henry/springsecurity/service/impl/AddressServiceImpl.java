package henry.springsecurity.service.impl;

import henry.springsecurity.dto.AddressDto;
import henry.springsecurity.entity.Address;
import henry.springsecurity.mapper.ListMapper;
import henry.springsecurity.repo.AddressRepo;
import henry.springsecurity.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AddressServiceImpl implements AddressService {
    private final AddressRepo addressRepo;
    private final ModelMapper modelMapper;
    private final ListMapper listMapper;

    @Override
    public void save(AddressDto addressDto) {
        System.out.println(addressDto);
        System.out.println(modelMapper.map(addressDto, Address.class));
        addressRepo.save(modelMapper.map(addressDto, Address.class));
    }

    @Override
    public List<AddressDto> findAll() {
        List<Address> addresses = (List<Address>) addressRepo.findAll();
        return listMapper.map(addresses, AddressDto.class);
    }

    @Override
    public void update(int addressId, AddressDto addressDto) {
        Address address = addressRepo.findById(addressId).orElse(null);
        if (address != null) {
            Address newAddress = modelMapper.map(addressDto, Address.class);
            newAddress.setId(addressId);
            addressRepo.save(newAddress);
        }
    }

    @Override
    public AddressDto delete(int addressId) {
        Address address = addressRepo.findById(addressId).orElse(null);
        addressRepo.deleteById(addressId);
        return address != null ? modelMapper.map(address, AddressDto.class) : null;
    }
}
