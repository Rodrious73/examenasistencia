$(document).ready(function () {
    let datatable = $("#dataTable").DataTable({
        ajax: 'personalcrud?tipo=1',
        columns: [
            {data: 'codiPers'},
            {data: 'appaPers'},
            {data: 'apmaPers'},
            {data: 'nombPers'},
            {data: 'fechNaciPers'},
            {data: 'pesoPers'},
            {
                data: null,
                render: function (data, type, row) {
                    return '<button class="btn btn-warning btn-sm" onclick="abrirModalEditar(' + row.codiPers + ')"><i class="fa-regular fa-pen-to-square"></i></button>'
                }
            }
        ],
        columnDefs: [
            {orderable: false, targets: [6]}
        ],
        pageLength: 5,
        lengthMenu: [5, 10, 25, 50, 100],
        language: {
            url: "https://cdn.datatables.net/plug-ins/1.10.25/i18n/Spanish.json"
        }
    });
});

function abrirModalEditar(codigoPersonal) {
    obtenerPersonalPorCodigo(codigoPersonal, function (personal) {
        $('#idPersonal').val(personal.codiPers);
        $('#txtNombres').val(personal.nombPers);
        $('#txtApPaterno').val(personal.appaPers);
        $('#txtApMaterno').val(personal.apmaPers);
        $('#txtPeso').val(personal.pesoPers);
        var fechaNacimiento = new Date(personal.fechNaciPers);
        var fechaFormateada = fechaNacimiento.toISOString().split('T')[0];
        $('#txtFechaNacimiento').val(fechaFormateada);
        $('#cmbTipo').val(personal.codiTipo);
        $('#modalEditar').modal('show');
    });
}

function obtenerPersonalPorCodigo(codigoPersonal, callback) {
    $.getJSON("personalcrud?tipo=2&codigo=" + codigoPersonal, function (data) {
        if (data.resultado === "error") {
            alert("No se encontró el código");
        } else {
            callback({
                codiPers: codigoPersonal,
                nombPers: data.nombPers,
                appaPers: data.appaPers,
                apmaPers: data.apmaPers,
                pesoPers: data.pesoPers,
                fechNaciPers: data.fechNaciPers,
                codiTipo: data.codiTipo
            });
        }
    });
}

$("#btnEditar").click(function () {
    let codigo = $('#idPersonal').val();
    let nombres = $('#txtNombres').val();
    let apPaterno = $('#txtApPaterno').val();
    let apMaterno = $('#txtApMaterno').val();
    let peso = $('#txtPeso').val();
    let fechaNacimiento = $('#txtFechaNacimiento').val();
    let rol = $("#cmbTipo").val();

    $.ajax({
        url: 'personalcrud',
        type: 'POST',
        data: {
            tipo: '3',
            codigo: codigo,
            nombres: nombres,
            appa: apPaterno,
            apma: apMaterno,
            peso: peso,
            rol: rol,
            fecha: fechaNacimiento
        },
        dataType: 'json',
        success: function (response) {
            if (response.resultado === 'ok') {
                $('#dataTable').DataTable().ajax.reload();
            } else {
                alert('error');
            }
        }
    });

    $('#modalEditar').modal('hide');
});

$('#btnAgregar').click(function () {
    let codigo = $('#idPersonalAgregar').val();
    let nombres = $('#txtNombresAgregar').val();
    let apPaterno = $('#txtApPaternoAgregar').val();
    let apMaterno = $('#txtApMaternoAgregar').val();
    let peso = $('#txtPesoAgregar').val();
    let fechaNacimiento = $('#txtFechaNacimientoAgregar').val();
    let logi = $('#txtLogiAgregar').val();
    let rol = $('#cmbTipoAgregar').val();

    if (codigo === '' || nombres === '' || apPaterno === '' || apMaterno === '') {
        mostrarMensaje('Todos los campos son obligatorios. Por favor, complete todos los campos.', 'error');
        return;
    }

    $.ajax({
        url: 'personalcrud',
        type: 'GET',
        data: {
            tipo: '2',
            codigo: codigo
        },
        dataType: 'json',
        success: function (response) {
            if (response.codiPers && response.codiPers.toString() === codigo) {
                mostrarMensaje('Ya existe un alumno con este código. Ingrese un código único.', 'error');
            } else {
                agregarPersonal(codigo, nombres, apPaterno, apMaterno, peso, fechaNacimiento, logi, rol);
            }
        }
    });
});

function agregarPersonal(codigo, nombres, apPaterno, apMaterno, peso, fechaNacimiento, logi, rol) {
    $.ajax({
        url: 'personalcrud',
        type: 'POST',
        data: {
            tipo: '4',
            codigo: codigo,
            nombres: nombres,
            appa: apPaterno,
            apma: apMaterno,
            peso: peso,
            fecha: fechaNacimiento,
            logi: logi,
            rol: rol
        },
        dataType: 'json',
        success: function (response) {
            if (response.resultado === 'ok') {
                $('#dataTable').DataTable().ajax.reload();
                $('#idAlumnoAgregar').val('');
                $('#txtNombresAgregar').val('');
                $('#txtApPaternoAgregar').val('');
                $('#txtApMaternoAgregar').val('');
                $('#txtPesoAgregar').val('');
                $('#txtLogiAgregar').val('');
            } else {
                alert('error');
            }
        }
    });

    $('#modalAgregar').modal('hide');
}

$("#generarReporte").click(function () {
    let url = "http://localhost:8080/Asistencia/rptasistencia";

    window.open(url);
});
