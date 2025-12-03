/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

/**
 *
 * @author aacev
 */
import Controlador.Conn;
import Modelo.Usuario;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO implements UsuarioCRUD {
    

    @Override
    public Usuario validarUsuario(String nombre, String contrasena) throws SQLException {
        String sql = "SELECT * FROM usuarios WHERE nombre = ? AND contrasena = ?";
        
        try (Connection conn = Conn.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, nombre);
            pstmt.setString(2, contrasena);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    try {
                        return new Usuario(
                            rs.getInt("id_usuario"),
                            rs.getString("nombre"),
                            rs.getString("contrasena"), 
                            rs.getString("rol")
                        );
                    } catch (Exception e) {
                        throw new SQLException("Error de validación en usuario: " + e.getMessage());
                    }
                }
            }
        }
        return null; 
    }

    @Override
    public boolean registrarUsuario(String nombre, String contrasena, String rol) throws SQLException {
        String sql = "INSERT INTO usuarios (nombre, contrasena, rol) VALUES (?, ?, ?)";
        
        try (Connection conn = Conn.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, nombre);
            pstmt.setString(2, contrasena);
            pstmt.setString(3, rol);
            
            int filasAfectadas = pstmt.executeUpdate();
            return filasAfectadas > 0;
        }
    }
    

    @Override
    public boolean usuarioExiste(String nombre) throws SQLException {
        String sql = "SELECT COUNT(*) FROM usuarios WHERE nombre = ?";
        
        try (Connection conn = Conn.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, nombre);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }
        return false;
    }
    

    @Override
    public List<Usuario> obtenerTodosUsuarios() throws SQLException {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM usuarios ORDER BY id_usuario";
        
        try (Connection conn = Conn.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                try {
                    Usuario usuario = new Usuario(
                        rs.getInt("id_usuario"),
                        rs.getString("nombre"),
                        rs.getString("contrasena"),
                        rs.getString("rol")
                    );
                    usuarios.add(usuario);
                } catch (Exception e) {
                    throw new SQLException("Error al leer usuario ID " + rs.getInt("id_usuario") + ": " + e.getMessage());
                }
            }
        }
        return usuarios;
    }
    

    @Override
    public Usuario obtenerUsuarioPorId(int idUsuario) throws SQLException {
        String sql = "SELECT * FROM usuarios WHERE id_usuario = ?";
        
        try (Connection conn = Conn.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, idUsuario);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    try {
                        return new Usuario(
                            rs.getInt("id_usuario"),
                            rs.getString("nombre"),
                            rs.getString("contrasena"),
                            rs.getString("rol")
                        );
                    } catch (Exception e) {
                        throw new SQLException("Error de validación al obtener usuario: " + e.getMessage());
                    }
                }
            }
        }
        return null;
    }
    

    @Override
    public boolean actualizarUsuario(Usuario usuario) throws SQLException {
        String sql = "UPDATE usuarios SET nombre = ?, contrasena = ?, rol = ? WHERE id_usuario = ?";
        
        try (Connection conn = Conn.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, usuario.getNombre());
            pstmt.setString(2, usuario.getContrasena());
            pstmt.setString(3, usuario.getRol());
            pstmt.setInt(4, usuario.getIdUsuario());
            
            int filasAfectadas = pstmt.executeUpdate();
            return filasAfectadas > 0;
        }
    }
    

    @Override
    public boolean eliminarUsuario(int idUsuario) throws SQLException {
        String sql = "DELETE FROM usuarios WHERE id_usuario = ?";
        
        try (Connection conn = Conn.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, idUsuario);
            
            int filasAfectadas = pstmt.executeUpdate();
            return filasAfectadas > 0;
        }
    }
    

    @Override
    public boolean cambiarRolUsuario(int idUsuario, String nuevoRol) throws SQLException {
        String sql = "UPDATE usuarios SET rol = ? WHERE id_usuario = ?";
        
        try (Connection conn = Conn.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, nuevoRol);
            pstmt.setInt(2, idUsuario);
            
            int filasAfectadas = pstmt.executeUpdate();
            return filasAfectadas > 0;
        }
    }

    @Override
    public List<Usuario> buscarUsuariosPorNombre(String nombre) throws SQLException {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM usuarios WHERE nombre LIKE ? ORDER BY nombre";
        
        try (Connection conn = Conn.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, "%" + nombre + "%");
            
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    try {
                        Usuario usuario = new Usuario(
                            rs.getInt("id_usuario"),
                            rs.getString("nombre"),
                            rs.getString("contrasena"),
                            rs.getString("rol")
                        );
                        usuarios.add(usuario);
                    } catch (Exception e) {
                        throw new SQLException("Error al leer usuario buscado: " + e.getMessage());
                    }
                }
            }
        }
        return usuarios;
    }
    

    @Override
    public int contarUsuariosPorRol(String rol) throws SQLException {
        String sql = "SELECT COUNT(*) FROM usuarios WHERE rol = ?";
        
        try (Connection conn = Conn.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, rol);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        }
        return 0;
    }
}
