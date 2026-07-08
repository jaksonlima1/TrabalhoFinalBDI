package App.DAO_DataAccessObject;

import App.Model.Discente;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DiscenteDAO {
    public void inserir(Discente discente) throws SQLException {
        String sql = "INSERT INTO Discente(idDiscente, matricula_discente, data_Ingresso, periodo_letivo, nome_discente, data_nascimento, cpf, sexo, idCurso) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConexaoBanco.obterConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, discente.getIdDiscente());
            stmt.setString(2, discente.getMatricula_discente());
            stmt.setDate(3, discente.getData_ingresso());
            stmt.setInt(4, discente.getPeriodo_letivo());
            stmt.setString(5, discente.getNome_discente());
            stmt.setDate(6, discente.getData_nascimento());
            stmt.setString(7, discente.getCpf());
            stmt.setString(8, discente.getSexo());
            stmt.setInt(9, discente.getIdCurso());
            stmt.executeUpdate();
        }
    }
    public List<Discente> listar() throws SQLException {
        List<Discente> lista = new ArrayList<>();
        String sql = "SELECT * FROM Discente";
        try (Connection conn = ConexaoBanco.obterConexao();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                lista.add(new Discente(
                   rs.getInt("idDiscente"),
                   rs.getString("matricula_discente"),
                   rs.getDate("data_ingresso"),
                   rs.getInt("periodo_letivo"),
                   rs.getString("nome_discente"),
                   rs.getDate("data_nascimento"),
                   rs.getString("cpf"),
                   rs.getString("sexo"),
                   rs.getInt("idCurso")
                ));
            }
        }
        return lista;
    }
}
