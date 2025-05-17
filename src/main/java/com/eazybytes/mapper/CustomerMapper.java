package com.eazybytes.mapper;

import com.eazybytes.auth.RegisterRequest;
import com.eazybytes.dto.response.CustomerResponseDto;
import com.eazybytes.model.Customer;

public class CustomerMapper {

    public static Customer mapToEntity(RegisterRequest registerRequest) {
        Customer customer = new Customer();
        customer.setName(registerRequest.getName());
        customer.setEmail(registerRequest.getEmail());
        return customer;
    }

    public static CustomerResponseDto mapToResponseDto(Customer customer) {
        CustomerResponseDto customerResponseDto = new CustomerResponseDto();
        customerResponseDto.setCustomerId(customer.getId());
        customerResponseDto.setName(customer.getName());
        customerResponseDto.setEmail(customer.getEmail());
        customerResponseDto.setRoles(RoleMapper.mapToRoleResponseDtoSet(customer.getRoles()));
        return customerResponseDto;
    }
}
