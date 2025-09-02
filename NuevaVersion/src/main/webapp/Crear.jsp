<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Crear Usuario</title>
    <link rel="stylesheet" href="styles/crear.css">
</head>
<body>
<div class="form-container">
    <h2>Crear Usuario</h2>
    <form action="${pageContext.request.contextPath}/SvCrud" method="POST">

      
      <!-- Campo oculto para la operación del switch -->
      <input type="hidden" name="operacion" value="Crear" />

      

      <label for="cedula">Cédula</label>
      <input type="text" id="cedula" name="cedula" maxlength="20" required />

      <label for="nombre">Nombre</label>
      <input type="text" id="nombre" name="nombre" maxlength="50" required />

      <label for="apellido">Apellido</label>
      <input type="text" id="apellido" name="apellido" maxlength="50" required />

      <label for="clave">Clave</label>
      <input type="password" id="clave" name="clave" minlength="6" required />

      <button type="submit" class="btn-submit">Crear</button>
    </form>
</div>
</body>
</html>
