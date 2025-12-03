/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author Santo Tomas
 */

public class IdiomaPais {
    private int idIdioma;
    private int idPais;
    private String idioma;
    private boolean esOficial;
    private float porcentajeHablante;

    public IdiomaPais() {
    }

    public IdiomaPais(int idPais, String idioma, boolean esOficial, float porcentajeHablante) throws Exception {
        this.idPais = idPais;
        this.setIdioma(idioma);
        this.esOficial = esOficial;
        this.setPorcentajeHablante(porcentajeHablante);
    }

    public int getIdIdioma() {
        return idIdioma;
    }

    public void setIdIdioma(int idIdioma) {
        this.idIdioma = idIdioma;
    }

    public int getIdPais() {
        return idPais;
    }

    public void setIdPais(int idPais) {
        this.idPais = idPais;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) throws Exception {
        if (idioma == null || idioma.trim().isEmpty()) {
            throw new Exception("El nombre del idioma es obligatorio");
        }
        if (idioma.length() > 100) {
            throw new Exception("El nombre del idioma no puede ser mas de 100 caracteres");
        }
        this.idioma = idioma;
    }

    public boolean isEsOficial() {
        return esOficial;
    }

    public void setEsOficial(boolean esOficial) {
        this.esOficial = esOficial;
    }

    public float getPorcentajeHablante() {
        return porcentajeHablante;
    }

    public void setPorcentajeHablante(float porcentajeHablante) throws Exception {
        if (porcentajeHablante < 0 || porcentajeHablante > 100) {
            throw new Exception("El porcentaje debe estar entre 0 y 100");
        }
        this.porcentajeHablante = porcentajeHablante;
    }
    
}
