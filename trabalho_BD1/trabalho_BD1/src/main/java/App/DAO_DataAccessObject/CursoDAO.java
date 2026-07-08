package App.DAO_DataAccessObject;

import App.Model.Curso;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CursoDAO {
    public void inserir(Curso curso) throws SQLException {
        String sql = "INSERT INTO Curso (idCurso, nome_curso, nivel_curso, duracao_curso, idFaculdade) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = ConexaoBanco.obterConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, curso.getIdCurso());
            stmt.setString(2, curso.getNome_curso());
            stmt.setString(3, curso.getNivel_curso());
            stmt.setString(4, curso.getDuracao_curso());
            stmt.setInt(5, curso.getIdFaculdade());
            stmt.executeUpdate();
        }
    }
    public List<Curso> listar() throws SQLException {
        List<Curso> lista = new ArrayList<>();
        String sql = "SELECT * FROM Curso";
        try (Connection conn = ConexaoBanco.obterConexao();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                lista.add(new Curso(
                        rs.getInt("idCurso"),
                        rs.getString("nome_curso"),
                        rs.getString("nivel_curso"),
                        rs.getString("ducacao_curso"),
                        rs.getInt("idFaculdade")
                ));
            }
        }
        return lista;
    }
}
