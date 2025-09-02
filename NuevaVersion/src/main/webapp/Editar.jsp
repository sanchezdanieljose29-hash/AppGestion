<div class="form-container">
    <h1>Editar Usuario</h1>
    <form action="${pageContext.request.contextPath}/SvCrud" method="POST">
        <input type="hidden" name="operacion" value="Actualizar" />
        
        <!-- Campo oculto para el ID -->
        <input type="hidden" name="id" value="<%= request.getParameter("id") != null ? request.getParameter("id") : "" %>" />

        <label for="cedula">Cédula:</label><br />
        <input type="text" id="cedula" name="cedula"
               value="<%= request.getParameter("cedula") != null ? request.getParameter("cedula") : "" %>" readonly /><br /><br />
        
        <label for="nombre">Nombre:</label><br />
        <input type="text" id="nombre" name="nombre"
               value="<%= request.getParameter("nombre") != null ? request.getParameter("nombre") : "" %>" required /><br /><br />
        
        <label for="apellido">Apellido:</label><br />
        <input type="text" id="apellido" name="apellido"
               value="<%= request.getParameter("apellido") != null ? request.getParameter("apellido") : "" %>" required /><br /><br />
        
        <label for="clave">Clave:</label><br />
        <input type="password" id="clave" name="clave"
               value="<%= request.getParameter("clave") != null ? request.getParameter("clave") : "" %>" required /><br /><br />
        
        <button type="submit">Actualizar</button>
        <a href="SvPanel">Cancelar</a>
    </form>
</div>
