$(document).ready(function(e) {
	getsubCat($("#cusategoriaActiv.id").val());

});

$(".categoriasForm").change(function() {
	var select = document.getElementById("cusategoriaActiv.id"), // El
	// <select>
	value = select.value, // El valor seleccionado
	text = select.options[select.selectedIndex].innerText; // El texto de la
	// opción
	getsubCat(value); // seleccionada
	console.log(value);

});

function getsubCat(id) {
	$
			.ajax({

				type : "POST",
				"url" : "/apiVitacora/subCategoria/" + id,
				dataType : 'json'

			})
			.done(
					function(data) {

						$("#subCategorias").empty();

						var result = [];
						for ( var i in data) {
							result.push([ i, data[i] ]);
						}

						var div = "<div class='form-group'> 	<label for='categoria'>Descripcion</label> <select class='form-control' name='subCat.id' id='subCat.id'>";

						for ( var i in data) {
							div += "<option value='" + data[i].id + "'>"
									+ data[i].nombre + "</option>";

						}
						div += "</div>";
						$("#subCategorias").append(div);
					});

}
