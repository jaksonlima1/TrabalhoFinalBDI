package App.DAO_DataAccessObject;

import App.Model.Certificado;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CertificadoDAO {
    public void inserir(Certificado certificado) throws SQLException {
        String sql = "INSERT INTO Certificado (idCertificado, horas_certificado, descricao_tarefa, data_certificado, emissor_certificado, idAtividade_complementar, Coodenador_idCoodenador) VALUES (?,?,?,?,?,?,?)";
        try (Connection conn = ConexaoBanco.obterConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, certificado.getIdCertificado());
            stmt.setInt(2, certificado.getHoras_certificado());
            stmt.setString(3, certificado.getDescricao_tarefa());
            stmt.setDate(4, certificado.getData());
            stmt.setString(5, certificado.getEmissor_certificado());
            stmt.setInt(6, certificado.getIdAtividade_complementar());
            stmt.setInt(7, certificado.getCoordenador_idCoordenador());
            stmt.executeUpdate();
        }
    }

    public List<Certificado> listar() throws SQLException {
        List<Certificado> lista = new ArrayList<>();
        String sql = "SELECT * FROM Certificado";
        try (Connection conn = ConexaoBanco.obterConexao();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                lista.add(new Certificado(
                        rs.getInt("idCertificado"),
                        rs.getInt("horas_certificado"),
                        rs.getString("descricao_tarefa"),
                        rs.getDate("data"),
                        rs.getString("emissor_certificado"),
                        rs.getInt("idAtividade_complementar"),
                        rs.getInt("Coordenador_idCoordenador")
                ));
            }
        }
        return lista;
    }
}
