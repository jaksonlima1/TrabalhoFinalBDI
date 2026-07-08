package App.DAO_DataAccessObject;

import App.Model.Atividade_complementar;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Atividade_complementarDAO {
    public void inserir(Atividade_complementar atividadeComplementar) throws SQLException {
        String sql = "INSERT INTO Atividade_complementar (idAtividade_complementar, descricao_atividade, carga_atividade, idSolicitacao_acc, idDiscente, idCategoria_acc) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConexaoBanco.obterConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, atividadeComplementar.getIdAtividade_complementar());
            stmt.setString(2, atividadeComplementar.getDescricao_Atividade());
            stmt.setInt(3, atividadeComplementar.getCarga_horaria());
            stmt.setInt(4, atividadeComplementar.getIdSolicitacao_acc());
            stmt.setInt(5, atividadeComplementar.getIdDiscente());
            stmt.setInt(6, atividadeComplementar.getIdCategoria_acc());
            stmt.executeUpdate();
        }
    }

    public List<Atividade_complementar> listar() throws SQLException {
        List<Atividade_complementar> lista = new ArrayList<>();
        String sql = "SELECT * FROM Atividade_complementar";
        try (Connection conn = ConexaoBanco.obterConexao();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                lista.add(new Atividade_complementar(
                        rs.getInt("idAtividade_complementar"),
                        rs.getString("descricao_atividade"),
                        rs.getInt("carga_horaria"),
                        rs.getInt("idSolicitacao_acc"),
                        rs.getInt("idDiscente"),
                        rs.getInt("idCategoria_acc")
                ));
            }
        }
        return lista;
    }
}
