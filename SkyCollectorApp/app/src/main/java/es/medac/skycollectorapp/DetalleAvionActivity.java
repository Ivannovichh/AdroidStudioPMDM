package es.medac.skycollectorapp;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide; // Usamos Glide para cargar la URL
import es.medac.skycollectorapp.databinding.ActivityDetalleAvionBinding;

public class DetalleAvionActivity extends AppCompatActivity {

    private ActivityDetalleAvionBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            binding = ActivityDetalleAvionBinding.inflate(getLayoutInflater());
            setContentView(binding.getRoot());

            // Recibir el avión
            Avion avion = (Avion) getIntent().getSerializableExtra("avion_extra");

            if (avion != null) {
                cargarDatos(avion);
            } else {
                // Si el avión llega vacío, avisamos y cerramos para no crashear
                android.widget.Toast.makeText(this, "Error: Datos del avión no recibidos", Toast.LENGTH_SHORT).show();
                finish();
            }

            binding.btnVolver.setOnClickListener(v -> finish());

        } catch (Exception e) {
            // Si falla al crear la pantalla (por ejemplo, error en el XML layout)
            e.printStackTrace();
            android.widget.Toast.makeText(this, "Error visual: " + e.getMessage(), Toast.LENGTH_LONG).show();
            finish(); // Volvemos atrás suavemente
        }
    }

    private void cargarDatos(Avion avion) {
        binding.txtDetalleModelo.setText(avion.getModelo());
        binding.txtDetalleFabricante.setText("Fabricante: " + avion.getFabricante());
        binding.txtDetallePais.setText("Origen: " + avion.getPaisOrigen());
        binding.txtDetalleVelocidad.setText("Velocidad: " + avion.getVelocidadMax());
        binding.txtDetalleCapacidad.setText("Capacidad: " + avion.getCapacidad());
        binding.txtDetallePeso.setText("Peso MTOW: " + avion.getPesoMax());
        binding.txtDetalleDimensiones.setText("Dimensiones: " + avion.getDimensiones());

        // Cargar foto grande con SEGURIDAD
        Glide.with(this)
                .load(avion.getImagenResId())
                .into(binding.imgDetalleGrande);
    }
}