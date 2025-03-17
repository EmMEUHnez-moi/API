package com.willimath.api.data;

import java.time.LocalDate;

import static java.util.UUID.fromString;

public class UserEntityList {

    public static UserEntity ALICE = new UserEntity(fromString("3721a1db-14ec-49f1-8f84-fc408b0f0918"),
            "Alice", "Dupont", "alice.dupont@example.com", "password123", LocalDate.of(1995,05,05), "0123456789");


    public static UserEntity BOB = new UserEntity(fromString("71937ac2-fc5f-45f5-9ce4-87191460786f"),
            "Bob", "Martin", "bob.martin@example.com", "securepass", LocalDate.of(1995, 05, 05), "0987654321");

    public  static UserEntity CHARLIE = new UserEntity(fromString("b2643f62-4b38-4ce1-8fb9-ba6f7b7e7a1e"),
            "Charlie", "Durand", "charlie.durand@example.com", "mypassword", LocalDate.of(1995, 05, 05), "0123456789");

}