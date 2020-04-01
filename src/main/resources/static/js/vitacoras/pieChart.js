window.onload = function() {

	var fecha1 = moment().startOf('month').format('YYYY-MM-DD');
	var fecha2 = moment().endOf('month').format('YYYY-MM-DD hh:mm');

	this.ajax(fecha1, fecha2);

	/*
	 * 
	 * var r={y:20};
	 */

	
	$("#descargarReporte").on("click",function(e){
		if ($("#fecha1").val() == "" || $("#fecha2").val() == "") {
			
			window.location.replace("/vitacora/reportes");

		}else{
			window.location.replace("/apiVitacora/reportes/"+$("#fecha1").val()+"/"+$("#fecha2").val());
		}
	});
	
	
	$("#buton").on("click", function(e) {

		if ($("#fecha1").val() == "" || $("#fecha2").val() == "") {
			console.log("No hay fecha");
			alert("Falta ingresar una fecha");

		} else {
			ajax($("#fecha1").val(), $("#fecha2").val());
			console.log($("#fecha1").val());

		}

	});

}

function ajax(fecha, fecha2) {

	var d = [];
	var r;
	$.ajax({

		type : "POST",
		url : "/apiVitacora/reporte/" + fecha + "/" + fecha2,
		dataType : "json"
	}).done(function(data) {

		if (!$.isEmptyObject(data)) {

			for ( var i in data) {
				r = {
					label : data[i].nombre,
					y : data[i].numeroReportes
				};

				d.push(r);

			}

			var options = {
				title : {
					text : "Reporte De Actividades"
				},
				data : [ {
					type : "pie",
					startAngle : 45,
					showInLegend : "true",
					legendText : "{label}",
					indexLabel : "{label} ({y})",
					yValueFormatString : "#,##0.#" % "",
					dataPoints : d
				} ]
			};
			$("#chartContainer").CanvasJSChart(options);
		} else {
			alert("¡No hay datos para mostrar un reporte!");
		}
	});

}