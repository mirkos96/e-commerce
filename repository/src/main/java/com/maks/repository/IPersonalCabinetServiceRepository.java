package com.maks.repository;

public interface IPersonalCabinetServiceRepository {

    boolean setNewNameSurname(String name, Long userId);

    boolean setNewAddress(String address, Long userId);

    boolean setNewPhone(String phone, Long userId);
}
