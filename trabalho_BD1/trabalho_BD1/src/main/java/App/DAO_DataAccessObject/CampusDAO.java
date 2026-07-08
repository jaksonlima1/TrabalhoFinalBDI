package App.DAO_DataAccessObject;

import App.Model.Campus;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CampusDAO {
    public void inserir(Campus campus) throws SQLException {
        String sql = "INSERT INTO Campus (idCampus, nome, sigla, idEndereco) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConexaoBanco.obterConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, campus.getIdCampus());
            stmt.setString(2, campus.getNome());
            stmt.setString(3, campus.getSigla());
            stmt.setInt(4, campus.getId_endereco());
            stmt.executeUpdate();
        }
    }
    public List<Campus> listar() throws SQLException {
        List<Campus> lista = new ArrayList<>();
        String sql = "SELECT * FROM Campus";
        try (Connection conn = ConexaoBanco.obterConexao();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                lista.add(new Campus(
                        rs.getInt("idCampus"),
                        rs.getString("nome"),
                        rs.getString("sigla"),
                        rs.getInt("idEndereco")
                ));
            }
        }
        return  lista;
    }
}
