package com.DAL;

import com.BO.Employee;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO
{

    private JdbcTemplate jdbcTemplate;
 
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate)
    {
        this.jdbcTemplate = jdbcTemplate;
    }

 
    public void saveEmployee(Employee employee)
    {
        String sql = "insert into Empleados values(?,?,?,?,?)";
        System.out.println("dao called");
        jdbcTemplate.update(sql, new Object[]        
        { 0, employee.getNombres(), employee.getApellidos(), employee.getTelefono(), employee.getCorreo() });
    }

   
    public Employee getEmployeeById(int id)
    {
        String sql = "select * from Empleados where empleado_id=?";
        Employee employee = (Employee) jdbcTemplate.queryForObject(sql, new Object[]
        { id }, new RowMapper<Employee>()
        {
            @Override
            public Employee mapRow(ResultSet rs, int rowNum) throws SQLException 
            {
                Employee employee = new Employee();
                employee.setEmpleado_id(rs.getInt(1));
                employee.setNombres(rs.getString(2));
                employee.setApellidos(rs.getString(3));
                employee.setTelefono(rs.getString(4));
                employee.setCorreo(rs.getString(5));
                return employee;
            }
        });
        return employee;
    }

   
    public List<Employee> getAllEmployees()
    {
        String sql = "select * from Empleados";

        List<Employee> employeeList = jdbcTemplate.query(sql, new ResultSetExtractor<List<Employee>>()
        {
            @Override
            public List<Employee> extractData(ResultSet rs) throws SQLException, DataAccessException
            {
                List<Employee> list = new ArrayList<Employee>();
                while (rs.next())
                {
                    Employee employee = new Employee();
                    employee.setEmpleado_id(rs.getInt(1));
                    employee.setNombres(rs.getString(2));
                    employee.setApellidos(rs.getString(3));
                    employee.setTelefono(rs.getString(4));
                    employee.setCorreo(rs.getString(5));
                    list.add(employee);
                }
                return list;
            }

        });
        return employeeList;
    }

    // Updating a particular Employee
    public void updateEmployee(Employee employee)
    {
        String sql = "update Empleados set Nombres =?, Apellidos=?, Telefono=?, Correo=? where empleado_id=?";
        jdbcTemplate.update(sql, new Object[]
        { employee.getNombres(), employee.getApellidos(), employee.getTelefono(), employee.getCorreo(), employee.getEmpleado_id() });
    }

    // Deletion of a particular Employee
    public void deleteEmployee(int id)
    {
        String sql = "delete from Empleados where empleado_id=?";
        jdbcTemplate.update(sql, new Object[]
        { id });
    }
}
