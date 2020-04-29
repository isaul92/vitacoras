$(function() {

	$("#producto").autocomplete({
		minLength : 0,
		source : "/productosApi/buscador",
		focus : function(event, ui) {

			return false;
		},
		select : function(event, ui) {
			/*
			 * $("#producto").val(ui.item.nombre + " " + ui.item.id);
			 * $("#project-id").val(ui.item.nombre);
			 * $("#project-description").html(ui.item.nombre + " " +
			 * ui.item.id); $("#producto").val(ui.item.id);
			 */

			window.location.replace("/ordenes/add/" + ui.item.id + "/1");
			return false;
		}
	}).autocomplete("instance")._renderItem = function(ul, item) {

		return $("<li>").append(
				"<div>" + item.nombre + " " + item.descr + " $" + item.precio
						+ "</div>").appendTo(ul);
	};
});