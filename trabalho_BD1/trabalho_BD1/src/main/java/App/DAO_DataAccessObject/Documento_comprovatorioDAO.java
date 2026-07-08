package App.DAO_DataAccessObject;

import App.Model.Documento_comprovatorio;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Documento_comprovatorioDAO {
    public void inserir(Documento_comprovatorio documento_comprovatorio) throws SQLException {
        String sql = "INSERT INTO Documento_comprovatorio (idDocumento_comprovatorio, caminho_arquivo, data_upload, idSolicitacao_acc, idDiscente) VALUES (?,?,?,?,?)";
        try (Connection conn = ConexaoBanco.obterConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, documento_comprovatorio.getIdDocumento_comprovatorio());
            stmt.setBytes(2, documento_comprovatorio.getCaminho_arquivo());
            stmt.setTime(3, documento_comprovatorio.getData_upload());
            stmt.setInt(4, documento_comprovatorio.getIdSolicitacao_acc());
            stmt.setInt(5, documento_comprovatorio.getIdDiscente());
            stmt.executeUpdate();
        }
    }

    public List<Documento_comprovatorio> listar() throws SQLException {
        List<Documento_comprovatorio> lista = new ArrayList<>();
        String sql = "SELECT * FROM Documento_comprovatorio";
        try (Connection conn = ConexaoBanco.obterConexao();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                lista.add(new Documento_comprovatorio(
                        rs.getInt("idDocumento"),
                        rs.getBytes("caminho_arquivo"),
                        rs.getTime("data_upload"),
                        rs.getInt("idSolicitacao_acc"),
                        rs.getInt("idDiscente")
                ));
            }
        }
        return lista;
    }
}
