package Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    // InformaÃ§Ãµes de conexÃ£o com o banco de dados
    private static final String URL = "jdbc:mysql://localhost:3306/TrabalhoPoo_2Etapa";
    private static final String USUARIO = "root";
    private static final String SENHA = "";

    // MÃ©todo para estabelecer a conexÃ£o com o banco de dados
    public static Connection obterConexao() {
        Connection conexao = null;
        try {
            // Registrar o driver JDBC
            Class.forName("com.mysql.jdbc.Driver");
            
            // Estabelecer a conexÃ£o
            conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
            System.out.println("ConexÃ£o com o banco de dados estabelecida.");
        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco de dados: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Driver JDBC nÃ£o encontrado: " + e.getMessage());
        }
        return conexao;
    }

    // MÃ©todo para fechar a conexÃ£o com o banco de dados
    public static void fecharConexao(Connection conexao) {
        if (conexao != null) {
            try {
                conexao.close();
                System.out.println("ConexÃ£o com o banco de dados fechada.");
            } catch (SQLException e) {
                System.out.println("Erro ao fechar a conexÃ£o com o banco de dados: " + e.getMessage());
            }
        }
    }
}
