package com.bridgelabz.addressbookapp.controller;

import com.bridgelabz.addressbookapp.dto.AddressBookDto;
import com.bridgelabz.addressbookapp.service.AddressBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class AddressBookController {


    @Autowired
    private AddressBookService addressBookService;

    @GetMapping(value = "/address")
    public List<AddressBookDto> getListOfAllAddress() {
        return addressBookService.getListOfAllAddress();
    }

    @PostMapping(value = "/address")
    public String addAddress(@Valid @RequestBody AddressBookDto addressBookDto) {
        return addressBookService.addAddress(addressBookDto);
    }

    @DeleteMapping("/address/{id}")
    public String deleteAddress(@PathVariable int id) {
        return addressBookService.deleteAddress(id);
    }

    @PutMapping("/address/{id}")
    public String updateAddress(@PathVariable(value = "id") int id,
                                 @Valid @RequestBody AddressBookDto addressBookDto){
        return addressBookService.updateAddress(id, addressBookDto);
    }
}
