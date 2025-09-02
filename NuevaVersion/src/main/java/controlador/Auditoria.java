	package controlador;

	import java.time.LocalDateTime;

	public class Auditoria {
		private int id;
		private String usuarioId;
		private String operacion;
		private LocalDateTime fechaHora;

		public Auditoria() {
		}

		public Auditoria(int id, String usuarioId, String operacion, LocalDateTime fechaHora) {
			this.id = id;
			this.usuarioId = usuarioId;
			this.operacion = operacion;
			this.fechaHora = fechaHora;
		}

		// Getters y Setters
		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getUsuarioId() {
			return usuarioId;
		}

		public void setUsuarioId(String usuarioId) {
			this.usuarioId = usuarioId;
		}

		public String getOperacion() {
			return operacion;
		}

		public void setOperacion(String operacion) {
			this.operacion = operacion;
		}

		public LocalDateTime getFechaHora() {
			return fechaHora;
		}

		public void setFechaHora(LocalDateTime fechaHora) {
			this.fechaHora = fechaHora;
		}
	}
