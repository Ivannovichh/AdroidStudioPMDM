package es.medac.superheroes;

import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import es.medac.superheroes.databinding.ActivityDetailBinding;

public class DetailActivity extends AppCompatActivity {
    public static final String SUPERHERO_KEY = "superhero";
    public static final String BITMAP_KEY = "bitmap";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Vincula esta activity con su Layout
        ActivityDetailBinding binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //Obtiene datos  enviades desde la activity anterior
        Bundle extras = getIntent().getExtras();
        Superhero superhero = extras.getParcelable(SUPERHERO_KEY);
        Bitmap bitmap = extras.getParcelable(BITMAP_KEY);

        //Si hay imagen, se muestra en el ImageView del layout
        if (bitmap != null) {
            binding.heroView.setImageBitmap(bitmap);
        }

        //Vincula el objeto superhero con el layout gracias a dataBinding
        //Esto hace que los textView del XML se rellenen ¡
        binding.setSuperhero(superhero);
    }
}