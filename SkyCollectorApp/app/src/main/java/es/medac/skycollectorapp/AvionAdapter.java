package es.medac.skycollectorapp;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.google.android.material.card.MaterialCardView;
import java.util.List;

public class AvionAdapter extends RecyclerView.Adapter<AvionAdapter.AvionViewHolder> {

    private List<Avion> listaAviones;

    public AvionAdapter(List<Avion> listaAviones) {
        this.listaAviones = listaAviones;
    }

    @NonNull
    @Override
    public AvionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_avion, parent, false);
        return new AvionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AvionViewHolder holder, int position) {
        // Obtenemos el avión actual
        Avion avion = listaAviones.get(position);

        // Asignamos textos
        holder.txtModelo.setText(avion.getModelo());
        holder.txtFabricante.setText(avion.getFabricante());
        holder.txtRareza.setText(avion.getRareza());

        // Asignamos colores según rareza
        holder.cardView.setStrokeColor(avion.getColorRareza());
        holder.txtRareza.setBackgroundColor(avion.getColorRareza());

        Glide.with(holder.itemView.getContext())
                .load(avion.getImagenResId())
                .into(holder.imgAvion);

        // --- EL EVENTO CLIC (BLINDADO) ---
        holder.itemView.setOnClickListener(v -> {
            try {
                // 1. Verificamos que el dato no esté vacío
                if (avion == null) {
                    throw new NullPointerException("El objeto Avión está vacío");
                }

                // 2. Preparamos el viaje a la pantalla de detalle
                Intent intent = new Intent(v.getContext(), DetalleAvionActivity.class);
                intent.putExtra("avion_extra", avion);

                // 3. Iniciamos la actividad
                v.getContext().startActivity(intent);

            } catch (Exception e) {
                // SI ALGO FALLA, LA APP NO SE CERRARÁ
                // En su lugar, mostrará este mensaje en pantalla
                e.printStackTrace();
                Toast.makeText(v.getContext(),
                        "ERROR AL ABRIR: " + e.getMessage(),
                        Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaAviones.size();
    }

    // Clase interna ViewHolder (Referencia a los elementos visuales)
    public static class AvionViewHolder extends RecyclerView.ViewHolder {
        TextView txtModelo, txtFabricante, txtRareza;
        ImageView imgAvion;
        MaterialCardView cardView;

        public AvionViewHolder(@NonNull View itemView) {
            super(itemView);
            txtModelo = itemView.findViewById(R.id.txtModelo);
            txtFabricante = itemView.findViewById(R.id.txtFabricante);
            txtRareza = itemView.findViewById(R.id.txtRareza);
            imgAvion = itemView.findViewById(R.id.imgAvion);
            cardView = (MaterialCardView) itemView;
        }
    }
}