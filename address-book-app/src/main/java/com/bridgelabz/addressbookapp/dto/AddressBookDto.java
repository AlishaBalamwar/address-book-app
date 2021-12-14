package com.bridgelabz.addressbookapp.dto;

import lombok.Data;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class AddressBookDto {
    @Size(min = 3, message = "Name should have atleast 3 characters")
    @Pattern(regexp = "[a-zA-Z]+[\\s]?[a-zA-Z ]+$", message = "Name only contains alphabet")
    private String name;
    @Size(min = 3, message = "Name should have atleast 3 characters")
    @Pattern(regexp = "[a-zA-Z]+[\\s]?[a-zA-Z ]+$", message = "Name only contains alphabet")
    private String address;
    @Size(min = 3, message = "City should have atleast 3 characters")
    private String city;
    @Size(min = 3, message = "State should have atleast 3 characters")
    @Pattern(regexp = "[a-zA-Z]+[\\s]?[a-zA-Z ]+$", message = "S only contains alphabet")
    private String state;
    @Pattern(regexp = "^[0-9]{10}", message = "Phone Number should contain exactly 10 digits")
    private String phoneNumber;
    @Pattern(regexp = "^[0-9]{6}", message = "Zipcode should contain exactly 6 digits")
    private String zip;

}
