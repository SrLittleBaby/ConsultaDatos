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
import Modelo.IdiomaPais;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class IdiomaPaisDAO implements IdiomaPaisCRUD{
    
    @Override
    public List<IdiomaPais> obtenerTodosIdiomas() throws SQLException {
        List<IdiomaPais> idiomas = new ArrayList<>();
        String sql = "SELECT * FROM idiomas";
        
        try (Connection conn = Conn.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                IdiomaPais idioma = mapearIdiomaPais(rs);
                idiomas.add(idioma);
            }
        }
        return idiomas;
    }
    
    @Override
    public List<IdiomaPais> obtenerIdiomasPorPais(int idPais) throws SQLException {
        List<IdiomaPais> idiomas = new ArrayList<>();
        String sql = "SELECT * FROM idiomas WHERE id_pais = ?";
        
        try (Connection conn = Conn.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, idPais);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    idiomas.add(mapearIdiomaPais(rs));
                }
            }
        }
        return idiomas;
    }
    
    @Override
    public List<IdiomaPais> obtenerIdiomasOficiales() throws SQLException {
        List<IdiomaPais> idiomas = new ArrayList<>();
        String sql = "SELECT * FROM idiomas WHERE esOficial = true";
        
        try (Connection conn = Conn.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                idiomas.add(mapearIdiomaPais(rs));
            }
        }
        return idiomas;
    }
    
    @Override
    public List<IdiomaPais> obtenerIdiomasOficialesPorPais(int idPais) throws SQLException {
        List<IdiomaPais> idiomas = new ArrayList<>();
        String sql = "SELECT * FROM idiomas WHERE id_pais = ? AND esOficial = true";
        
        try (Connection conn = Conn.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, idPais);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    idiomas.add(mapearIdiomaPais(rs));
                }
            }
        }
        return idiomas;
    }
    
    @Override
    public IdiomaPais obtenerIdiomaPorId(int idIdioma) throws SQLException {
        String sql = "SELECT * FROM idiomas WHERE id_idioma = ?";
        
        try (Connection conn = Conn.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, idIdioma);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return mapearIdiomaPais(rs);
                }
            }
        }
        return null;
    }
    
    @Override
    public int insertarIdioma(IdiomaPais idiomaPais) throws SQLException {
        String sql = "INSERT INTO idiomas (id_pais, idioma, esOficial, porcentajeHablante) VALUES (?, ?, ?, ?)";
        
        try (Connection conn = Conn.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            pstmt.setInt(1, idiomaPais.getIdPais());
            pstmt.setString(2, idiomaPais.getIdioma());
            pstmt.setBoolean(3, idiomaPais.isEsOficial());
            pstmt.setFloat(4, idiomaPais.getPorcentajeHablante());
            
            pstmt.executeUpdate();
            
            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                }
            }
        }
        return -1;
    }
    
    @Override
    public boolean actualizarIdioma(IdiomaPais idiomaPais) throws SQLException {
        String sql = "UPDATE idiomas SET id_pais=?, idioma=?, esOficial=?, porcentajeHablante=? WHERE id_idioma=?";
        
        try (Connection conn = Conn.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, idiomaPais.getIdPais());
            pstmt.setString(2, idiomaPais.getIdioma());
            pstmt.setBoolean(3, idiomaPais.isEsOficial());
            pstmt.setFloat(4, idiomaPais.getPorcentajeHablante());
            pstmt.setInt(5, idiomaPais.getIdIdioma());
            
            return pstmt.executeUpdate() > 0;
        }
    }
    
    @Override
    public boolean eliminarIdioma(int idIdioma) throws SQLException {
        String sql = "DELETE FROM idiomas WHERE id_idioma = ?";
        
        try (Connection conn = Conn.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, idIdioma);
            return pstmt.executeUpdate() > 0;
        }
    }
    
    @Override
    public boolean eliminarIdiomasPorPais(int idPais) throws SQLException {
        String sql = "DELETE FROM idiomas WHERE id_pais = ?";
        
        try (Connection conn = Conn.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, idPais);
            return pstmt.executeUpdate() > 0;
        }
    }
    
    @Override
    public int contarIdiomasPorPais(int idPais) throws SQLException {
        String sql = "SELECT COUNT(*) as total FROM idiomas WHERE id_pais = ?";
        
        try (Connection conn = Conn.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, idPais);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("total");
                }
            }
        }
        return 0;
    }
    public List<IdiomaPais> obtenerRankingIdiomas() throws SQLException {
        List<IdiomaPais> idiomas = new ArrayList<>();
        String sql = "SELECT i.idioma, " +
                     "SUM(p.poblacion * (i.porcentajeHablante / 100)) / (SELECT SUM(poblacion) FROM paises) * 100 as porcentaje_global " +
                     "FROM idiomas i " +
                     "JOIN paises p ON i.id_pais = p.id_pais " +
                     "GROUP BY i.idioma " +
                     "ORDER BY porcentaje_global DESC";
        
        try (Connection conn = Conn.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                try {
                    IdiomaPais idioma = new IdiomaPais();
                    idioma.setIdioma(rs.getString("idioma"));
                    idioma.setPorcentajeHablante(rs.getFloat("porcentaje_global"));
                    idioma.setEsOficial(false);
                    idioma.setIdPais(-1);
                    idiomas.add(idioma);
                } catch (Exception e) {
                    throw new SQLException("Error al procesar datos del ranking: " + e.getMessage());
                }
            }
        }
        return idiomas;
    }
    
    private IdiomaPais mapearIdiomaPais(ResultSet rs) throws SQLException {
        try {
            IdiomaPais idioma = new IdiomaPais();
            idioma.setIdIdioma(rs.getInt("id_idioma"));
            idioma.setIdPais(rs.getInt("id_pais"));
            idioma.setIdioma(rs.getString("idioma"));
            idioma.setEsOficial(rs.getBoolean("esOficial"));
            idioma.setPorcentajeHablante(rs.getFloat("porcentajeHablante"));
            return idioma;
        } catch (Exception e) {
            throw new SQLException("Error al leer idioma desde la BD: " + e.getMessage());
        }
    }
}
