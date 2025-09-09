<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Crear Mascota</title>
    <link rel="stylesheet" href="styles/crear.css">
</head>
<body>
<div class="form-container">
    <h2>Crear Mascota</h2>
    <form action="${pageContext.request.contextPath}/SvCrudMascotas" method="POST">
        
        <!-- Campo oculto para la operación del switch -->
        <input type="hidden" name="operacion" value="Crear" />

        <label for="nombre">Nombre</label>
        <input type="text" id="nombre" name="nombre" maxlength="20" required />

        <label for="tipo">Tipo de Mascota</label>
        <input type="text" id="tipo" name="tipo" maxlength="50" required />

        <label for="raza">Raza</label>
        <input type="text" id="raza" name="raza" maxlength="50" required />

        <label for="sexo">Sexo</label>
        <select id="sexo" name="sexo" required>
            <option value="">Seleccione</option>
            <option value="macho">Macho</option>
            <option value="hembra">Hembra</option>
        </select>

        <label for="precioBase">Precio Base</label>
        <input type="number" step="0.01" id="precioBase" name="precioBase" required />

        <label for="precioVenta">Precio Venta</label>
        <input type="number" step="0.01" id="precioVenta" name="precioVenta" />

        <label for="estado">Estado</label>
        <select id="estado" name="estado" required>
            <option value="">Seleccione</option>
            <option value="disponible">Disponible</option>
            <option value="vendido">Vendido</option>
        </select>

        <label for="idCliente">ID Cliente</label>
        <input type="number" id="idCliente" name="idCliente" />

        <button type="submit" class="btn-submit">Crear</button>
    </form>
</div>
</body>
</html>
