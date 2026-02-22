package com.wallet.app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.wallet.app.views.CircularProgressView;

public class CardDetailsActivity extends AppCompatActivity {

    private ImageView iconBack;
    private CircularProgressView circularProgress;
    private TextView navBalance;
    private TextView navBudget;
    private TextView navExpenses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_details);

        initViews();
        setupClickListeners();
    }

    private void initViews() {
        iconBack = findViewById(R.id.iconBack);
        circularProgress = findViewById(R.id.circularProgress);
        navBalance = findViewById(R.id.navBalance);
        navBudget = findViewById(R.id.navBudget);
        navExpenses = findViewById(R.id.navExpenses);
        
        // Set initial progress
        circularProgress.setProgress(0.75f);
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
                // Already on balance view
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
                Intent intent = new Intent(CardDetailsActivity.this, ExpensesActivity.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });
    }
}
