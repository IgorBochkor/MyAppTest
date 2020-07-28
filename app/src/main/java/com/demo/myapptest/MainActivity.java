package com.demo.myapptest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.demo.myapptest.adapter.EmployeeAdapter;
import com.demo.myapptest.pojo.Employee;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerViewEmployees;
    private EmployeeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerViewEmployees = findViewById(R.id.RecycleViewEmployee);
        adapter = new EmployeeAdapter();
        recyclerViewEmployees.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewEmployees.setAdapter(adapter);

//        перевірка роботи recycleView
//        List<Employee> employees = new ArrayList<>();
//        Employee employee1 = new Employee();
//        Employee employee2 = new Employee();
//        employee1.setFName("Ihor");
//        employee2.setFName("Max");
//        employee1.setLName("Bochkor");
//        employee2.setLName("Fray");
//        employees.add(employee1);
//        employees.add(employee2);
//        adapter.setEmployees(employees);
    }
}