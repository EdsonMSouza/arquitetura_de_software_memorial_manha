<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:import url = "topo.jsp" />

<div class="alert-success text-center espaco">
    Cadastrar Aluno
</div>

<form name="cadastrar" method="post" action="AlunosController">
    <input type="text" name="ra" /><br>
    <input type="text" name="nome" /><br>
    <input type="text" name="curso" /><br>

    <input type="hidden" name="operacao" value="Inserir" />
    <input type="submit" name="bt_enviar" value="Cadastrar" />

</form>

<c:import url = "rodape.jsp" />