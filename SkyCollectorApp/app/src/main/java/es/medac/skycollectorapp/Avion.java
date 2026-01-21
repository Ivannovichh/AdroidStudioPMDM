package es.medac.skycollectorapp;

import java.io.Serializable;

public class Avion implements Serializable {
    private String modelo;
    private String fabricante;
    private String rareza;
    private int imagenResId; // CAMBIO: Ahora es un número de recurso (R.drawable...)

    // Datos técnicos
    private String velocidadMax;
    private String capacidad;
    private String dimensiones;
    private String paisOrigen;
    private String pesoMax;

    public Avion(String modelo, String fabricante, String rareza, int imagenResId,
                 String velocidadMax, String capacidad, String dimensiones, String paisOrigen, String pesoMax) {
        this.modelo = modelo;
        this.fabricante = fabricante;
        this.rareza = rareza;
        this.imagenResId = imagenResId;
        this.velocidadMax = velocidadMax;
        this.capacidad = capacidad;
        this.dimensiones = dimensiones;
        this.paisOrigen = paisOrigen;
        this.pesoMax = pesoMax;
    }

    public String getModelo() { return modelo; }
    public String getFabricante() { return fabricante; }
    public String getRareza() { return rareza; }
    public int getImagenResId() { return imagenResId; } // Getter actualizado

    public String getVelocidadMax() { return velocidadMax; }
    public String getCapacidad() { return capacidad; }
    public String getDimensiones() { return dimensiones; }
    public String getPaisOrigen() { return paisOrigen; }
    public String getPesoMax() { return pesoMax; }

    public int getColorRareza() {
        switch (rareza) {
            case "LEGENDARY": return 0xFFFFD600;
            case "EPIC": return 0xFFD500F9;
            case "RARE": return 0xFF2979FF;
            default: return 0xFFB0BEC5;
        }
    }
}