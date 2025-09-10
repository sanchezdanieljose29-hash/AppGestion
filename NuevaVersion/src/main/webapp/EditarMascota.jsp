<div class="form-container">
    <h1>Editar Mascota</h1>
    <form action="${pageContext.request.contextPath}/SvCrudMascotas" method="POST">
        <input type="hidden" name="operacion" value="Actualizar" />
        
        <!-- Campo oculto para el ID -->
        <input type="hidden" name="id" 
               value="<%= request.getParameter("id") != null ? request.getParameter("id") : "" %>" />

        <label for="nombre">Nombre:</label><br />
        <input type="text" id="nombre" name="nombre"
               value="<%= request.getParameter("nombre") != null ? request.getParameter("nombre") : "" %>" required /><br /><br />

        <label for="tipo">Tipo:</label><br />
        <input type="text" id="tipo" name="tipo"
               value="<%= request.getParameter("tipo") != null ? request.getParameter("tipo") : "" %>" required /><br /><br />

        <label for="raza">Raza:</label><br />
        <input type="text" id="raza" name="raza"
               value="<%= request.getParameter("raza") != null ? request.getParameter("raza") : "" %>" required /><br /><br />

        <label for="sexo">Sexo:</label><br />
        <select id="sexo" name="sexo" required>
            <option value="macho" <%= "macho".equals(request.getParameter("sexo")) ? "selected" : "" %>>Macho</option>
            <option value="hembra" <%= "hembra".equals(request.getParameter("sexo")) ? "selected" : "" %>>Hembra</option>
        </select><br /><br />

        <label for="precioBase">Precio Base:</label><br />
        <input type="number" id="precioBase" name="precioBase" step="1" 
               value="<%= request.getParameter("precioBase") != null ? request.getParameter("precioBase") : "" %>" required /><br /><br />

        <label for="precioVenta">Precio Venta:</label><br />
        <input type="number" id="precioVenta" name="precioVenta" step="1" 
               value="<%= request.getParameter("precioVenta") != null ? request.getParameter("precioVenta") : "" %>" required /><br /><br />

        <label for="estado">Estado:</label><br />
        <select id="estado" name="estado" required>
            <option value="disponible" <%= "disponible".equals(request.getParameter("estado")) ? "selected" : "" %>>Disponible</option>
            <option value="vendido" <%= "vendido".equals(request.getParameter("estado")) ? "selected" : "" %>>Vendido</option>
        </select><br /><br />

        <label for="idCliente">ID Cliente:</label><br />
        <input type="number" id="idCliente" name="idCliente"
               value="<%= request.getParameter("idCliente") != null ? request.getParameter("idCliente") : "" %>" required /><br /><br />

        <button type="submit">Actualizar</button>
        <a href="SvPanelMascotas">Cancelar</a>
    </form>
</div>  	