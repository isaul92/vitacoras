window.onload = function() {

	
	
	$(function() {

		$("#buscadorOrdenes")
				.autocomplete(
						{
							minLength : 0,
							source : "/clientesApi/buscador",
							focus : function(event, ui) {

								return false;
							},
							select : function(event, ui) {
								$("#buscadorOrdenes").val(
										ui.item.nombre + " " + ui.item.apellidos);
							
								window.location.href = "/ordenes/indexPaginate/0/nombre/"
									+ ui.item.id;
							
								return false;
							}
						}).autocomplete("instance")._renderItem = function(ul, item) {

			return $("<li>").append(
					"<div>" + item.nombre + " " + item.apellidos + "</div>")
					.appendTo(ul);
		};
	});
	
	
	



	$("#buscar")
			.click(
					function() {
						/*
						 * var select =
						 * document.getElementById("cusategoriaActiv.id"), // El //
						 * <select> value = select.value, // El valor
						 * seleccionado text =
						 * select.options[select.selectedIndex].innerText; // El
						 * texto de la // opción getsubCat(value); //
						 * seleccionada console.log(value);
						 */

						var accion = $('input:radio[name=options]:checked')
								.val();

						if (accion == "idbuscarOrden") {

							if ($("#buscadorOrdenes").val().trim() == "") {

								alert("Ingrese Folio de Orden de Servicio a Buscar")
							} else {

								if (tiene_letras($("#buscadorOrdenes").val()) != 1) {
									window.location.href = "/ordenes/indexPaginate/0/folio/"
											+ $("#buscadorOrdenes").val();
								} else {
									alert("El folio debe de ser numerico")
								}

							}

						} else if (accion == "nombreBuscarProducto") {

							if ($("#buscadorOrdenes").val().trim() == "") {

								alert("Ingrese Nombre a Buscar")
							} else {}
						} else {

							if ($("#fechaBuscar").val().trim() == "") {

								alert("Ingrese Fecha a Buscar")
							} else {
								window.location.href = "/ordenes/indexPaginate/0/fecha/"
										+ $("#fechaBuscar").val();
							}
						}
						// descripcionBuscarProducto
						// nombreBuscarProducto
						// idBuscarProducto

					});
	function tiene_letras(texto) {
		var letras = "abcdefghyjklmnñopqrstuvwxyz";
		texto = texto.toLowerCase();
		for (i = 0; i < texto.length; i++) {
			if (letras.indexOf(texto.charAt(i), 0) != -1) {
				return 1;
			}
		}
		return 0;
	}

	$(document).on("click", "#fechaVigencia", function(e) {

		$("#fechaBuscar").removeAttr('style');
		$("#buscadorOrdenes").css("display", "none");

	});

	$(document).on("click", "#idOrden,#nombreOrden", function(e) {

		$("#buscadorOrdenes").css("display", "block");

		$("#fechaBuscar").css("display", "none")

	});

}
