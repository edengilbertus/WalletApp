package com.wallet.app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class WalletHomeActivity extends AppCompatActivity {

    private ImageView iconArrowUp;
    private ImageView iconArrowDown;
    private ImageView iconEye;
    private FrameLayout cardContainer;
    private FrameLayout fabAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet_home);

        initViews();
        setupClickListeners();
    }

    private void initViews() {
        iconArrowUp = findViewById(R.id.iconArrowUp);
        iconArrowDown = findViewById(R.id.iconArrowDown);
        iconEye = findViewById(R.id.iconEye);
        cardContainer = findViewById(R.id.cardContainer);
        fabAdd = findViewById(R.id.fabAdd);
    }

    private void setupClickListeners() {
        // Navigate to Card Details when card is clicked
        cardContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WalletHomeActivity.this, CardDetailsActivity.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });

        // FAB click listener
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Add new card functionality
            }
        });

        // Arrow up click
        iconArrowUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Previous card
            }
        });

        // Arrow down click
        iconArrowDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Next card
            }
        });

        // Eye toggle
        iconEye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toggle card number visibility
            }
        });
    }
}
