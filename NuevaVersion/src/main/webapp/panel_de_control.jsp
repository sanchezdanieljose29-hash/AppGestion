<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="controlador.Usuario" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Panel de Control</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<div class="container-fluid">
    <div class="row">
        <!-- Sidebar -->
        <nav class="col-md-2 d-md-block bg-dark sidebar vh-100">
            <div class="position-sticky p-3 text-white">
                <h2 class="h5">Menú</h2>
                <ul class="nav flex-column">
                    <li class="nav-item"><a class="nav-link text-white" href="#">🏠 Inicio</a></li>
                    <li class="nav-item"><a class="nav-link text-white" href="#">👤 Usuarios</a></li>
                    <li class="nav-item"><a class="nav-link text-white" href="registros.jsp">📋 Registros</a></li>
                    <li class="nav-item"><a class="nav-link text-white" href="index.jsp">🔒 Cerrar sesión</a></li>
                </ul>
            </div>
        </nav>

        <!-- Main content -->
        <main class="col-md-10 ms-sm-auto px-4">
            <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
                <h1 class="h3">Panel de Control</h1>
                <div>
                    <a href="Crear.jsp" class="btn btn-success">➕ Agregar nuevo</a>
                    <a href="ReporteClientespdf" class="btn btn-danger" target="_blank">📄 Generar PDF</a>
                </div>
            </div>

            <!-- Tabla de usuarios -->
            <div class="table-responsive">
                <table class="table table-striped table-hover align-middle">
                    <thead class="table-dark">
                        <tr>
                            <th>ID</th>
                            <th>Cédula</th>
                            <th>Nombre</th>
                            <th>Apellido</th>
                            <th>Clave</th>
                            <th>Acciones</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            List<Usuario> usuarios = (List<Usuario>) request.getAttribute("usuarios");
                            if (usuarios != null && !usuarios.isEmpty()) {
                                for (Usuario usuario : usuarios) {
                        %>
                        <tr>
                            <td><%= usuario.getId() %></td>
                            <td><%= usuario.getCedula() %></td>
                            <td><%= usuario.getNombre() %></td>
                            <td><%= usuario.getApellido() %></td>
                            <td><%= usuario.getClave() %></td>
                            <td>
                                <!-- Editar -->
                                <form action="Editar.jsp" method="GET" class="d-inline">
                                    <input type="hidden" name="id" value="<%= usuario.getId() %>" />
                                    <input type="hidden" name="cedula" value="<%= usuario.getCedula() %>" />
                                    <input type="hidden" name="nombre" value="<%= usuario.getNombre() %>" />
                                    <input type="hidden" name="apellido" value="<%= usuario.getApellido() %>" />
                                    <input type="hidden" name="clave" value="<%= usuario.getClave() %>" />
                                    <button class="btn btn-sm btn-warning" name ="Actualizar" value="Actualizar">✏️ Actualizar</button>
                                </form>

                                <form action="<%= request.getContextPath() %>/SvCrud" method="POST" class="d-inline" onsubmit="return confirm('¿Eliminar este usuario?');">
                                    <input type="hidden" name="operacion" value="Eliminar" />
                                    <input type="hidden" name="id" value="<%= usuario.getId()%>"/>
                                    <button class="btn btn-sm btn-danger">🗑️ Eliminar</button>
                                </form>
                            </td>
                        </tr>
                        <%
                                }
                            } else {
                        %>
                        <tr><td colspan="6" class="text-center">No hay usuarios para mostrar.</td></tr>
                        <%
                            }
                        %>
                    </tbody>
                </table>
            </div>
        </main>
    </div>
</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
