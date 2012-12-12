package fedon.test.dao;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import fedon.test.model.Employee;

/**
 * @author Dmytro Fedonin
 *
 */
@Repository
public class EmployeeDao {
    Logger log = LoggerFactory.getLogger(EmployeeDao.class);

    List<Employee> employees;

    public List<Employee> listAll() {
        if (employees == null) {
            employees = readFile();
        }
        return employees;
    }

    public List<Employee> listByNameLow(String pattern) {
        List<Employee> result = new ArrayList<Employee>();
        if (employees == null) {
            employees = readFile();
        }
        for (Employee empl : employees) {
            int i = empl.name.toLowerCase().indexOf(pattern);
            if (i >= 0) {
                result.add(empl);
            }
        }
        return result;
    }

    public List<Employee> listByName(String pattern) {
        List<Employee> result = new ArrayList<Employee>();
        if (employees == null) {
            employees = readFile();
        }
        for (Employee empl : employees) {
            if (empl.name.indexOf(pattern) >= 0) {
                result.add(empl);
            }
        }
        return result;
    }

    List<Employee> readFile() {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("employees_list.txt");
        Gson gson = new Gson();
        Type listType = new TypeToken<List<Employee>>() {}.getType();
        return gson.fromJson(new BufferedReader(new InputStreamReader(in)), listType);
    }
}
