package com.maks.service;

public interface IPersonalCabinetServiceService {

    boolean setNewNameAndSurname(String name, Long userId);

    boolean setNewAddress(String address, Long userId);

    boolean setNewPhone(String phone, Long userId);
}
