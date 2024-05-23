package com.itnation.translateai;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class TranslatedActivity extends AppCompatActivity {

    EditText resultTxt;
    TextView toolTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_translated);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        resultTxt = findViewById(R.id.resultTxt);
        toolTitle = findViewById(R.id.toolTitle);


        Intent intent= getIntent();
        String displayTxt = intent.getStringExtra("resultTxt");
        String fromLanguage = intent.getStringExtra("fromLanguage");
        String toLanguage = intent.getStringExtra("toLanguage");

        resultTxt.setText(displayTxt);
        toolTitle.setText(fromLanguage + " To " + toLanguage);







    }


    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }
}