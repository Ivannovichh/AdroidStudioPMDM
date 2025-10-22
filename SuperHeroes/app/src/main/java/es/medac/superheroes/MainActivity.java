package es.medac.superheroes;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import es.medac.superheroes.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Data Binding
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.button.setOnClickListener(v -> {
            // Save user input
            String superheroName = binding.superheroName.getText().toString();
            String superheroEgo = binding.superheroEgo.getText().toString();
            String bio = binding.bio.getText().toString();
            String rating = binding.rating.getText().toString();
            float ratingFloat = binding.rating.getRating();

            // Send data to DetailActivity
            openDetailActivity(superheroName, superheroEgo, bio, rating);

        });
    }

    private void openDetailActivity() {
        // Mover datos entre clases
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("superhero_name", superheroName);

        startActivity(intent);
    }
}