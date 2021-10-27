<%@page import="Model.Seguro"%>
<%@page import="java.util.Date"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.util.List"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="Model.Usuario"%>
<%@page import="Model.Veiculo"%>
<%@page import="Dao.UsuarioDao"%>
<%@page import="java.text.DecimalFormat"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">
<title>Atividade 2</title>
</head>
<body>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
		crossorigin="anonymous"></script>
	<nav class="navbar navbar-expand-md navbar-dark bg-dark">
		<div class="container-fluid">
			<span class="navbar-brand mb-0 h1">Atividade 2 - Funções de cálculo de seguro</span>
		</div>
	</nav>
	<div class="container">
		<div class="bg-light p-5 rounded mt-5">
			<form class="row g-3" >
				<div class="col-md-8">
					<label for="inputNome" class="form-label">Nome*</label> 
					<input type="text" name="nome" class="form-control" id="inputNome" required>
				</div>
				<div class="col-md-4">
					<label for="inputCpf" class="form-label">CPF* (Somente numeros)</label> 
					<input type="text" name="cpf" class="form-control" id="inputCpf" maxlength="11" required>
				</div>
				<div class="col-md-2">
					<label for="inputSexo" class="form-label">Sexo*</label> 
					<select id="inputSexo" name="sexo" class="form-select" required>
						<option selected disabled value="">Selecione...</option>
						<option value="F">F</option>
					    <option value="M">M</option>
					</select>
				</div>
				<div class="col-md-2">
					<label for="inputNascimento" class="form-label">Data de Nascimento*</label> 
					<input type="date" name="nascimento" min="1921-01-01" class="form-control" id="inputNascimento" required>
				</div>
				<div class="col-md-4">
					<label for="inputVeiculo" class="form-label">Nome do Veículo*</label> 
					<input type="text" name="nomeVeiculo" class="form-control" id="inputVeiculo" required>
				</div>
				<div class="col-md-4">
				<label for="inputValor" class="form-label">Valor do Veículo*</label> 
				<div class="input-group mb-1">	
					<span class="input-group-text">R$</span> 
						<input type="number" name="valorVeiculo" min="1" class="form-control" required> 
					<span class="input-group-text">.00</span>
					</div>
				</div>
				<div class="col-12">
					<button type="submit" name="enviar" class="btn btn-primary">Enviar</button>
				</div>
			</form>						
		</div>
		<%
			try{
				UsuarioDao ud = new UsuarioDao();
				Usuario u = new Usuario();
				Veiculo v = new Veiculo();
				
				if(request.getParameter("enviar") == ""){
					if(!u.ValidaCPF(request.getParameter("cpf"))){
						%>
							<div class="alert alert-danger alert-dismissible fade show" role="alert">
								CPF INVALIDO
								<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
							</div>
						<%
					}else{
					DecimalFormat df = new DecimalFormat("R$ ##,###,##0.00");		        		
					
					//Convertendo data
					SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd"); 
					Date date = new Date();
			        long timeInMilliSeconds = formato.parse(request.getParameter("nascimento")).getTime();
			        
					u.setNome(request.getParameter("nome"));
					u.setCpf(Long.parseLong(request.getParameter("cpf")));
					u.setSexo(request.getParameter("sexo").charAt(0));
					u.setNascimento(new java.sql.Date(timeInMilliSeconds));
					v.setNome(request.getParameter("nomeVeiculo"));
					v.setValor(Double.parseDouble(request.getParameter("valorVeiculo")));			
					u.setVeiculo(v);
					
					ud.Salvar(u);
					
					SimpleDateFormat formatoTabela = new SimpleDateFormat("dd-MM-yyyy");
		%>
		<div class="mt-5">
			<table class="table align-middle table-striped table-bordered ">
				<thead>
					<tr>
						<th scope="col">Nome</th>
						<th scope="col">CPF</th>
						<th scope="col">Sexo</th>
						<th scope="col">Nascimento</th>
						<th scope="col">Nome do Veículo</th>
						<th scope="col">Valor do Veículo</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td><%=u.nome%></td>
						<td><%=u.cpf%></td>
						<td><%=u.sexo%></td>
						<td><%=formatoTabela.format(u.nascimento)%></td>
						<td><%=u.veiculo.nome%></td>
						<td><%=df.format(u.veiculo.valor)%></td>
					</tr>
				</tbody>
			</table>
		</div>
		<%
			Seguro seguro = new Seguro();
			double valorTotal = seguro.calcularValorTotal(u);		
		%>
		<div class="mt-5">
			<table class="table align-middle table-striped table-bordered ">
				<thead>
					<tr>
						<th scope="col">Qtd parcelas</th>
						<th scope="col">Valor Parcela</th>
						<th scope="col">Valor Total</th>
					</tr>
				</thead>
				<tbody>
					<%
					for (int i = 1; i <= 12; i++) {
					%>
					<tr>
						<th><%=i%>x</th>
						<%
						if (i < 6) {
						%>
						<td><%=df.format(valorTotal / i)%></td>
						<td><%=df.format(valorTotal)%></td>
						<%
						} else if (i >= 6 && i <= 9) {
						%>
						<td><%=df.format(valorTotal / i * 0.03 + valorTotal / i)%></td>
						<td><%=df.format(valorTotal + valorTotal * 0.03)%> (Acréscimo de 3%)</td>
						<%
						} else {
						%>
						<td><%=df.format(valorTotal / i * 0.05 + valorTotal / i)%></td>
						<td><%=df.format(valorTotal + valorTotal * 0.05)%> (Acréscimo de 5%)</td>
						<%
						}
						%>
					</tr>
					<%} %>
				</tbody>
			</table>
		</div>
		<%}}}catch(Exception e){} %>	
	</div>
</body>
</html>