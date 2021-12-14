package com.bridgelabz.addressbookapp.repository;

import com.bridgelabz.addressbookapp.entity.AddressBook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepo extends JpaRepository<AddressBook, Integer> {
}
