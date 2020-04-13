package models;

import beans.Aluno;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utils.DBConnection;

/**
 *
 * @author Edson Melo de Souza
 * @date 30/03/2020
 *
 * Métodos da classe: inserir, listar, pesquisar, atualizar, excluir
 *
 */
public class AlunosModel implements Serializable {

    // Declaração das variáveis globais
    private Connection conexao = null;
    private String status;

    // Método construtor da classe
    // Método que é executado toda vez que a classe é instanciada
    public AlunosModel() throws SQLException {
        // retorna uma conexão na instância da classe
        this.conexao = DBConnection.getInstance().getConnection();
    }

    /**
     * Método listar() Retorna todos os alunos cadastrados no banco de dados
     *
     * @return List (Aluno)
     */
    public List<Aluno> listar() {
        // variável para receber a lista de objetos Aluno()
        List<Aluno> alunos = new ArrayList<>();

        // prevenindo erros (tratamento de erro)
        try {
            String sql = "SELECT * FROM alunos ORDER BY nome;";

            // tratando erros de conexão
            try (
                    // prepara a instrução SQL para er executada no banco
                    PreparedStatement ps = conexao.prepareStatement(sql);
                    // recuperando os dados enviados pelo banco
                    ResultSet rs = ps.executeQuery()) {

                // percorrendo os dados que foram retornados do banco
                while (rs.next()) {
                    // cria um objeto Aluno() a cada iteração, ou seja,
                    // a cada linha lida do conjunto de dados (rs)
                    Aluno aluno = new Aluno();

                    // atribuir os dados para o objeto Aluno()
                    aluno.setId(rs.getInt("id"));
                    aluno.setRa(rs.getInt("ra"));
                    aluno.setNome(rs.getString("nome"));
                    aluno.setCurso(rs.getString("curso"));

                    // coloca o objeto criado e populado na lista
                    // de objetos "alunos"
                    alunos.add(aluno);
                }
            }
            // o método retorna a lista de alunos (caso exista(m))
            return alunos;

            // se ocorrer algum erro, será enviada uma mensagem que deverá
            // ser tratada posteriormente no AlunosController
        } catch (SQLException ex) {
            throw new RuntimeException("Falha ao listar os alunos.", ex);
        }
    }
}
