function loginJS() {
    var error = false;
    var emailLogin = $("#emailLogin").val();
    var passLogin = $("#passLogin").val();
    if (emailLogin.trim() != '') {
        if (/^[-\w.%+]{1,64}@(?:[A-Z0-9-]{1,63}\.){1,125}[A-Z]{2,63}$/i.test(emailLogin)) {
            $('#loginError').html('<span style="color:red;"></span>');
        } else {
            $('#loginError').html('<span style="color:red;">Introduzca una dirección de correo válida</span>');
            error = true;
        }
    } else {
        error = true;
    }
    if (!error) {
        $.ajax({
            type: 'POST',
            url: 'registro',
            data: {emailLogin: emailLogin, passLogin: passLogin, login: "true"},
            beforeSend: function () {
                $('.submitBtn').attr("disabled", "disabled");
                $('.modal-body').css('opacity', '.5');
            },
            success: function (response) {
                if ("index" === response) {
                    $('#loginError').html('<span style="color:green;">Se ha completado el registro.</p>');
                    window.location.replace("../Subasta");
                } else
                if ("admin" === response) {
                    window.location.replace("../Subasta/jsp/admin.jsp");
                } else {
                    $('#loginError').html('<span style="color:red;">Contraseña incorrecta.</span>');
                }
                $('.submitBtn').removeAttr("disabled");
                $('.modal-body').css('opacity', '');
            }
        });
    }
}
