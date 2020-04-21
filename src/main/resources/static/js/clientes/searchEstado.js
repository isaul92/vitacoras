window.onload = function() {

	/*
	 * 
	 * var r={y:20};
	 */

	$("#descargarReporte").on(
			"click",
			function(e) {
				if ($("#fecha").val() == "" || $("#fecha2").val() == "") {
					alert("Ingere las fechas para buscar los Reportes")

				} else {
					window.location.replace("/estado/reportes/1/"
							+ $("#fecha").val() + "/" + $("#fecha2").val());
				}
			});

	$("#buton").on(
			"click",
			function(e) {
				if ($("#fecha").val() == "" || $("#fecha2").val() == "") {
					alert("Ingere las fechas")
					/*
					 * window.location.href = "/vitacora/indexPaginate/0/fecha/" +
					 * document.querySelector('#fechaBuscar').getAttribute(
					 * "data-date");
					 */

				} else {
					// alert($("#fecha1").val() + " " + $("#fecha2").val())
					window.location.replace("/estado/1/0/" + $("#fecha").val()
							+ "/" + $("#fecha2").val());
				}

			});

}