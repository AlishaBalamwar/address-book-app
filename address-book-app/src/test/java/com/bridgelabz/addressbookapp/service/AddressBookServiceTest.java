package com.bridgelabz.addressbookapp.service;

import com.bridgelabz.addressbookapp.builder.AddressBuilder;
import com.bridgelabz.addressbookapp.dto.AddressBookDto;
import com.bridgelabz.addressbookapp.entity.AddressBook;
import com.bridgelabz.addressbookapp.exception.ResourceNotFoundException;
import com.bridgelabz.addressbookapp.repository.AddressRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AddressBookServiceTest {

    @InjectMocks
    private AddressBookService addressBookService;
    @Mock
    private AddressRepo addressRepo;
    @Mock
    private AddressBuilder addressBuilder;
    @Mock
    private ModelMapper modelMapper;

    @Test
    void givenAListOfEmployeeDetails_whenGetAListOfEmployeeCalled_shouldReturnAListOfEmployeeDTo() {
        List<AddressBook> addressBookList = new ArrayList<>();
        AddressBook addressBook1 = new AddressBook();
        addressBook1.setId(1);
        addressBook1.setName("Alisha");
        addressBook1.setAddress("Civil lines");
        addressBook1.setState("Maharashtra");
        addressBook1.setCity("Nagpur");
        addressBook1.setPhoneNumber("9876543210");
        addressBook1.setZip("123456");
        addressBook1.setCreatedOn(LocalDateTime.now());
        addressBookList.add(addressBook1);
        AddressBook addressBook2 = new AddressBook();
        addressBook2.setId(2);
        addressBook2.setName("Harry");
        addressBook2.setAddress("Civil lines");
        addressBook2.setState("Maharashtra");
        addressBook2.setCity("Nagpur");
        addressBook2.setPhoneNumber("9879543210");
        addressBook2.setZip("120056");
        addressBook2.setCreatedOn(LocalDateTime.now());
        addressBookList.add(addressBook2);


        List<AddressBookDto> addressBookDtoList = new ArrayList<>();
        AddressBookDto addressBookDto1 = new AddressBookDto();
        addressBookDto1.setName("Alisha");
        addressBookDto1.setAddress("Civil lines");
        addressBookDto1.setState("Maharashtra");
        addressBookDto1.setCity("Nagpur");
        addressBookDto1.setPhoneNumber("9876543210");
        addressBookDto1.setZip("123456");
        addressBookDtoList.add(addressBookDto1);
        AddressBookDto addressBookDto2 = new AddressBookDto();
        addressBookDto2.setName("Harry");
        addressBookDto2.setAddress("Civil lines");
        addressBookDto2.setState("Maharashtra");
        addressBookDto2.setCity("Nagpur");
        addressBookDto2.setPhoneNumber("9879543210");
        addressBookDto2.setZip("120056");
        addressBookDtoList.add(addressBookDto2);

        when(addressRepo.findAll()).thenReturn(addressBookList);
        when(modelMapper.map(addressBookList.get(0), AddressBookDto.class)).thenReturn(addressBookDto1);
        when(modelMapper.map(addressBookList.get(1), AddressBookDto.class)).thenReturn(addressBookDto2);
        List<AddressBookDto> actualListOfAddress = addressBookService.getListOfAllAddress();
        assertEquals(2, actualListOfAddress.size());
        assertEquals(addressBookDtoList, actualListOfAddress);
    }

    @Test
    void givenEmployeePayrollDto_whenCalledAddEmployee_shouldReturnSuccessMessage() {
        String successMessage = "Address added successfully";
        AddressBookDto addressBookDto1 = new AddressBookDto();
        addressBookDto1.setName("Alisha");
        addressBookDto1.setAddress("Civil lines");
        addressBookDto1.setState("Maharashtra");
        addressBookDto1.setCity("Nagpur");
        addressBookDto1.setPhoneNumber("9876543210");
        addressBookDto1.setZip("123456");

        AddressBook addressBook1 = new AddressBook();
        addressBook1.setId(1);
        addressBook1.setName("Alisha");
        addressBook1.setAddress("Civil lines");
        addressBook1.setState("Maharashtra");
        addressBook1.setCity("Nagpur");
        addressBook1.setPhoneNumber("9876543210");
        addressBook1.setZip("123456");
        addressBook1.setCreatedOn(LocalDateTime.now());

        when(modelMapper.map(addressBookDto1, AddressBook.class)).thenReturn(addressBook1);
        String actualMessage = addressBookService.addAddress(addressBookDto1);
        assertEquals(successMessage, actualMessage);
        verify(addressRepo, times(1)).save(addressBook1);
    }

    @Test
    void givenAddressIdDto_whenCalledDeleteAddress_shouldReturnSuccessMessage() {
        String successMessage = "Address deleted successfully";
        int id = 1;
        AddressBookDto addressBookDto1 = new AddressBookDto();
        addressBookDto1.setName("Alisha");
        addressBookDto1.setAddress("Civil lines");
        addressBookDto1.setState("Maharashtra");
        addressBookDto1.setCity("Nagpur");
        addressBookDto1.setPhoneNumber("9876543210");
        addressBookDto1.setZip("123456");

        AddressBook addressBook1 = new AddressBook();
        addressBook1.setId(1);
        addressBook1.setName("Alisha");
        addressBook1.setAddress("Civil lines");
        addressBook1.setState("Maharashtra");
        addressBook1.setCity("Nagpur");
        addressBook1.setPhoneNumber("9876543210");
        addressBook1.setZip("123456");
        addressBook1.setCreatedOn(LocalDateTime.now());

        when(addressRepo.findById(id)).thenReturn(Optional.of(addressBook1));
        String actualMessage = addressBookService.deleteAddress(id);
        assertEquals(successMessage, actualMessage);
        verify(addressRepo, times(1)).delete(addressBook1);
    }

    @Test
    void givenAddressIdDto_whenCalledUpdateAddress_shouldReturnSuccessMessage(){
        int id = 1;
        ArgumentCaptor<AddressBook> employeePayrollArgumentCaptor = ArgumentCaptor.forClass(AddressBook.class);
        String successMessage = "Address updated successfully";
        AddressBookDto addressBookDto1 = new AddressBookDto();
        addressBookDto1.setName("Alisha");
        addressBookDto1.setAddress("Civil lines");
        addressBookDto1.setState("Maharashtra");
        addressBookDto1.setCity("Nagpur");
        addressBookDto1.setPhoneNumber("9876543210");
        addressBookDto1.setZip("123456");

        AddressBook addressBook1 = new AddressBook();
        addressBook1.setId(1);
        addressBook1.setName("Alisha");
        addressBook1.setAddress("Civil lines");
        addressBook1.setState("Maharashtra");
        addressBook1.setCity("Nagpur");
        addressBook1.setPhoneNumber("9876543210");
        addressBook1.setZip("123456");
        addressBook1.setCreatedOn(LocalDateTime.now());

        when(addressRepo.findById(id)).thenReturn(Optional.of(addressBook1));
        when(addressBuilder.buildAddressEntity(addressBookDto1, addressBook1)).thenReturn(addressBook1);
        String actualMessage = addressBookService.updateAddress(id, addressBookDto1);
        assertEquals(successMessage, actualMessage);
        verify(addressRepo, times(1)).save(employeePayrollArgumentCaptor.capture());
        assertEquals(addressBook1.getName(), employeePayrollArgumentCaptor.getValue().getName());
        assertEquals(addressBook1.getAddress(), employeePayrollArgumentCaptor.getValue().getAddress());
        assertEquals(addressBook1.getCity(), employeePayrollArgumentCaptor.getValue().getCity());
        assertEquals(addressBook1.getState(), employeePayrollArgumentCaptor.getValue().getState());
        assertEquals(addressBook1.getPhoneNumber(), employeePayrollArgumentCaptor.getValue().getPhoneNumber());
        assertEquals(addressBook1.getZip(), employeePayrollArgumentCaptor.getValue().getZip());
    }

    @Test
    void givenAAddressDetails_whenUpdateAddressIsCalled_shouldThrowExceptionMessage() {
        int id = 1;
        AddressBookDto addressBookDto = new AddressBookDto();
        addressBookDto.setName("Alisha");
        addressBookDto.setAddress("Civil lines");
        addressBookDto.setState("Maharashtra");
        addressBookDto.setCity("Nagpur");
        addressBookDto.setPhoneNumber("9876543210");
        addressBookDto.setZip("123456");
        when(addressRepo.findById(id)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> addressBookService.updateAddress(id, addressBookDto));
    }
}
