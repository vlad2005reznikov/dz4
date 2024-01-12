package com.example.app3;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView resultTextView;
    private Button calculateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultTextView = findViewById(R.id.resultTextView);
        calculateButton = findViewById(R.id.calculateButton);

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double income = calculateIncome();
                resultTextView.setText("Месячная выручка морского порта: " + income + " монет");
            }
        });
    }

    private double calculateIncome() {
        Income rent = new Rent(170000);
        Income service = new Service(125, 3500);
        Income[] incomes = {rent, service};
        Port port = new Port(incomes);
        return port.getTotalIncome();
    }


    abstract class Income {
        abstract double calculate();
    }

    class Rent extends Income {
        private double amount;

        public Rent(double amount) {
            this.amount = amount;
        }


        @Override
        double calculate() {

            return amount;
        }
    }

    class Service extends Income {

        private int ships;
        private double cost;


        public Service(int ships, double cost) {
            this.ships = ships;
            this.cost = cost;
        }

        @Override
        double calculate() {
            return ships * cost;
        }
    }

    class Port {

        private Income[] incomes;


        public Port(Income[] incomes) {
            this.incomes = incomes;
        }

        public double getTotalIncome() {
            double sum = 0;
            for (Income income : incomes) {

                sum += income.calculate();
            }
            return sum;
        }
    }
}
