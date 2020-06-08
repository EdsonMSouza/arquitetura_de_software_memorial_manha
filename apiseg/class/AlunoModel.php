<?php

# incluir a classe de conexão com o banco de dados
include('PDOConnection.php');

# criar a nossa classe
class AlunoModel
{
    private static $pdo; # variável para receber a conexão

    # criar o método construtor da classe
    function __construct()
    {
        self::$pdo = \PDOConnection::connection();
    }

    # Campos do banco: id (automático), ra, nome, curso
    # criar um novo aluno
    public function novo($ra, $nome, $curso)
    {
        $sql = "INSERT INTO aluno_seg (ra, nome, curso) 
                VALUES (:ra, :nome, :curso)";
        # cria um objeto de banco de dados
        # Isso "->" se chama Single Arrow
        $stmt = self::$pdo->prepare($sql);

        # vamos associar as variáveis com os parâmetros
        $stmt->bindValue(":ra", $ra);
        $stmt->bindValue(":nome", $nome);
        $stmt->bindValue(":curso", $curso);

        # executa a instrução SQL
        $stmt->execute();

        # depois que incluiu, retorna o ID novo
        return self::$pdo->lastInsertId();
    }

    # listar os alunos
    public function listar()
    {
        # lista todos os alunos em ordem crescente
        $sql = "SELECT * FROM aluno_seg ORDER BY nome ASC";
        $stmt = self::$pdo->prepare($sql);
        $stmt->execute();

        # depois que executou, retorna os registros (se achou)
        # o retorno é um array php
        return $stmt->fetchAll(\PDO::FETCH_ASSOC);
    }

    # pesquisar aluno
    public function pesquisar($ra)
    {
        $sql = "SELECT * FROM aluno_seg 
                WHERE ra = :ra";
        $stmt = self::$pdo->prepare($sql);
        $stmt->bindValue(":ra", $ra);
        $stmt->execute();

        return $stmt->fetch(\PDO::FETCH_ASSOC);
    }
}
