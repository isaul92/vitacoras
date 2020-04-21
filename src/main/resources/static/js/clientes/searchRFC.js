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
					

							if ($("#inputRFC").val().trim() == "") {

								alert("Ingrese RFC a Buscar")
							} else {
								window.location.href = "/datosFiscales/indexPaginate/0/"
										+ $("#inputRFC").val();
							}

						
						// descripcionBuscarProducto
						// nombreBuscarProducto
						// idBuscarProducto

					});


}