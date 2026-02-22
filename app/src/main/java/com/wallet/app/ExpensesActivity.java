package com.wallet.app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.wallet.app.views.StepChartView;

public class ExpensesActivity extends AppCompatActivity {

    private ImageView iconBack;
    private StepChartView stepChart;
    private TextView navBalance;
    private TextView navBudget;
    private TextView navExpenses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expenses);

        initViews();
        setupClickListeners();
        setupChart();
    }

    private void initViews() {
        iconBack = findViewById(R.id.iconBack);
        stepChart = findViewById(R.id.stepChart);
        navBalance = findViewById(R.id.navBalance);
        navBudget = findViewById(R.id.navBudget);
        navExpenses = findViewById(R.id.navExpenses);
    }

    private void setupClickListeners() {
        // Back button
        iconBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });

        // Navigation items
        navBalance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ExpensesActivity.this, CardDetailsActivity.class);
                startActivity(intent);
                finish();
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });

        navBudget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to budget view
            }
        });

        navExpenses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Already on expenses view
            }
        });
    }

    private void setupChart() {
        // Set sample data matching the design
        // Values represent relative heights of each step
        float[] data = {0.25f, 0.65f, 0.45f, 0.35f, 0.55f};
        stepChart.setDataPoints(data);
        stepChart.setHighlightIndex(1); // Highlight March with the dot
    }
}
