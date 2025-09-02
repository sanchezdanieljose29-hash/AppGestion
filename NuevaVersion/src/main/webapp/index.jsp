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

        <form action="SvLogin" method="POST">
            <label for="cedula">Cédula</label>
            <input type="text" id="cedula" name="cedula" placeholder="Número de Cédula" required>

            <label for="clave">Contraseña</label>
            <input type="password" id="contrasena" name="clave" placeholder="Contraseña" required>

            <div class="form-check">
                <input type="checkbox" id="remember" name="remember">
                <label for="remember">Recordarme</label>
            </div>

            <button type="submit">Ingresar</button>
        </form>
    </div>

</body>
</html>