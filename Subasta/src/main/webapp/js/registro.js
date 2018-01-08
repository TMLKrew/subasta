$(document).ready(function () {
    $("#email").blur(function () {
        var email = $('#email').val();
        $.ajax({
            type: 'POST',
            url: 'registro',
            data: {email: email, comprobar: "comprobar"},
            success: function (response) {
                if ("false" == response) {
                    $('#emailError').html('<span style="color:red;"></span>');
                    $('.submitBtn').removeAttr("disabled");
                } else {
                    $('#emailError').html('<span style="color:red;">Ese email ya está en uso</span>');
                    $('.submitBtn').attr("disabled", "disabled");

                }
            }
        });
    })
})
function validarRegistroJS() {
    var error = false;
    var email = $('#email').val();
    var password = $('#password').val();
    var nombre = $('#nombre').val();
    var apellido1 = $('#apellido1').val();
    var apellido2 = $('#apellido2').val();
    var nif = $('#nif').val();
    var direccion = $('#direccion').val();
    var telefono = $('#telefono').val();
    if (email.trim() != '') {
        if (/^[-\w.%+]{1,64}@(?:[A-Z0-9-]{1,63}\.){1,125}[A-Z]{2,63}$/i.test(email)) {
            $('#emailError').html('<span style="color:red;"></span>');
        } else {
            $('#emailError').html('<span style="color:red;">Introduzca una dirección de correo válida</span>');
            error = true;
        }
    }
    if (email.trim() == '') {
        $('#emailError').html('<span style="color:red;">Campo requerido.</span>');
        error = true;
    }
    if (password.trim() != '') {
        $('#passwordError').html('<span style="color:red;"></span>');
    }
    if (password.trim() == '') {
        $('#passwordError').html('<span style="color:red;">Introduzca una contraseña.</span>');
        error = true;
    }
    if (nombre.trim() != '') {
        if (/^([A-ZÁÉÍÓÚ]{1}[a-zñáéíóú A-ZÁÉÍÓÚ]{1,19}[\\s]*)+$/.test(nombre)) {
            $('#nombreError').html('<span style="color:red;"></span>');
        } else {
            $('#nombreError').html('<span style="color:red;">Nombre no válido.</span>');
            error = true;
        }
    }
    if (nombre.trim() == '') {
        $('#nombreError').html('<span style="color:red;">Campo requerido.</span>');
        error = true;
    }
    if (apellido1.trim() != '') {
        if (/^([A-ZÁÉÍÓÚ]{1}[a-zñáéíóú A-ZÁÉÍÓÚ]{1,19}[\\s]*)+$/.test(apellido1)) {
            $('#apellido1Error').html('<span style="color:red;"></span>');
        } else {
            $('#apellido1Error').html('<span style="color:red;">Apellido no válido.</span>');
        }
    }
    if (apellido1.trim() == '') {
        $('#apellido1Error').html('<span style="color:red;">Campo requerido.</span>');
        error = true;
    }

    if (apellido2.trim != '') {
        if (/^([A-ZÁÉÍÓÚ]{1}[a-zñáéíóú A-ZÁÉÍÓÚ]{1,19}[\\s]*)+$/.test(apellido2)) {
            $('#apellido2Error').html('<span style="color:red;"></span>');
        } else {
            $('#apellido2Error').html('<span style="color:red;">Apellido no válido.</span>');
            error = true;
        }
    }
    if (apellido2.trim() == '') {
        $('#apellido2Error').html('<span style="color:red;">Campo requerido.</span>');
        error = true;
    }
    if (nif.trim() != '') {
        if (/[0-9]{8}[TRWAGMYFPDXBNJZSQVHLCKEtrwagmyfpdxbnjzsqvhlcke]/.test(nif)) {
            $('#nifError').html('<span style="color:red;"></span>');
        } else {
            $('#nifError').html('<span style="color:red;">Introduzca un NIF válido.</span>');
            error = true;
        }
    }
    if (nif.trim() == '') {
        $('#nifError').html('<span style="color:red;">Campo requerido.</span>');
        error = true;
    }
    if (direccion.trim() != '') {
        if (direccion.length <= 45) {
            $('#direccionError').html('<span style="color:red;"></span>');
        } else {
            $('#direccionError').html('<span style="color:red;">La dirección introducida es demasiado grande.</span>');
            error = true;
        }
    }
    if (direccion.trim() == '') {
        $('#direccionError').html('<span style="color:red;">Campo requerido.</span>');
        error = true;
    }
    if (telefono.trim() != '') {
        if (/^[1-9][0-9]{8}/.test(telefono)) {
            $('#telefonoError').html('<span style="color:red;"></span>');
        } else {
            $('#telefonoError').html('<span style="color:red;">Introduzca un número de teléfono válido.</span>');
            error = true;
        }
    }
    if (telefono.trim() == '') {
        $('#telefonoError').html('<span style="color:red;">Campo requerido.</span>');
        error = true;
    }
    if (!error) {
        $.ajax({
            type: 'POST',
            url: 'registro',
            data: {email: email, password: password, nombre: nombre, apellido1: apellido1,
                apellido2: apellido2, nif: nif, direccion: direccion, telefono: telefono, registrar: "true"},
            beforeSend: function () {
                $('.submitBtn').attr("disabled", "disabled");
                $('.modal-body').css('opacity', '.5');
            },
            success: function (response) {
                if ("false" == response) {
                    $('.statusMsg').html('<span style="color:green;">Se ha completado el registro.</p>');
                } else {
                    $('.statusMsg').html('<span style="color:red;">Ha surgido un problema con el registro.</span>');
                }
                $('.submitBtn').removeAttr("disabled");
                $('.modal-body').css('opacity', '');
            }
        });
    } else {
        return false;
    }
}

function reset() {
    $('#email').text("");
    $('#password').text("");
    $('#nombre').text("");
    $('#apellido1').text("");
    $('#apellido2').text("");
    $('#nif').text("");
    $('#direccion').text("");
    $('#telefono').text("");
}
