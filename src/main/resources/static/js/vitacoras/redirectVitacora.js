$("#buscar").on(
		"click",
		function(e) {
			if ($("#buscadorLicencia").val() == ""
					&& $("#fechaBuscar").val() == "") {
				alert("!Ingrese un valor!");
			} else if ($("#buscadorLicencia").val() != ""
					&& $("#fechaBuscar").val() == "") {
				window.location.href = "/vitacora/indexPaginate/0/"
						+ $("#buscadorLicencia").val();
			} else if ($("#buscadorLicencia").val() == ""
					&& $("#fechaBuscar").val() != "") {

				window.location.href = "/vitacora/indexPaginate/0/fecha/"
						+ document.querySelector('#fechaBuscar').getAttribute( "data-date");
			} else if ($("#buscadorLicencia").val() != ""
					&& $("#fechaBuscar").val() != "") {

				window.location.href = "/vitacora/indexPaginate/0/"+$("#buscadorLicencia").val() +'/'
						+ document.querySelector('#fechaBuscar').getAttribute( "data-date");
			}
					

		});
$("#fecha1").on(
		"change",
		function() {
			this.setAttribute("data-date", moment(this.value, "YYYY-MM-DD")
					.format(this.getAttribute("data-date-format")))
		}).trigger("change")
		$("#fechaBuscar").on(
		"change",
		function() {
			this.setAttribute("data-date", moment(this.value, "YYYY-MM-DD")
					.format(this.getAttribute("data-date-format")))
		}).trigger("change")

$("#fecha2").on(
		"change",
		function() {
			this.setAttribute("data-date", moment(this.value, "YYYY-MM-DD")
					.format(this.getAttribute("data-date-format")))
		}).trigger("change")