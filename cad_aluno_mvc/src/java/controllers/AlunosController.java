/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import beans.Aluno;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.AlunosModel;

/**
 *
 * @author souza
 */
public class AlunosController extends HttpServlet {

    // variáveis para manipulação dos métodos (listar, editar, etc.)
    Aluno aluno = new Aluno();
    List<Aluno> alunoDados; // variável para receber um Aluno
    List<Aluno> alunosDados; // variável para receber a LISTA de alunos

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // faz o tratamento das requisições feitas por URL
        // Ex.: Verificar se tem um session ativa, se tem um cookie ativo ou existente, etc...
        // configura o tipo de dados que será enviado para a View
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        // fazendo o tratamento contra erros de sistema
        try {
            // criando uma instância do objeto Model
            AlunosModel alunosModel = new AlunosModel();
            // recuperando a listagem dos alunos
            alunosDados = alunosModel.listar();
            // verificar se houve um retorno de dados
            if (alunosDados.isEmpty()) {
                request.setAttribute(
                        "mensagem",
                        "Não há registros para serem listados.");
                request.getRequestDispatcher("view_mensagem.jsp").
                        forward(request, response);
            } else {
                request.setAttribute(
                        "listaAlunos", alunosDados);
                request.getRequestDispatcher("view_listar.jsp").
                        forward(request, response);
            }
        } catch (SQLException ex) {
            // se ocorreu algum erro, envia uma mensagem
            request.setAttribute(
                    "mensagem", ex);
            request.getRequestDispatcher("view_mensagem.jsp").
                    forward(request, response);
        }

        // preparando uma variável (attribute = atributo) para enviar para a view
        // primeiro parâmetro é o nome da variável
        // segundo parâmetro é o valor a ser enviado. Pode ser um valor
        // numérico, texto ou objeto
        request.setAttribute("mensagem",
                "Este é o método doGet do AlunosController");

        // Cria o redirecionamento para a View (view_mensagem.jsp)
        request.getRequestDispatcher("view_listar.jsp").
                forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        // variável para receber qual o tipo de operação enviada
        String operacao = request.getParameter("operacao");
        // variável para pegar o "ra" enviado
        String ra = request.getParameter("ra");
        // criando um sistema de seleção (Estrutura de Seleção)
        switch (operacao) {
            case "Inserir":
                break;
            case "Editar":
                request.setAttribute(
                        "mensagem", "O RA solicitado para EDIÇÃO foi: " + ra);
                request.getRequestDispatcher("view_mensagem.jsp").
                        forward(request, response);
                break;
            case "Excluir":
                request.setAttribute(
                        "mensagem", "O RA solicitado para EXCLUSÃO foi: " + ra);
                request.getRequestDispatcher("view_mensagem.jsp").
                        forward(request, response);
                break;
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
