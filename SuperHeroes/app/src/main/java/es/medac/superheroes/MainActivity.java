package es.medac.superheroes;

import static es.medac.superheroes.DetailActivity.SUPERHERO_KEY;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import es.medac.superheroes.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private static final int PHOTO_REQUEST_CODE = 1000;
    private ActivityMainBinding binding;
    private Bitmap takenPhotoBitmap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Data Binding
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.saveButton.setOnClickListener(v -> {
            // Save user input
            String superheroName = binding.heroNameEdit.getText().toString();
            String superheroEgo = binding.heroEgoEdit.getText().toString();
            String bio = binding.heroPowerEdit.getText().toString();
            float ratingFloat = binding.rating.getRating();


            // Send data to DetailActivity
            openDetailActivity(superheroName, superheroEgo, bio, ratingFloat);

        });

        //Cuando pulse el botón de Image
        binding.heroImage.setOnClickListener(v -> {
            openCamera();
        });
    }

    private void openCamera() {
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, PHOTO_REQUEST_CODE);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK && requestCode == PHOTO_REQUEST_CODE) {
            if (data != null) {
                //Extrae la imagen en miniatura devuelta por la camara
                takenPhotoBitmap = data.getExtras().getParcelable("data");
                //Muestra la foto en el imageView
                binding.heroImage.setImageBitmap(takenPhotoBitmap);
            } else {
                Toast.makeText(this, "Error tomando la foto", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void openDetailActivity(String superheroName, String superheroEgo, String bio, float rating) {
        // Mover datos entre clases
        Superhero s1 = new Superhero(superheroName, superheroEgo, bio, rating);
        Intent intent = new Intent(this, DetailActivity.class);

        intent.putExtra(SUPERHERO_KEY, s1);
        intent.putExtra(DetailActivity.BITMAP_KEY, this.takenPhotoBitmap);
        startActivity(intent);
    }
}