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
					

							if ($("#inputCliente").val().trim() == "") {

								alert("Ingrese Nombre a Buscar")
							} else {
								window.location.href = "/clientes/indexPaginate/0/"
										+ $("#inputCliente").val();
							}

						
						// descripcionBuscarProducto
						// nombreBuscarProducto
						// idBuscarProducto

					});


}
