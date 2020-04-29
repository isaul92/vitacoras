$(document).on("change", ".cantidad", function(e) {
	id = $(this).attr("name");
	cantidad = $("#cantidad" + id).val();
	$("#agregar" + id).attr("href", "/ordenes/add/" + id + "/" + cantidad);
	console.log("/ordenes/add/" + id + "/" + cantidad)
});
