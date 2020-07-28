package com.demo.myapptest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.demo.myapptest.adapter.EmployeeAdapter;
import com.demo.myapptest.api.ApiFactory;
import com.demo.myapptest.api.ApiService;
import com.demo.myapptest.pojo.Employee;
import com.demo.myapptest.pojo.EmployeeResponse;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerViewEmployees;
    private EmployeeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerViewEmployees = findViewById(R.id.RecycleViewEmployee);
        adapter = new EmployeeAdapter();
        adapter.setEmployees(new ArrayList<Employee>());
        recyclerViewEmployees.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewEmployees.setAdapter(adapter);

        ApiFactory apiFactory = ApiFactory.getInstance();
        ApiService apiService = apiFactory.getApiService();

        apiService.getEmployees()
                .subscribeOn(Schedulers.io()) //завантажуй дані в другому програмному потоці
                .observeOn(AndroidSchedulers.mainThread()) //виводь дані в головному програмному потоці
                .subscribe(new Consumer<EmployeeResponse>() { //виконується якщо дані завантажилися успішно
                    @Override
                    public void accept(EmployeeResponse employeeResponse) throws Exception {
                        adapter.setEmployees(employeeResponse.getResponse());
                    }
                }, new Consumer<Throwable>() { //виконується якщо дані завантажилися неуспішно
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Toast.makeText(MainActivity.this, "Помилка завантаження даних!", Toast.LENGTH_SHORT).show();
                    }
                });
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