package com.example.practicalfive;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MUNICIPALITY = "municipality_name";

    private EditText municipalityInput;
    private TextProcessor textProcessor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        municipalityInput = findViewById(R.id.municipalityInput);
        textProcessor = new TextProcessor();

        municipalityInput.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_GO) {
                handleInput();
                return true;
            }
            return false;
        });
    }

    private void handleInput() {
        String input = municipalityInput.getText().toString().trim();

        if (input.isEmpty()) {
            Toast.makeText(this, R.string.error_empty, Toast.LENGTH_SHORT).show();
            return;
        }

        String result = textProcessor.processMunicipality(input);

        if (result == null) {
            Toast.makeText(this, R.string.error_invalid, Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(MainActivity.this, Municipality.class);
            intent.putExtra(EXTRA_MUNICIPALITY, result);
            startActivity(intent);
        }
    }
}
