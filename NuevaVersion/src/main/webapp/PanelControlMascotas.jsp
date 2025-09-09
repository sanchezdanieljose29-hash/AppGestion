<%@page import="controlador.Mascotas"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Panel de Control</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"/>
</head>
<body class="bg-light">

<div class="container-fluid">
    <div class="row">
        <!-- Sidebar -->
        <nav class="col-md-2 d-md-block bg-dark sidebar vh-100">
            <div class="position-sticky p-3 text-white">
                <h2 class="h5">Menú</h2>
                <ul class="nav flex-column">
                    <li class="nav-item"><a class="nav-link text-white" href="masas">🏠 Inicio</a></li>
                   <a class="nav-link text-white" href="SvPanelMascotas">👤 Usuarios</a>
                     <a class="nav-link text-white" href="/SvPanelMascotas">🐕 Mascotas</a>
                    <li class="nav-item"><a class="nav-link text-white" href="usuarios">👤 Clientes</a></li>
                    
                </ul>
            </div>
        </nav>

        <main class="col-md-10 ms-sm-auto px-4">
    <!-- Encabezado -->
    <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">    
        <h1 class="h3">Panel de Control</h1>
        <div>
            <a href="CrearMascota.jsp" class="btn btn-success">➕ Agregar nuevo</a>
            <a href="ReporteClientespdf" class="btn btn-danger" target="_blank">📄 Generar PDF</a>
            <form action="EnvioCorreo" method="POST" target="_blank" style="display: inline;">
                <button type="submit" class="btn btn-danger">📧 Enviar Correo</button>
            </form>
        </div>
    </div>

    <!-- Tabla de mascotas -->
    <div class="table-responsive">
        <table class="table table-striped table-hover align-middle">
            <thead class="table-dark">
                <tr>
                    <th>ID</th>
                    <th>Nombre</th>
                    <th>Tipo</th>
                    <th>Raza</th>
                    <th>Sexo</th>
                    <th>Precio Base</th>
                    <th>Precio Venta</th>
                    <th>Estado</th>
                    <th>Dueño</th>
                    <th>Acciones</th>
                </tr>
            </thead>
            <tbody>
                <%
                    List<Mascotas> mascotas = (List<Mascotas>) request.getAttribute("Mascotas");
                    if (mascotas != null && !mascotas.isEmpty()) {
                        for (Mascotas mascota : mascotas) {
                %>
                <tr>
                    <td><%=mascota.getId()%></td>
                    <td><%=mascota.getNombre()%></td>
                    <td><%=mascota.getTipo()%></td>
                    <td><%=mascota.getRaza()%></td>
                    <td><%=mascota.getSexo()%></td>
                    <td><%=mascota.getPrecioBase()%></td>
                    <td><%=mascota.getPrecioVenta()%></td>
                    <td><%=mascota.getEstado()%></td>
                    <td><%=mascota.getIdCliente()%></td>
                    <td>
                        <!-- Botón Editar -->
                        <form action="Editar.jsp" method="GET" class="d-inline">
                            <input type="hidden" name="id" value="<%=mascota.getId()%>" />
                            <input type="hidden" name="nombre" value="<%=mascota.getNombre()%>" />
                            <input type="hidden" name="tipo" value="<%=mascota.getTipo()%>" />
                            <input type="hidden" name="raza" value="<%=mascota.getRaza()%>" />
                            <input type="hidden" name="sexo" value="<%=mascota.getSexo()%>" />
                            <input type="hidden" name="precioBase" value="<%=mascota.getPrecioBase()%>" />
                            <input type="hidden" name="precioVenta" value="<%=mascota.getPrecioVenta()%>" />
                            <input type="hidden" name="estado" value="<%=mascota.getEstado()%>" />
                            <input type="hidden" name="idCliente" value="<%=mascota.getIdCliente()%>" />
                            <button class="btn btn-sm btn-warning" name="Actualizar" value="Actualizar">✏️ Actualizar</button>
                        </form>

                        <!-- Botón Eliminar -->
                        <form action="<%=request.getContextPath()%>/SvCrudMascotas" method="POST" class="d-inline" onsubmit="return confirm('¿Eliminar esta mascota?');">
                            <input type="hidden" name="operacion" value="Eliminar" />
                            <input type="hidden" name="id" value="<%=mascota.getId()%>" />
                            <button class="btn btn-sm btn-danger">🗑️ Eliminar</button>
                        </form>
                    </td>
                </tr>
                <%
                        }
                    } else {
                %>
                <tr>
                    <td colspan="10" class="text-center">No hay mascotas para mostrar.</td>
                </tr>
                <%
                    }
                %>
            </tbody>
        </table>
    </div>
</main>
           

        </div>

        </main>
    </div>
</div>

<footer class="text-center py-3 bg-light border-top">
    <p>Mi sistema © 2025</p>
</footer>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
