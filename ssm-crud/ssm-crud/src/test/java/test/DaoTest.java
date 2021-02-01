package test;

import com.study.dao.DepartmentMapper;
import com.study.dao.EmployeeMapper;
import com.study.domain.Department;
import com.study.domain.Employee;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DaoTest {
    @Test
    public void testCRUD(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        DepartmentMapper departmentMapper = ac.getBean(DepartmentMapper.class);
        Department department = new Department();
        department.setDeptName("研发部");
        department.setDeptId(null);

        departmentMapper.insertSelective(department);

    }
    @Test
    public void testEp(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        EmployeeMapper employeeMapper = ac.getBean(EmployeeMapper.class);
        employeeMapper.insertSelective(new Employee(1,"jerry","m","jerry123",1));


    }
}
