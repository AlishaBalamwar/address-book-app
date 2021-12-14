package com.bridgelabz.addressbookapp.builder;

import com.bridgelabz.addressbookapp.dto.AddressBookDto;
import com.bridgelabz.addressbookapp.entity.AddressBook;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AddressBuilder {

    @Autowired
    private ModelMapper modelMapper;

    public AddressBook buildAddressEntity(AddressBookDto addressBookDto, AddressBook addressBook) {
        modelMapper.map(addressBookDto, addressBook);
        return addressBook;
    }
}

