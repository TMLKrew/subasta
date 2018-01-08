$(document).ready(function () {
    $(".carac").css("display", "none");
    $(".1").css("display", "block");
    $(".car").attr("disabled", "disabled");
    $(".1").removeAttr("disabled");


    $(".categoria").change(function () {
        $(".car").attr("disabled", "disabled");
        $(".1").attr("disabled", "disabled");
        $(".carac").hide();
        var clase = ".";
        clase += $(".categoria").val();
        $(clase).fadeIn("slow");
        $(clase).removeAttr("disabled");
    })

    $(".fechaInc").change(function () {
        var valor = $(".fechaInc").val();
        $("#fechaFin").attr("min", valor);
        $("#fechaFin").removeAttr("disabled");
    })

    $("#fechaFin").change(function () {
        var valor = $("#fechaFin").val();
        $(".fechaInc").attr("max", valor);

    })

})