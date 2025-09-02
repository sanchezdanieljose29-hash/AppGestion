<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <link rel="stylesheet" href="styles/login.css">
</head>
<body>

    <div class="login-container">
        <div class="login-header">Iniciar Sesión</div>

        <div class="success-msg">✅ Conexión exitosa a la base de datos</div>

        <form action="panel_de_control.jsp" method="post">
            <label for="cedula">Cédula</label>
            <input type="text" id="cedula" name="cedula" placeholder="Número de Cédula" required>

            <label for="contrasena">Contraseña</label>
            <input type="password" id="contrasena" name="contrasena" placeholder="Contraseña" required>

            <div class="form-check">
                <input type="checkbox" id="remember" name="remember">
                <label for="remember">Recordarme</label>
            </div>

            <button type="submit">Ingresar</button>
        </form>
    </div>

</body>
</html>