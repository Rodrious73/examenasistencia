$(document).ready(function () {
    $("#btnCambiarContrasenia").click(function () {
        let codiPers = $("#txtCodiPers").val();
        let nuevo_pass = $("#txtPassNuevo").val();

        let parametro = {
            accion: 2 , codiPers: codiPers, pass: nuevo_pass
        };

        $.getJSON("validar", parametro, function (data) {
            if (data.resultado === "ok") {
                $(location).attr('href', 'index.html');
            } else {
                alert("No se encontro el codigo proporcionado");
            }
        });
    });
});


