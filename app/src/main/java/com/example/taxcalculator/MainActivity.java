package com.example.taxcalculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import android.content.res.ColorStateList;  // Added import
import android.graphics.Color;               // Added import


public class MainActivity extends AppCompatActivity {

    private EditText editTextSurface;
    private EditText editTextPieces;
    private CheckBox checkBoxPiscine;
    private Button buttonCalculer;
    private TextView textViewResultat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        editTextSurface = findViewById(R.id.edit_text_surface);
        editTextPieces = findViewById(R.id.edit_text_pieces);
        checkBoxPiscine = findViewById(R.id.check_box_piscine);
        buttonCalculer = findViewById(R.id.button_calculer);
        textViewResultat = findViewById(R.id.text_view_resultat);

        // Set the checked color of the CheckBox to green
        checkBoxPiscine.setButtonTintList(ColorStateList.valueOf(Color.GREEN)); // New line added

        // Set onClick listener for the button
        buttonCalculer.setOnClickListener(v -> calculerImpots());
    }


    private void calculerImpots() {
        // Validate and parse the surface input
        double surface;
        try {
            surface = Double.parseDouble(editTextSurface.getText().toString());
        } catch (NumberFormatException e) {
            editTextSurface.setError("Veuillez entrer une surface valide");
            return;
        }

        // Validate and parse the number of pieces input
        int nombreDePieces;
        try {
            nombreDePieces = Integer.parseInt(editTextPieces.getText().toString());
        } catch (NumberFormatException e) {
            editTextPieces.setError("Veuillez entrer un nombre de pièces valide");
            return;
        }

        // Get the state of the pool checkbox
        boolean piscine = checkBoxPiscine.isChecked();

        // Calculate taxes
        double impotDeBase = surface * 2;
        double impotSupplementaire = nombreDePieces * 50 + (piscine ? 100 : 0);
        double impotTotal = impotDeBase + impotSupplementaire;

        // Display results using string resources
        String resultat = String.format(getString(R.string.impot_base), impotDeBase) + "\n" +
                String.format(getString(R.string.impot_supplementaire), impotSupplementaire) + "\n" +
                String.format(getString(R.string.impot_total), impotTotal);

        textViewResultat.setText(resultat);
    }
}
