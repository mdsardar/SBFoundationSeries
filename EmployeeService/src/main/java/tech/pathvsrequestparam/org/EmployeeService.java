package tech.pathvsrequestparam.org;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    EmployeeRepository employeeRepository;

    EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Optional<Employee> getEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }

    public List<Employee> getEmployees(String department) {
        if (department != null  && !department.isEmpty() ) {
            return employeeRepository.findByDepartment(department);
        }
        return employeeRepository.findAll();
    }

    public Employee getEmployeeByNameAndDepartment(String name, String department) {
        return employeeRepository.getEmployeeByNameAndDepartment(name, department);
    }

}
