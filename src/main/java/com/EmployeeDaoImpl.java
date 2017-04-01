package com;

import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.scheduling.annotation.Async;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ishwar on 31/3/17.
 */
public class EmployeeDaoImpl implements EmployeeDao{


    private DataSource dataSource;

    private SimpleJdbcInsert jdbcTemplate;

    public void setDataSource(DataSource dataSource){
        this.dataSource = dataSource;
        jdbcTemplate = new SimpleJdbcInsert(dataSource).withTableName("employee");
    }


    @Override
    public void insert(Employee employee) {

        Map map = new HashMap();
        map.put("name",employee.getName());
        map.put("mailId",employee.getMailId());
        map.put("salary",employee.getSalary());
        jdbcTemplate.execute(map);

    }

   /* @Async
    public void asyncMethodWithVoidReturnType() {
        System.out.println("Execute method asynchronously. "
                + Thread.currentThread().getName());
        try {
            System.out.println("sleeping for 3 secs");
            Thread.sleep(3000);
            System.out.println("sleep over");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }*/
}
