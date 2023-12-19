$(document).ready(function () {
    $("#btnIniciarSesion").click(function () {
        let logi = $("#txtLogi").val();
        let pass = $("#txtPass").val();
        let tipo = $("#cmbTipo").val();

        let parametro = {
            accion: 1 , logi: logi, pass: pass, tipo: tipo
        };

        $.getJSON("validar", parametro, function (data) {
            if (data.resultado === "1") {
                $(location).attr('href', 'principal.html');
            } else if (data.resultado === "2"){
                $(location).attr('href', 'asistencia.html');
            }else{
                 alert("¿Eres Administrador? o logi y/o pass incorrecto.");
            }
        });
    });
});