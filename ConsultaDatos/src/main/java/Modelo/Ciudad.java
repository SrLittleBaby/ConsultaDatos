/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author Santo Tomas
 */

public class Ciudad {
    private int idCiudad;
    private int idPais;
    private String nombre;
    private String distrito;
    private int poblacion;
    private boolean esCapital;
    
    public Ciudad() {}
    
    public Ciudad(int idPais, String nombre, String distrito, int poblacion, boolean esCapital) throws Exception {
        this.idPais = idPais;
        this.setNombre(nombre);
        this.setDistrito(distrito);
        this.setPoblacion(poblacion);
        this.setEsCapital(esCapital);
    }

    public int getIdCiudad() {
        return idCiudad;
    }

    public void setIdCiudad(int idCiudad) {
        this.idCiudad = idCiudad;
    }

    public int getIdPais() {
        return idPais;
    }

    public void setIdPais(int idPais) {
        this.idPais = idPais;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) throws Exception {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new Exception("El nombre de la ciudad es obligatorio");
        }
        if (nombre.length() > 100) {
            throw new Exception("El nombre de la ciudad no puede ser mas de 100 caracteres");
        }
        this.nombre = nombre;
    }

    public String getDistrito() {
        return distrito;
    }

    public void setDistrito(String distrito) throws Exception {
        if (distrito == null || distrito.trim().isEmpty()) {
            throw new Exception("El distrito es obligatorio");
        }
        if (distrito.length() > 100) {
            throw new Exception("El distrito no puede ser mas de 100 caracteres");
        }
        this.distrito = distrito;
    }

    public int getPoblacion() {
        return poblacion;
    }

    public void setPoblacion(int poblacion) throws Exception {
        if (poblacion < 0) {
            throw new Exception("La poblacion de la ciudad no puede ser negativa");
        }
        this.poblacion = poblacion;
    }

    public boolean isEsCapital() {
        return esCapital;
    }

    public void setEsCapital(boolean esCapital) {
        this.esCapital = esCapital;
    }
}