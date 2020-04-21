window.onload = function() {
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
						if (accion == "descripcionBuscarProducto") {

							if ($("#inputBuscarProductoId").val().trim() == "") {

								alert("Ingrese Descripcion a Buscar")
							} else {
								window.location.href = "/productosService/indexPaginate/0/desc/"
										+ $("#inputBuscarProductoId").val();
							}

						} else if (accion == "nombreBuscarProducto") {

							if ($("#inputBuscarProductoId").val().trim() == "") {

								alert("Ingrese Nombre a Buscar")
							} else {
								window.location.href = "/productosService/indexPaginate/0/nombre/"
										+ $("#inputBuscarProductoId").val();
							}
						} else {
							numero = $("#inputBuscarProductoId").val();
						numero=tiene_letras(numero)
							if (numero==1
									|| $("#inputBuscarProductoId").val().trim() == "") {

								alert("Ingrese Id a Buscar")
							} else {
								window.location.href = "/productosService/indexPaginate/0/id/"
										+ $("#inputBuscarProductoId").val();
							}
						}
						// descripcionBuscarProducto
						// nombreBuscarProducto
						// idBuscarProducto

					});
	function tiene_letras(texto) {
		var letras="abcdefghyjklmnñopqrstuvwxyz";
		texto = texto.toLowerCase();
		for (i = 0; i < texto.length; i++) {
			if (letras.indexOf(texto.charAt(i), 0) != -1) {
				return 1;
			}
		}
		return 0;
	}

}
