<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<link rel="stylesheet" href="${origen}/css/amelia.css">
		<title>Programacion Avanzada II - Trabajo practico final</title>
	</head>
	
	<body>
		<c:if test="${empty origen}">
			<c:set var="origen" scope="session" value="<%=request.getContextPath()%>"/>
		</c:if>
		
		<nav class="navbar navbar-default navbar-fixed-top" role="navigation">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
					<span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span>
					<span class="icon-bar"></span> <span class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="${origen}/index">Electronica Don Pepe</a>
			</div>
			<center>
				<div class="navbar-brand">Ventas: detalle</div>
			</center>
			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav navbar-right">
					<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown">${usuario.username}<b class="caret"></b></a>
						<ul class="dropdown-menu">
							<li>
								<a href="${origen}/logout">Cerrar sesión</a>
							</li>
						</ul>
					</li>
				</ul>
			</div>
		</nav>
		<div class="container">
			<div class="page-header">
				<h3>Detalles de la venta del ${venta.fecha}</h3>
				<table class="table">
					<thead>
						<tr>
							<td><center>Id</center></td>
							<td><center>Nombre</center></td>
							<td><center>Precio Unitario</center></td>
						</tr>
					</thead>
					<tbody>
						<c:if test="${not empty lista}">
							<c:forEach items="${lista}" var="item">
								<tr>
									<td><center>${item.id}</center></td>
									<td><center>${item.nombre}</center></td>
									<td><center>${item.precioUnitario}</center></td>
								</tr>
							</c:forEach>
						</c:if>
					</tbody>
				</table>
			</div>
			<div>
				Total: ${venta.importe} <br>
				Vendedor: ${venta.vendedor.nombre} ${venta.vendedor.apellido}
			</div>
		</div>
		<script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
		<!-- <script src="js/bootstrap.min.js"></script> -->
		<script src="${origen}/js/bootstrap.js"></script>
	</body>
</html>