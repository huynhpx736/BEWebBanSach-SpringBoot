package com.example.bookshop.service;

public interface AdminService {

    void cancelOrder(int id, String cancelReason);
    void updateCustomerRole(int idCustomer, String role);
    void updateProductStatus(int idProduct, String status);
    void updateUserStatus(int idUser, String status);
}
