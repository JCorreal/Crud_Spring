package com.BLL;

import com.BO.Employee;
import com.DAL.EmployeeDAO;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EmployeeController
{
    @Autowired
    private EmployeeDAO employeeDAO;

    @RequestMapping(value = "/employee",method=RequestMethod.POST)
    public ModelAndView saveEmployee(@ModelAttribute("employee") Employee employee)
    {
        try
        {
            if(employeeDAO.getEmployeeById(employee.getEmpleado_id()) != null);
            employeeDAO.updateEmployee(employee);
        }
        catch(EmptyResultDataAccessException e)
        {
            employeeDAO.saveEmployee(employee);
        }
        return new ModelAndView("redirect:/employees");
    }
    
    @RequestMapping(value = "/edit/{empleado_id}")
    public ModelAndView editEmployee(@ModelAttribute("employee") Employee employee,@PathVariable("empleado_id") int empleado_id)
    {
        ModelAndView model = new ModelAndView("employees");        
        employee = employeeDAO.getEmployeeById(empleado_id);
        List<Employee> employeeList = employeeDAO.getAllEmployees();       
        model.addObject("employee",employee);        
        model.addObject("employeeList",employeeList);        
        return model;        
    }
    
    @RequestMapping(value = "/delete/{empleado_id}")
    public ModelAndView deleteEmployee(@ModelAttribute("employee") Employee employee,@PathVariable("empleado_id") int empleado_id)
    {
        employeeDAO.deleteEmployee(empleado_id);        
        return new ModelAndView("redirect:/employees");
    }

    @RequestMapping(value = "/employees")
    public ModelAndView listEmployees(@ModelAttribute("employee") Employee employee)
    {
        ModelAndView model = new ModelAndView("employees");
        List<Employee> employeeList = employeeDAO.getAllEmployees();      
        model.addObject("employeeList", employeeList);        
        return model;
    }
}
