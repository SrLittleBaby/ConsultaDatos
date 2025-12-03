/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

/**
 *
 * @author aacev
 */

import Modelo.Ciudad;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CiudadDAO implements CiudadCRUD{
    private Connection connection;
    
    public CiudadDAO(Connection connection) {
        this.connection = connection;
    }
    
    @Override
    public void insertar(Ciudad ciudad) {
        String sql = "INSERT INTO ciudades (id_pais, nombre, distrito, poblacion, es_capital) VALUES (?, ?, ?, ?, ?)";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, ciudad.getIdPais());
            stmt.setString(2, ciudad.getNombre());
            stmt.setString(3, ciudad.getDistrito());
            stmt.setInt(4, ciudad.getPoblacion());
            stmt.setBoolean(5, ciudad.isEsCapital());
            
            stmt.executeUpdate();
            
            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                ciudad.setIdCiudad(generatedKeys.getInt(1));
            }
            
        } catch (SQLException e) {
            throw new RuntimeException("Error al insertar ciudad: " + e.getMessage(), e);
        }
    }
    
    @Override
    public Ciudad obtenerPorId(int idCiudad) {
        String sql = "SELECT * FROM ciudades WHERE id_ciudad = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idCiudad);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return mapearCiudad(rs);
            }
            
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener ciudad por ID", e);
        }
        
        return null;
    }
    
    @Override
    public List<Ciudad> obtenerTodas() {
        List<Ciudad> ciudades = new ArrayList<>();
        String sql = "SELECT * FROM ciudades ORDER BY nombre";
        
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                ciudades.add(mapearCiudad(rs));
            }
            
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener todas las ciudades", e);
        }
        
        return ciudades;
    }
    
    @Override
    public List<Ciudad> obtenerPorNombre(String nombre) {
        List<Ciudad> ciudades = new ArrayList<>();
        String sql = "SELECT * FROM ciudades WHERE nombre LIKE ? ORDER BY nombre";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, "%" + nombre + "%");
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                ciudades.add(mapearCiudad(rs));
            }
            
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener ciudades por nombre", e);
        }
        
        return ciudades;
    }
    
    @Override
    public List<Ciudad> obtenerPorPais(int idPais) {
        List<Ciudad> ciudades = new ArrayList<>();
        String sql = "SELECT * FROM ciudades WHERE id_pais = ? ORDER BY nombre";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idPais);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                ciudades.add(mapearCiudad(rs));
            }
            
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener ciudades por país", e);
        }
        
        return ciudades;
    }
    
    @Override
    public List<Ciudad> obtenerPorDistrito(String distrito) {
        List<Ciudad> ciudades = new ArrayList<>();
        String sql = "SELECT * FROM ciudades WHERE distrito = ? ORDER BY nombre";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, distrito);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                ciudades.add(mapearCiudad(rs));
            }
            
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener ciudades por distrito", e);
        }
        
        return ciudades;
    }
    
    @Override
    public void actualizar(Ciudad ciudad) {
        String sql = "UPDATE ciudades SET id_pais = ?, nombre = ?, distrito = ?, poblacion = ?, es_capital = ? WHERE id_ciudad = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, ciudad.getIdPais());
            stmt.setString(2, ciudad.getNombre());
            stmt.setString(3, ciudad.getDistrito());
            stmt.setInt(4, ciudad.getPoblacion());
            stmt.setBoolean(5, ciudad.isEsCapital());
            stmt.setInt(6, ciudad.getIdCiudad());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar ciudad", e);
        }
    }
    
    @Override
    public void eliminar(int idCiudad) {
        String sql = "DELETE FROM ciudades WHERE id_ciudad = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idCiudad);
            stmt.executeUpdate();
            
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar ciudad", e);
        }
    }
    
    @Override
    public List<Ciudad> obtenerPorPoblacionMayorA(int poblacionMinima) {
        List<Ciudad> ciudades = new ArrayList<>();
        String sql = "SELECT * FROM ciudades WHERE poblacion > ? ORDER BY poblacion DESC";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, poblacionMinima);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                ciudades.add(mapearCiudad(rs));
            }
            
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener ciudades por población mayor", e);
        }
        
        return ciudades;
    }
    
    @Override
    public List<Ciudad> obtenerPorPoblacionMenorA(int poblacionMaxima) {
        List<Ciudad> ciudades = new ArrayList<>();
        String sql = "SELECT * FROM ciudades WHERE poblacion < ? ORDER BY poblacion ASC";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, poblacionMaxima);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                ciudades.add(mapearCiudad(rs));
            }
            
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener ciudades por población menor", e);
        }
        
        return ciudades;
    }
    
    @Override
    public int contarTotalCiudades() {
        String sql = "SELECT COUNT(*) as total FROM ciudades";
        
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            if (rs.next()) {
                return rs.getInt("total");
            }
            
        } catch (SQLException e) {
            throw new RuntimeException("Error al contar ciudades", e);
        }
        
        return 0;
    }
    
    @Override
    public int contarPorPais(int idPais) {
        String sql = "SELECT COUNT(*) as total FROM ciudades WHERE id_pais = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idPais);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return rs.getInt("total");
            }
            
        } catch (SQLException e) {
            throw new RuntimeException("Error al contar ciudades por país", e);
        }
        
        return 0;
    }
    
    private Ciudad mapearCiudad(ResultSet rs) throws SQLException {
        try {
            Ciudad ciudad = new Ciudad();
            ciudad.setIdCiudad(rs.getInt("id_ciudad"));
            ciudad.setIdPais(rs.getInt("id_pais"));
            ciudad.setNombre(rs.getString("nombre"));
            ciudad.setDistrito(rs.getString("distrito"));
            ciudad.setPoblacion(rs.getInt("poblacion"));
            ciudad.setEsCapital(rs.getBoolean("es_capital"));
            return ciudad;
        } catch (Exception e) {
            throw new SQLException("Error al leer datos de la ciudad desde la BD: " + e.getMessage());
        }
    }
}