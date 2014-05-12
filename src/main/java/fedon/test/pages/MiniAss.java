package fedon.test.pages;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Zone;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fedon.test.dao.EmployeeDao;
import fedon.test.model.Employee;

/**
 * @author Dmytro Fedonin
 *
 */
public class MiniAss {
    Logger log = LoggerFactory.getLogger(MiniAss.class);

    @Inject
    EmployeeDao empDao;
    @InjectComponent
    private Zone zone;
    @Property
    private String searchName;
    @Persist
    private List<Employee> employees;

    public List<Employee> getEmployees() {
        log.info("employee size: " + (employees == null ? "null" : employees.size()));
        return employees;
    }

    public List<String> onProvideCompletions(String input) {
        searchName = input;
        List<String> result = new ArrayList<String>();
        for (Employee e : empDao.listByNameLow(input.toLowerCase())) {
            result.add(e.name);
        }
        return result;
    }

    Object onSuccess() {
        if (searchName != null && !searchName.isEmpty())
            employees = empDao.listByName(searchName);
        searchName = null;
        return zone;
    }
}
