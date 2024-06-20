package com.greatlearning.ems.controller;

//import org.hibernate.mapping.List;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.greatlearning.ems.entity.Employee;
import com.greatlearning.ems.service.EmployeeService;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@RequestMapping("/list")
	public String listEmployees(Model theModel) {

		List<Employee> employees = employeeService.findAll();

		theModel.addAttribute("employees", employees);
		return "employees/list-employees";
	}

	@RequestMapping("/showEmployeeForm_Save")
	public String saveEmployee_stp1(Model theModel) {

		Employee employee = new Employee();
		theModel.addAttribute("employee", employee);
		return "employees/employee-form";

	}

	@RequestMapping("/save")
	public String saveEmployee(@ModelAttribute("employee") Employee employee) {
		employeeService.save(employee);
		return "redirect:/employees/list";
	}

	@RequestMapping("/showEmployeeForm_Update")
	public String updateEmployee_stp1(@RequestParam("employeeId") Integer employeeId, Model theModel) {

		Employee employee = employeeService.findById(employeeId);
		theModel.addAttribute("employee", employee);
		return "employees/employee-form";
	}

	@RequestMapping("/delete")
	public String delete(@RequestParam("employeeId") Integer employeeId) {
		employeeService.deleteById(employeeId);
		return "redirect:/employees/list";
	}

}
