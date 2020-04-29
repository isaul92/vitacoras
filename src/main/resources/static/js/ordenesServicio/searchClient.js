$(function() {

	$("#project")
			.autocomplete(
					{
						minLength : 0,
						source : "/clientesApi/buscador",
						focus : function(event, ui) {

							return false;
						},
						select : function(event, ui) {
							$("#project").val(
									ui.item.nombre + " " + ui.item.apellidos);
							$("#project-id").val(ui.item.nombre);
							$("#datosCliente").html(
									ui.item.nombre + " " + ui.item.apellidos
											+ "<br>" + ui.item.correo + "<br>"
											+ ui.item.telefono);
							$("#clienteId").val(ui.item.id);
							return false;
						}
					}).autocomplete("instance")._renderItem = function(ul, item) {

		return $("<li>").append(
				"<div>" + item.nombre + " " + item.apellidos + "</div>")
				.appendTo(ul);
	};
});