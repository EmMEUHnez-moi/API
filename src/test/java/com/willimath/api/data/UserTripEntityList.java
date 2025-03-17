package com.willimath.api.data;

import java.util.Arrays;
import java.util.List;

public class UserTripEntityList {

    public static UserTripEntity ALICE = new UserTripEntity(1,UserEntityList.ALICE, TripEntityList.PARIS, new RoleEntity("Driver"));

    public static UserTripEntity ALICE_BOB = new UserTripEntity(2,UserEntityList.BOB, TripEntityList.PARIS, new RoleEntity("Passenger"));

    public static UserTripEntity CHARLIE = new UserTripEntity(3,UserEntityList.CHARLIE, TripEntityList.LILLE, new RoleEntity("Driver"));

    public static List<UserTripEntity> list = Arrays.asList(ALICE, ALICE_BOB, CHARLIE);


}
