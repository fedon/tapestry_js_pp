package fedon.test.dao;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import fedon.test.model.Employee;

/**
 * @author Dmytro Fedonin
 *
 */
@ContextConfiguration(locations = { "classpath:app-context-test.xml" })
public class EmployeeDaoTest extends AbstractTestNGSpringContextTests {
    @Autowired
    EmployeeDao dao;

    @Test
    public void testReadFile() {
        List<Employee> list = dao.readFile();
        assertNotNull(list);
        assertTrue(list.size() > 0);
    }

    @Test
    public void testListByName() {
        List<Employee> list = new ArrayList<Employee>();
        Employee emp = new Employee();
        emp.name = "Test1";
        list.add(emp);
        Employee emp2 = new Employee();
        emp2.name = "oror";
        list.add(emp2);

        dao.employees = list;

        list = dao.listByName("T");

        assertNotNull(list);
        assertTrue(list.size() == 1);
    }

    @Test
    public void testListByNameLow() {
        List<Employee> list = new ArrayList<Employee>();
        Employee emp = new Employee();
        emp.name = "Test1";
        list.add(emp);
        Employee emp2 = new Employee();
        emp2.name = "ororT";
        list.add(emp2);

        dao.employees = list;

        list = dao.listByNameLow("t");

        assertNotNull(list);
        assertTrue(list.size() == 2);

        list = dao.listByNameLow("o");

        assertNotNull(list);
        assertTrue(list.size() == 1);
    }
}
