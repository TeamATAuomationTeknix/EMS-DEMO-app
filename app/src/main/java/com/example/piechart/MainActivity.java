package com.example.piechart;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Pie;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    AnyChartView anyChartView;
    private Button button1, button2, button3, button4;
    private TextView textView;

    //Server connection
    private static String ip = "192.168.43.5";
    private static String port = "1433";
    private static String Classes = "net.sourceforge.jtds.jdbc.Driver";
    private static String database = "testDatabase";
    private static String username = "test";
    private static String password = "test";
    private static String url = "jdbc:jtds:sqlserver://"+ip+":"+port+"/"+database;;

    private Connection connection = null;


    String [] months = {"Temp", "Flow", "Pres", "Elec"};
    int [] earning = {500, 800, 300, 900};

    String [] pres1 = {"Temp", "Flow", "Pres", "Elec"};
    int [] pres2 = {200, 100, 1000, 400};

    String [] elec1 = {"Temp", "Flow", "Pres", "Elec"};
    int [] elec2 = {600, 700, 900, 1000};

    String [] flow1 = {"Temp", "Flow", "Pres", "Elec"};
    int [] flow2 = {4000, 1000, 400, 500};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        anyChartView = findViewById(R.id.any_chart_view);

        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
        textView = findViewById(R.id.textView);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);


        try {
            Class.forName(Classes);
            connection = DriverManager.getConnection(url, username, password);
            textView.setText("Success");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            textView.setText("ERROR");
        } catch (SQLException e) {
            e.printStackTrace();
            textView.setText("Failure");
        }

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setupPieChart();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setupPieChart1();
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setupPieChart2();
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setupPieChart3();
            }
        });

    }

    private void setupPieChart3() {
        Pie pie3 = AnyChart.pie();
        List<DataEntry> dataEntries1 = new ArrayList<>();
        for (int j = 0; j < flow1.length; j++){
            dataEntries1.add(new ValueDataEntry(flow1[j], flow2[j]));
        }
        pie3.data(dataEntries1);
        pie3.title("Flow");
        anyChartView.setChart(pie3);
    }

    private void setupPieChart2() {
        Pie pie2 = AnyChart.pie();
        List<DataEntry> dataEntries2 = new ArrayList<>();
        for (int k = 0; k < elec1.length; k++){
            dataEntries2.add(new ValueDataEntry(elec1[k], elec2[k]));
        }
        pie2.data(dataEntries2);
        pie2.title("Electricity");
        anyChartView.setChart(pie2);
    }

    private void setupPieChart1() {
        Pie pie1 = AnyChart.pie();
        List<DataEntry> dataEntries3 = new ArrayList<>();
        for (int l = 0; l < pres1.length; l++){
            dataEntries3.add(new ValueDataEntry(pres1[l], pres2[l]));
        }
        pie1.data(dataEntries3);
        pie1.title("Pressure");
        anyChartView.setChart(pie1);
    }

    private void setupPieChart() {

        Pie pie = AnyChart.pie();
        List<DataEntry> dataEntries = new ArrayList<>();
        for (int i = 0; i < months.length; i++){
            dataEntries.add(new ValueDataEntry(months[i], earning[i]));
        }
        pie.data(dataEntries);
        pie.title("Temperature");
        anyChartView.setChart(pie);
    }

    // SQL Button
    /*public void sqlButton(View view){

        if (connection != null){

            Statement statement = null;
            try {
                statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("Select * from TEST_TABLE;");
                while (resultSet.next()){
                    textView.setText(resultSet.getString(1));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else {
            textView.setText("Connections is null");
        }
    }

    public void sqlButton2(View view) {
        if (connection != null){

            Statement statement = null;
            try {
                statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("Select * from TEMP_TABLE;");
                while (resultSet.next()){
                    textView.setText(resultSet.getString(1));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else {
            textView.setText("Connections is null");
        }
    }

    public void sqlButton3(View view) {
        if (connection != null){

            Statement statement = null;
            try {
                statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("Select * from FLOW_TABLE;");
                while (resultSet.next()){
                    textView.setText(resultSet.getString(1));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else {
            textView.setText("Connections is null");
        }
    }

    public void sqlButton1(View view) {
        if (connection != null){

            Statement statement = null;
            try {
                statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("Select * from PRESS_TABLE;");
                while (resultSet.next()){
                    textView.setText(resultSet.getString(1));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else {
            textView.setText("Connections is null");
        }
    }*/


}

