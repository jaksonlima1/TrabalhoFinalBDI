package App.DAO_DataAccessObject;

import App.Model.Categoria_acc;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Categoria_accDAO {
    public void inserir(Categoria_acc categoria_acc) throws SQLException {
        String sql = "INSERT INTO Categoria_acc (idCategoria_acc, codigo_inciso, descricao, tipo_calculo) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConexaoBanco.obterConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, categoria_acc.getIdCategoria_acc());
            stmt.setString(2, categoria_acc.getCodigo_inciso());
            stmt.setString(3, categoria_acc.getDescricao());
            stmt.setString(4, categoria_acc.getTipo_calculo());
            stmt.executeUpdate();
        }
    }

    public List<Categoria_acc> listar (int idCategoria_acc) throws SQLException {
        List<Categoria_acc> lista = new ArrayList<>();
        String sql = "SELECT * FROM Categoria_acc";
        try (Connection conn = ConexaoBanco.obterConexao();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new Categoria_acc(
                   rs.getInt("idCategoria"),
                   rs.getString("codigo_inciso"),
                   rs.getString("descricao"),
                   rs.getString("tipo_calculo")
                ));
            }
        }
        return lista;
    }
}
