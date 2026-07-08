package App.DAO_DataAccessObject;

import App.Model.Faculdade;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FaculdadeDAO {
    public void inserir(Faculdade faculdade) throws SQLException{
        String sql = "INSERT INTO Faculdade(idFaculdade, nome, idCampus) VALUES(?, ?, ?)";
        try (Connection conn = ConexaoBanco.obterConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, faculdade.getIdFaculdade());
            stmt.setString(2, faculdade.getNome());
            stmt.setInt(3, faculdade.getIdCampus());
            stmt.executeUpdate();
        }
    }
    public List<Faculdade> listar() throws SQLException {
        List<Faculdade> lista = new ArrayList<>();
        String sql = "SELECT * FROM Faculdade;";
        try(Connection conn = ConexaoBanco.obterConexao();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                lista.add(new Faculdade(
                        rs.getInt("idFaculdade"),
                        rs.getString("nome"),
                        rs.getInt("idCampus")
                ));
            }
        }
        return lista;
    }
}
