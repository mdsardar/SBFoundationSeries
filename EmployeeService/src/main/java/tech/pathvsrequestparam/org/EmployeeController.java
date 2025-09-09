package tech.pathvsrequestparam.org;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employee/{id}")
    private Employee getEmployeeById(@PathVariable Long id) {
        return employeeService.getEmployeeById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

    }

    @GetMapping("/employee")
    private List<Employee> getEmployees(@RequestParam(required = false) String department) {
            return employeeService.getEmployees(department);
    }

    @GetMapping("/employee/{name}/{dep}")
    private Employee getEmployeeByNameAndDepartment(@PathVariable String name, @PathVariable String dep) {
        return employeeService.getEmployeeByNameAndDepartment(name, dep);
    }

}
