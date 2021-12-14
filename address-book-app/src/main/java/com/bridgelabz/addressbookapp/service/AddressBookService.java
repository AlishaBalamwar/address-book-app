package com.bridgelabz.addressbookapp.service;

import com.bridgelabz.addressbookapp.builder.AddressBuilder;
import com.bridgelabz.addressbookapp.dto.AddressBookDto;
import com.bridgelabz.addressbookapp.entity.AddressBook;
import com.bridgelabz.addressbookapp.exception.ResourceNotFoundException;
import com.bridgelabz.addressbookapp.repository.AddressRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressBookService {

    private static final String ADDRESS_ADDED_SUCCESSFULLY = "Address added successfully";
    private static final String INVALID_ID = "Invalid id";
    private static final String ADDRESS_DELETED_SUCCESSFULLY = "Address deleted successfully";
    private static final String ADDRESS_UPDATED_SUCCESSFULLY = "Address updated successfully";
    @Autowired
    private AddressRepo addressRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private AddressBuilder addressBuilder;

    public List<AddressBookDto> getListOfAllAddress() {
        return addressRepo
                .findAll()
                .stream()
                .map(addressBook -> modelMapper.map(addressBook, AddressBookDto.class))
                .collect(Collectors.toList());
    }

    public String addAddress(AddressBookDto addressBookDto) {
        AddressBook addressBook = modelMapper.map(addressBookDto, AddressBook.class);
        addressRepo.save(addressBook);
        return ADDRESS_ADDED_SUCCESSFULLY;
    }

    private AddressBook findAtmEntityById(int id) {
        return addressRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException(INVALID_ID));
    }

    public String deleteAddress(int id) throws ResourceNotFoundException {
        AddressBook addressBook = findAtmEntityById(id);
        addressRepo.delete(addressBook);
        return ADDRESS_DELETED_SUCCESSFULLY;
    }

    public String updateAddress(int id, AddressBookDto addressBookDto) throws ResourceNotFoundException {
        AddressBook addressBook = findAtmEntityById(id);
        addressBook = addressBuilder.buildAddressEntity(addressBookDto, addressBook);
        addressRepo.save(addressBook);
        return ADDRESS_UPDATED_SUCCESSFULLY;
    }
}
