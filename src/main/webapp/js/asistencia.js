$(document).ready(function () {
    $("#btnAsistencia").click(function () {
        let codigo = $("#txtDni").val();

        let parametro = {
            tipo: 1 , codigo: codigo
        };

        $.getJSON("asistencia", parametro, function (data) {
            if (data.resultado === "ok") {
                alert('Asistencia registrada');
            }else{
                alert('Error');
            }
        });
    });
});


