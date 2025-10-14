package es.medac.miedadcanina;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import es.medac.miedadcanina.databinding.ActivityMainBinding;
import pl.droidsonroids.gif.AnimationListener;
import pl.droidsonroids.gif.GifDrawable;

public class MainActivity extends AppCompatActivity {

    // 1. Declarar el objeto Binding como variable de la clase
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 2. Inflar el layout usando View Binding y establecerlo como la vista de la actividad
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // 3. Configurar los listeners para todos los botones usando las referencias de binding
        ConfigurarBtn();
        ConfigActionBtn();

        // 4. Configurar el listener para el botón "Calcular"
        binding.boton.setOnClickListener(view -> calculateAge());

        // Opcional: Código para el bucle infinito del GIF del bosque
        configurarBucleGif();
    }

    /**
     * Configura los listeners para los botones numéricos (0-9) usando View Binding.
     */
    private void ConfigurarBtn() {
        View.OnClickListener listener = v -> {
            Button b = (Button) v;
            // Accedemos a urEdad a través del binding
            String currentText = binding.urEdad.getText().toString();
            if (currentText.length() < 3) {
                binding.urEdad.append(b.getText().toString());
            }
        };

        // Asignamos el listener usando las referencias directas del binding
        binding.btn0.setOnClickListener(listener);
        binding.btn1.setOnClickListener(listener);
        binding.btn2.setOnClickListener(listener);
        binding.btn3.setOnClickListener(listener);
        binding.btn4.setOnClickListener(listener);
        binding.btn5.setOnClickListener(listener);
        binding.btn6.setOnClickListener(listener);
        binding.btn7.setOnClickListener(listener);
        binding.btn8.setOnClickListener(listener);
        binding.btn9.setOnClickListener(listener);
    }

    /**
     * Configura los listeners para los botones de acción (Limpiar y Borrar) usando View Binding.
     */
    private void ConfigActionBtn() {
        // Botón Limpiar (C)
        binding.btnClear.setOnClickListener(v -> {
            binding.urEdad.setText("");
            binding.resultText.setText("");
        });

        // Botón Borrar (DEL)
        binding.btnDel.setOnClickListener(v -> {
            String currentText = binding.urEdad.getText().toString();
            if (!currentText.isEmpty()) {
                binding.urEdad.setText(currentText.substring(0, currentText.length() - 1));
            }
        });
    }

    /**
     * Lógica principal para calcular la edad canina.
     */
    private void calculateAge() {
        String edad = binding.urEdad.getText().toString();

        if (!edad.isEmpty()) {
            int edadHumana = Integer.parseInt(edad);
            if (edadHumana > 0 && edadHumana <= 100) {
                int edadCanina = edadHumana * 7;
                // Usamos el binding para acceder al TextView del resultado
                binding.resultText.setText(getString(R.string.EdadPerro, edadCanina));
            } else {
                Toast.makeText(this, "Por favor, introduce una edad realista (1-100)", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Por favor, introduce una edad", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Configura el GIF del fondo para que se reproduzca en un bucle parcial y evitar cortes.
     */
    private void configurarBucleGif() {
        // Este código es opcional, si quieres implementar la solución del bucle del GIF
        // Asegúrate de que tu GifImageView de fondo tiene el id 'fondoGif' en el XML
        try {
            GifDrawable gifDrawable = (GifDrawable) binding.fondoGif.getDrawable();
            gifDrawable.addAnimationListener(new AnimationListener() {
                @Override
                public void onAnimationCompleted(int loopNumber) {
                    // Cuando la animación termina, la reinicia desde un fotograma específico
                    // para crear un bucle suave. Ajusta el número '20' según tu GIF.
                    final int START_FRAME = 20;
                    gifDrawable.seekToFrame(START_FRAME);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
