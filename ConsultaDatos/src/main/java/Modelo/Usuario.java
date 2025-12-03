/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author aacev
 */

public class Usuario {
    private int idUsuario;
    private String nombre;
    private String contrasena;
    private String rol;
    
    public Usuario() {
    }
    
    public Usuario(int idUsuario, String nombre, String contrasena, String rol) throws Exception {
        this.idUsuario = idUsuario;
        this.setNombre(nombre);
        this.setContrasena(contrasena);
        this.setRol(rol);
    }
    
    public Usuario(String nombre, String contrasena, String rol) throws Exception {
        this.setNombre(nombre);
        this.setContrasena(contrasena);
        this.setRol(rol);
    }
    
    public int getIdUsuario() {
        return idUsuario;
    }
    
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) throws Exception {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new Exception("El nombre de usuario es obligatorio");
        }
        if (nombre.length() < 3) {
            throw new Exception("El nombre debe tener al menos 3 caracteres");
        }
        if (nombre.length() > 150) {
            throw new Exception("El nombre no puede tener mas de 150 caracteres");
        }
        this.nombre = nombre;
    }
    
    public String getContrasena() {
        return contrasena;
    }
    
    public void setContrasena(String contrasena) throws Exception {
        if (contrasena == null || contrasena.trim().isEmpty()) {
            throw new Exception("La contraseña es obligatoria");
        }
        if (contrasena.length() < 3) {
            throw new Exception("La contraseña debe tener al menos 3 caracteres");
        }
        if (contrasena.length() > 20) {
            throw new Exception("La contraseña no puede tener mas de 20 caracteres");
        }
        this.contrasena = contrasena;
    }
    
    public String getRol() {
        return rol;
    }
    
    public void setRol(String rol) throws Exception {
        if (rol == null || rol.trim().isEmpty()) {
            throw new Exception("El rol es obligatorio");
        }
        if (rol.length() > 20) {
            throw new Exception("El rol no puede tener mas de"
                    + " 20 caracteres");
        }
        this.rol = rol;
    }
}