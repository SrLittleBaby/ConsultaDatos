/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Modelo;

/**
 *
 * @author Santo Tomas
 */

import java.util.ArrayList;
import java.util.List;

public class Pais {
    private int idPais;
    private String nombre; 
    private String continente; 
    private String region; 
    private float superficie;
    private int anioIndependencia;
    private int poblacion; 
    private float expectLife; 
    private float pib;  
    private String gobierno; 
    private String jefeGobierno; 
    private String codPais; 
    private List<Ciudad> ciudades = new ArrayList<>(); 
    private List<IdiomaPais> idiomas = new ArrayList<>(); 
    
    private String capitalNombre;

    public Pais() {
    }

    public Pais(int idPais, String nombre, String continente, String region, float superficie, 
                int anioIndependencia, int poblacion, float expectLife, float pib, String gobierno, 
                String jefeGobierno, String codPais, 
                List<Ciudad> ciudades, List<IdiomaPais> idiomas) throws Exception {
        
        this.idPais = idPais;
        this.setNombre(nombre);
        this.setContinente(continente);
        this.setRegion(region);
        this.setSuperficie(superficie);
        this.setAnioIndependencia(anioIndependencia);
        this.setPoblacion(poblacion);
        this.setExpectLife(expectLife);
        this.setPib(pib);
        this.setGobierno(gobierno);
        this.setJefeGobierno(jefeGobierno);
        this.setCodPais(codPais);
        
        this.ciudades = ciudades != null ? ciudades : new ArrayList<>();
        this.idiomas = idiomas != null ? idiomas : new ArrayList<>();
    }

    public Pais(String nombre, String continente, String region, float superficie, 
                int anioIndependencia, int poblacion, float expectLife, float pib, 
                String gobierno, String jefeGobierno, String codPais) throws Exception {
        
        this.idPais = 0;
        this.setNombre(nombre);
        this.setContinente(continente);
        this.setRegion(region);
        this.setSuperficie(superficie);
        this.setAnioIndependencia(anioIndependencia);
        this.setPoblacion(poblacion);
        this.setExpectLife(expectLife);
        this.setPib(pib);
        this.setGobierno(gobierno);
        this.setJefeGobierno(jefeGobierno);
        this.setCodPais(codPais);
        
        this.ciudades = new ArrayList<>();
        this.idiomas = new ArrayList<>();
    }

    public Pais(int idPais, String nombre, String continente, String region, float superficie, 
                int anioIndependencia, int poblacion, float expectLife, float pib, 
                String gobierno, String jefeGobierno, String codPais) throws Exception {
        this.idPais = idPais;
        this.setNombre(nombre);
        this.setContinente(continente);
        this.setRegion(region);
        this.setSuperficie(superficie);
        this.setAnioIndependencia(anioIndependencia);
        this.setPoblacion(poblacion);
        this.setExpectLife(expectLife);
        this.setPib(pib);
        this.setGobierno(gobierno);
        this.setJefeGobierno(jefeGobierno);
        this.setCodPais(codPais);
        
        this.ciudades = new ArrayList<>();
        this.idiomas = new ArrayList<>();
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
            throw new Exception("El nombre del pais es obligatorio");
        }
        if (nombre.length() > 100) {
            throw new Exception("El nombre no puede exceder 100 caracteres");
        }
        this.nombre = nombre;
    }

    public String getContinente() {
        return continente;
    }

    public void setContinente(String continente) throws Exception {
        if (continente == null || continente.trim().isEmpty()) {
            throw new Exception("El continente es obligatorio");
        }
        if (continente.length() > 100) {
            throw new Exception("El continente no puede exceder 100 caracteres");
        }
        this.continente = continente;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) throws Exception {
        if (region == null || region.trim().isEmpty()) {
            throw new Exception("La region es obligatoria");
        }
        if (region.length() > 100) {
            throw new Exception("La region no puede exceder 100 caracteres");
        }
        this.region = region;
    }

    public float getSuperficie() {
        return superficie;
    }

    public void setSuperficie(float superficie) throws Exception {
        if (superficie < 0) {
            throw new Exception("La superficie no puede ser negativa");
        }
        this.superficie = superficie;
    }

    public int getAnioIndependencia() {
        return anioIndependencia;
    }

    public void setAnioIndependencia(int anioIndependencia) {
        this.anioIndependencia = anioIndependencia;
    }

    public int getPoblacion() {
        return poblacion;
    }

    public void setPoblacion(int poblacion) throws Exception {
        if (poblacion < 0) {
            throw new Exception("La poblacion no puede ser negativa");
        }
        this.poblacion = poblacion;
    }

    public float getExpectLife() {
        return expectLife;
    }

    public void setExpectLife(float expectLife) throws Exception {
        if (expectLife < 0) {
            throw new Exception("La expectativa de vida no puede ser negativa");
        }
        this.expectLife = expectLife;
    }

    public float getPib() {
        return pib;
    }

    public void setPib(float pib) throws Exception {
        if (pib < 0) {
            throw new Exception("El PIB no puede ser negativo");
        }
        this.pib = pib;
    }

    public String getGobierno() {
        return gobierno;
    }

    public void setGobierno(String gobierno) throws Exception {
        if (gobierno == null || gobierno.trim().isEmpty()) {
            throw new Exception("La forma de gobierno es obligatoria");
        }
        if (gobierno.length() > 100) {
            throw new Exception("El gobierno no puede ser mas de 100 caracteres");
        }
        this.gobierno = gobierno;
    }

    public String getJefeGobierno() {
        return jefeGobierno;
    }

    public void setJefeGobierno(String jefeGobierno) throws Exception {
        if (jefeGobierno == null || jefeGobierno.trim().isEmpty()) {
            throw new Exception("El jefe de gobierno es obligatorio");
        }
        if (jefeGobierno.length() > 100) {
            throw new Exception("El jefe de gobierno no puede ser mas de 100 caracteres");
        }
        this.jefeGobierno = jefeGobierno;
    }

    public String getCodPais() {
        return codPais;
    }

    public void setCodPais(String codPais) throws Exception {
        if (codPais == null || codPais.trim().isEmpty()) {
            throw new Exception("El codigo del pais es obligatorio");
        }
        if (codPais.length() > 3) {
            throw new Exception("El codigo no puede tener mas de 3 caracteres");
        }
        this.codPais = codPais;
    }

    public List<Ciudad> getCiudades() {
        return ciudades;
    }

    public void setCiudades(List<Ciudad> ciudades) {
        this.ciudades = ciudades;
    }

    public List<IdiomaPais> getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(List<IdiomaPais> idiomas) {
        this.idiomas = idiomas;
    }

    public void agregarCiudad(Ciudad ciudad) {
        this.ciudades.add(ciudad);
    }

    public void agregarIdioma(IdiomaPais idioma) {
        this.idiomas.add(idioma);
    }

    public void removerCiudad(Ciudad ciudad) {
        this.ciudades.remove(ciudad);
    }

    public void removerIdioma(IdiomaPais idioma) {
        this.idiomas.remove(idioma);
    }

    @Override
    public String toString() {
        return this.nombre + " (" + this.codPais + ")";
    }

    public String getCapitalNombre() {
        return (capitalNombre != null) ? capitalNombre : "No definida";
    }

    public void setCapitalNombre(String capitalNombre) {
        this.capitalNombre = capitalNombre;
    }
}