<?php

# Esse aqui é o endPoint da nossa API
# liberar as requisições externas ao servidor

use FFI\Exception;

if (isset($_SERVER['HTTP_ORIGIN'])) {
    header("Access-Control-Allow-Origin: {$_SERVER['HTTP_ORIGIN']}");
    header('Access-Control-Allow-Credentials: true');
    header('Access-Control-Max-Age: 8640000');
}

if ($_SERVER['REQUEST_METHOD'] == 'OPTIONS') {
    if (isset($_SERVER['HTTP_ACCESS_CONTROL_REQUEST_METHOD']))
        header("Access-Control-Allow-Methods: GET, POST, PUT, DELETE, OPTIONS");
    if (isset($_SERVER['HTTP_ACCESS_CONTROL_REQUEST_HEADERS']))
        header("Access-Control-Allow-Headers: {$_SERVER['HTTP_ACCESS_CONTROL_REQUEST_HEADERS']}");
}

# preparando para receber dados enviados ao endPoint
$json = file_get_contents('php://input');

# converter os dados recebidos para o formato JSON
# é importante saber que esses dados são objetos
$obj = json_decode($json);

# verificar se o formato está correto, senão gera um erro
if ($obj === null && json_last_error() !== JSON_ERROR_NONE) {
    print(json_encode([["type" => "request_incorreto"]]));
    die(); # finaliza o código, não processa mais nada
}

# inclui a classe do model (AlunoModel.php)
include("class/AlunoModel.php");

# criar um switch() para escolher o que deve ser feito
switch ($obj->type) {
    case 'novo':
        # tratamento de erro
        try {
            # cria um objeto para o AlunoModel
            $aluno = new AlunoModel();

            # cria uma variável para executar a operação e receber o resultado dela
            $resultado = $aluno->novo($obj->ra, $obj->nome, $obj->curso);

            # verificar o que aconteceu
            if($resultado != null){
                # essa linha retorna o ID inserido
                print(json_encode([["type" => $resultado]]));
            }else{
                print(json_encode([["type" => "erro_ao_criar"]]));
            }

        } catch (Exception $ex) {
            print(json_encode([["type" => $ex->getMessage()]]));
        }
        break;

    case 'listar':
        # tratamento de erro
        try {
            // aqui vão códigos
            // caso tudo ocorra bem, executa o bloquinho
        } catch (Exception $ex) {
            print(json_encode([["type" => $ex->getMessage()]]));
        }
        break;

    case 'pesquisar':
        # tratamento de erro
        try {
            // aqui vão códigos
            // caso tudo ocorra bem, executa o bloquinho
        } catch (Exception $ex) {
            print(json_encode([["type" => $ex->getMessage()]]));
        }
        break;

    default:
        # se nenhuma das opções foram informadas, então retorna uma mensagem de aviso
        print(json_encode([["type" => "request_incorreto"]]));
        break;
}





# vamos fazer um teste simples
#print(json_encode($obj));