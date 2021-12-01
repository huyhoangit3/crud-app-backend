package com.hoangit3.service.impl;

import com.hoangit3.model.Employee;
import com.hoangit3.repository.EmployeeRepository;
import com.hoangit3.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.module.ResolutionException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id).orElseThrow(
                () -> new ResolutionException("Employee with id = " + id + " not found"));
    }

    @Override
    public Employee addEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Employee updateEmployee(Long id, Employee employee) {
        Employee oldEmployee = employeeRepository.findById(id).orElseThrow(
                () -> new ResolutionException("Employee with id = " + id + " not found"));
        BeanUtils.copyProperties(employee, oldEmployee, "id");
        return employee;
    }

    @Override
    public void deleteEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(
                () -> new ResolutionException("Employee with id = " + id + " not found"));
        employeeRepository.delete(employee);
    }
}
