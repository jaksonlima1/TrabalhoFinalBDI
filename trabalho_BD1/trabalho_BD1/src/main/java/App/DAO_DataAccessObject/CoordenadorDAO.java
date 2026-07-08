package App.DAO_DataAccessObject;

import App.Model.Coordenador;
import java.sql.*;
import  java.util.ArrayList;
import java.util.List;

public class CoordenadorDAO {
    public void inserir(Coordenador coordenador) throws SQLException {
       String sql = "INSERT INTO Coordenador (idCoordenador, nome_coordenador, data_nascimento, cpf, sexo, matricula) VALUES (?, ?, ?, ?, ?, ?)";
       try (Connection conn = ConexaoBanco.obterConexao();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
           stmt.setInt(1, coordenador.getIdCoordenador());
           stmt.setString(2, coordenador.getNome_coordenador());
           stmt.setDate(3, coordenador.getData_nascimento());
           stmt.setString(4, coordenador.getCpf());
           stmt.setString(5, coordenador.getSexo());
           stmt.setString(6, coordenador.getMatricula());
           stmt.executeUpdate();
       }
    }

    public List<Coordenador> listar() throws SQLException {
        List<Coordenador> lista = new ArrayList<>();
        String sql = "SELECT * FROM Coordenador";
        try (Connection conn = ConexaoBanco.obterConexao();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                lista.add(new Coordenador(
                        rs.getInt("idCoordenador"),
                        rs.getString("nome_coordenador"),
                        rs.getDate("data_nascimento"),
                        rs.getString("cpf"),
                        rs.getString("sexo"),
                        rs.getString("matricula")
                ));
            }
        }
        return lista;
    }
}
