package com.willimath.api.data;

import java.time.LocalDate;

public class UserEntityList {

    public static UserEntity ALICE = new UserEntity(1,
            "Alice", "Dupont", "alice.dupont@example.com", "password123", LocalDate.of(1995,05,05), "0123456789");


    public static UserEntity BOB = new UserEntity(2,
            "Bob", "Martin", "bob.martin@example.com", "securepass", LocalDate.of(1995, 05, 05), "0987654321");

    public  static UserEntity CHARLIE = new UserEntity(3,
            "Charlie", "Durand", "charlie.durand@example.com", "mypassword", LocalDate.of(1995, 05, 05), "0123456789");

}