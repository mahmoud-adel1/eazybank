package com.eazybytes.service;

import com.eazybytes.auth.RegisterRequest;
import com.eazybytes.dto.response.CustomerResponseDto;
import com.eazybytes.enums.RoleEnum;
import com.eazybytes.exceptions.CustomerAlreadyExistsException;
import com.eazybytes.exceptions.CustomerNotFoundException;
import com.eazybytes.exceptions.CustomerRegistrationFailedException;
import com.eazybytes.mapper.CustomerMapper;
import com.eazybytes.model.Customer;
import com.eazybytes.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;

    public CustomerResponseDto getCustomerDetails(String email) {
        Customer customer = customerRepository.findByEmail(email)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found!"));
        return CustomerMapper.mapToResponseDto(customer);
    }

    public Customer registerCustomer(RegisterRequest registerRequest) {

        if (customerExistsByEmail(registerRequest.getEmail())) {
            throw new CustomerAlreadyExistsException("Customer already exists!");
        }

        try {
            Customer customer = CustomerMapper.mapToEntity(registerRequest);
            customer.setPassword(passwordEncoder.encode(customer.getPassword()));
            customer.setCreateDt(LocalDate.now());
            customer.addRole(roleService.getByRoleEnum(RoleEnum.ROLE_USER));
            Customer savedCustomer = customerRepository.save(customer);

            if (savedCustomer.getId() != null && savedCustomer.getId() > 0) {
                return savedCustomer;
            } else {
                throw new CustomerRegistrationFailedException("Customer registration failed. Please try again.");
            }
        } catch (Exception e) {
            throw new RuntimeException("An exception occurred " + e.getMessage());
        }

    }

    public Customer getCustomerByEmail(String email) {

        return customerRepository.findByEmail(email)
                .orElseThrow(()->new CustomerNotFoundException("Customer not found!"));

    }

    public boolean customerExistsByEmail(String email) {
        return customerRepository.findByEmail(email).isPresent();
    }

}
