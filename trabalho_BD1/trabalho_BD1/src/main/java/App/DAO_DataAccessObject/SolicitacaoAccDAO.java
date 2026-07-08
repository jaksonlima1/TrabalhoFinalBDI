package App.DAO_DataAccessObject;

import App.Model.SolicitacaoACC;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SolicitacaoAccDAO {
    public void inserir (SolicitacaoACC solicitacaoACC) throws SQLException {
        String sql = "INSERT INTO Solicitacao_acc (idSolicitacao_acc, data_submissao, situacao_solicitacao, carga_horaria_declarada, na_area, idDiscente) VALUES (?, ? , ?, ?, ?, ?)";
        try (Connection conn = ConexaoBanco.obterConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, solicitacaoACC.getIdSolicitacao_acc());
            stmt.setDate(2, solicitacaoACC.getData_submissao());
            stmt.setString(3, solicitacaoACC.getSituacao_solitacao());
            stmt.setInt(4, solicitacaoACC.getCarga_horaria_declarada());
            stmt.setString(5, solicitacaoACC.getNa_area());
            stmt.setInt(6, solicitacaoACC.getIdDiscente());
            stmt.executeUpdate();
        }
    }
    public List<SolicitacaoACC> listar () throws SQLException {
        List<SolicitacaoACC> lista = new ArrayList<>();
        String sql = "SELECT * FROM Solicitacao_acc";
        try (Connection conn = ConexaoBanco.obterConexao();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                lista.add(new SolicitacaoACC(
                        rs.getInt("idSolicitacao"),
                        rs.getDate("data_submissao"),
                        rs.getString("situacao_solicitacao"),
                        rs.getInt("caraga_horaria_declarada"),
                        rs.getString("na_area"),
                        rs.getInt("idDiscente")
                ));
            }
        }
        return lista;
    }
}
