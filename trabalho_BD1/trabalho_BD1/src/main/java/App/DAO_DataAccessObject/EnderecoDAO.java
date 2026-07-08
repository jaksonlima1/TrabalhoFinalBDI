package App.DAO_DataAccessObject;

import App.Model.Endereco;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EnderecoDAO {
    public void inserir(Endereco endereco) throws SQLException {
        String sql = "INSERT INTO Endereco (idEndereco, logadouro, numero, cidade, estado, bairro, cep) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConexaoBanco.obterConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, endereco.getIdEndereco());
            stmt.setString(2, endereco.getLogadouro());
            stmt.setString(3, endereco.getNumero());
            stmt.setString(4, endereco.getCidade());
            stmt.setString(5, endereco.getEstado());
            stmt.setString(6, endereco.getBairro());
            stmt.setString(7, endereco.getCep());
            stmt.executeUpdate();
        }
    }
    public List<Endereco> listar() throws SQLException {
        List<Endereco> lista = new ArrayList<>();
        String sql = "SELECT * FROM Endereco;";
        try(Connection conn = ConexaoBanco.obterConexao();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                lista.add(new Endereco(
                        rs.getInt("idEndereco"),
                        rs.getString("logadouro"),
                        rs.getString("numero"),
                        rs.getString("ciudade"),
                        rs.getString("estado"),
                        rs.getString("bairro"),
                        rs.getString("cep")
                ));
            }
        }
        return lista;
    }
}
