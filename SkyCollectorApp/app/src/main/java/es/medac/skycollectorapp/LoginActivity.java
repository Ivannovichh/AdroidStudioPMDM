package es.medac.skycollectorapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import es.medac.skycollectorapp.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Acción al pulsar el botón
        binding.btnLogin.setOnClickListener(v -> validarLogin());
    }

    private void validarLogin() {
        // Obtenemos los textos
        String email = binding.inputEmail.getEditText().getText().toString().trim();
        String password = binding.inputPass.getEditText().getText().toString().trim();
        boolean esValido = true;

        // 1. Validar Email
        if (TextUtils.isEmpty(email)) {
            binding.inputEmail.setError("Debes introducir tu ID de piloto");
            esValido = false;
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.inputEmail.setError("Formato de ID incorrecto");
            esValido = false;
        } else {
            binding.inputEmail.setError(null); // Limpiar error
        }

        // 2. Validar Contraseña (Mínimo 6 caracteres)
        if (TextUtils.isEmpty(password)) {
            binding.inputPass.setError("Introduce el código de acceso");
            esValido = false;
        } else if (password.length() < 6) {
            binding.inputPass.setError("El código debe tener al menos 6 dígitos");
            esValido = false;
        } else {
            binding.inputPass.setError(null);
        }

        // 3. Si todo está correcto, pasamos al Hangar
        if (esValido) {
            // Simulamos que el login es correcto (aquí conectarías con Firebase/Base de datos)
            Toast.makeText(this, "ACCESO AUTORIZADO. BIENVENIDO PILOTO.", Toast.LENGTH_SHORT).show();

            // Navegar a MainActivity
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish(); // Cierra el Login para que no se pueda volver atrás
        }
    }
}