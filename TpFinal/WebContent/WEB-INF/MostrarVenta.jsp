<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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
				<div class="navbar-brand">Ventas: Consulta</div>
			</center>
			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav navbar-right">
					<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown">${usuario.username}<b class="caret"></b></a>
						<ul class="dropdown-menu">
							<li><a href="${origen}/logout">Cerrar sesión</a></li>
						</ul>
					</li>
				</ul>
			</div>
			<!-- /.navbar-collapse -->
		</nav>
		<div class="container">
			<div class="page-header">
				<h3>Logueado como ${usuario.vendedor.nombre} ${usuario.vendedor.apellido}</h4><br>
				<h4>Fechas a filtrar</h3>
				<div>
					<form action="consulta" method="post">
						<div>
							<label id="desde">Desde: </label><br>
							<div class="row">
								<div class="col-md-2"><input name="desdeDia" type="text" alt="dia" class="form-control" placeholder="dia"></div>
								<div class="col-md-2"><input name="desdeMes" type="text" alt="mes" class="form-control" placeholder="mes"></div>
								<div class="col-md-2"><input name="desdeAnio" type="text" alt="anio" class="form-control" placeholder="año"></div>
							</div>
						</div>
						<div>
							<label id="hasta">Hasta: </label><br>
							<div class="row">
							<div class="col-md-2"><input name="hastaDia" type="text" alt="dia" class="form-control" placeholder="dia"></div>
							<div class="col-md-2"><input name="hastaMes" type="text" alt="mes" class="form-control" placeholder="mes"></div>
							<div class="col-md-2"><input name="hastaAnio" type="text" alt="anio" class="form-control" placeholder="año"></div>
							</div>
						</div>
						<input type="submit" value="Aceptar">
					</form>
				</div>
				<br>
				<div class="row">
						<table class="table">
							<thead>
								<tr>
									<td><center>Nro</center></td>
									<td><center>Fecha</center></td>
									<td><center>Importe</center></td>
									<td><center>Vendedor</center></td>
									<td><center>Detalles</center></td>
								</tr>
							</thead>
							<tbody>
								<c:if test="${not empty lista}">
									<c:forEach items="${lista}" var="item">
										<tr>
											<td><center>${item.id}</center></td>
											<td><center>${item.fecha}</center></td>
											<td><center>${item.importe}</center></td>
											<td><center>${item.vendedor.nombre} ${item.vendedor.apellido}</center></td>
											<%-- <td><center><a href="${origen}/venta/consulta/detalle?venta=${item.id}">Ir</a></center></td> --%>
											<td><center><button class="btn btn-link" data-toggle="modal" data-target="#item${item.id}"> Ver</button></center>
											<div class="modal fade" id="item${item.id}" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
											  <div class="modal-dialog">
											    <div class="modal-content">
											      <div class="modal-header">
											        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
											        <h4 class="modal-title" id="myModalLabel">Venta id ${item.id} del <fmt:formatDate type="date" value="${item.fecha}"/></h4>
											      </div>
											      <div class="modal-body">
											       <table class="table">
														<thead>
															<tr>
																<td><center>Id</center></td>
																<td><center>Nombre</center></td>
																<td><center>Precio Unitario</center></td>
															</tr>
														</thead>
														<tbody>
																<c:forEach items="${item.productos}" var="vendido">
																	<tr>
																		<td><center>${vendido.id}</center></td>
																		<td><center>${vendido.nombre}</center></td>
																		<td><center><fmt:formatNumber type="currency" currencyCode="ARS">${vendido.precioUnitario}</fmt:formatNumber></center></td>
																	</tr>
																</c:forEach>
														</tbody>
													</table>
													Total: <fmt:formatNumber type="currency" currencyCode="ARS">${item.importe}</fmt:formatNumber>
											      </div><!-- /.modal-body -->
											    </div><!-- /.modal-content -->
											  </div><!-- /.modal-dialog -->
											</div><!-- /.modal -->
											</td>
										</tr>
									</c:forEach>
								</c:if>
								<c:if test="${empty lista}">
									<tr><td rowspan="4">No existen resultados</td></tr>
								</c:if>
							</tbody>
						</table>
					</div>
					<!-- <div class="col-md-4">
						<c:if test="${detalle}">
							<c:forEach items="${lista}" var="item">
							<div><label id="nombre">${detalle.nombre}</label></div><br>
							</c:forEach>
						</c:if>
					</div> -->
			</div>
		</div>
		<script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
		<!-- <script src="js/bootstrap.min.js"></script> -->
		<script src="${origen}/js/bootstrap.js"></script>
		<script type="text/javascript" src="${origen}/js/jquery.meiomask.js" charset="utf-8" ></script>
		<script type="text/javascript" src="${origen}/js/MeioMaskStart.js"></script>
	</body>
</html>