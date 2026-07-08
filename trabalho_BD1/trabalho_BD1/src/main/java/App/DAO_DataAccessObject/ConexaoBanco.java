package App.DAO_DataAccessObject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBanco {

    //Dados de conexão
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/BD1";
    private static final String USUARIO = "root";
    private static final String SENHA = "larisso123";

    // Metodo que os DAOs vão chamar para pegar a conexão
    public static Connection obterConexao() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, SENHA);
    }

    // Metodo main para teste de conexao
    public static void main(String[] args) {
        System.out.println("Tentando conectar ao banco de dados...");
        try (Connection conexao = obterConexao()) {
            if (conexao != null) {
                System.out.println("Conexão realizada com sucesso!");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao conectar ao MySQL: " + e.getMessage());
        }
    }
}
