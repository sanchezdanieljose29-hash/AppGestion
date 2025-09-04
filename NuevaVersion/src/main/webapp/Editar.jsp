<div class="container mt-5">
    <div class="card shadow">
        <div class="card-header bg-primary text-white">
            <h2 class="mb-0">Editar Usuario</h2>
        </div>
        <div class="card-body">
            <form action="${pageContext.request.contextPath}/SvCrud" method="POST">
                <!-- Operación -->
                <input type="hidden" name="operacion" value="Actualizar" />

                <!-- ID oculto -->
                <input type="hidden" name="id" value="<%= request.getParameter("id") != null ? request.getParameter("id") : "" %>" />

                <!-- Cédula -->
                <div class="mb-3">
                    <label for="cedula" class="form-label">Cédula</label>
                    <input type="text" id="cedula" name="cedula" class="form-control" 
                           value="<%= request.getParameter("cedula") != null ? request.getParameter("cedula") : "" %>" readonly />
                </div>

                <!-- Nombre -->
                <div class="mb-3">
                    <label for="nombre" class="form-label">Nombre</label>
                    <input type="text" id="nombre" name="nombre" class="form-control" required
                           value="<%= request.getParameter("nombre") != null ? request.getParameter("nombre") : "" %>" />
                </div>

                <!-- Apellido -->
                <div class="mb-3">
                    <label for="apellido" class="form-label">Apellido</label>
                    <input type="text" id="apellido" name="apellido" class="form-control" required
                           value="<%= request.getParameter("apellido") != null ? request.getParameter("apellido") : "" %>" />
                </div>

                <!-- Clave -->
                <div class="mb-3">
                    <label for="clave" class="form-label">Clave</label>
                    <input type="password" id="clave" name="clave" class="form-control" required
                           value="<%= request.getParameter("clave") != null ? request.getParameter("clave") : "" %>" />
                </div>

                <!-- Botones -->
                <div class="d-flex justify-content-between">
                    <button type="submit" class="btn btn-success">Actualizar</button>
                    <a href="SvPanel" class="btn btn-secondary">Cancelar</a>
                </div>
            </form>
        </div>
    </div>
</div>
