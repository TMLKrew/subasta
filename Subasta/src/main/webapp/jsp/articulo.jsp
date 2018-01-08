<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html> 

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link rel="stylesheet" href="<c:out value="${pageContext.request.contextPath}/css/estilo.css"/>">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css" integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb" crossorigin="anonymous">
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js" integrity="sha384-vFJXuSJphROIrBnz7yo7oB41mKfc8JzQZiCq4NCceLEaO4IHwicKwpJf9c9IpFgh" crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js" integrity="sha384-alpBpkh1PFOepccYVYDB4do5UnbKysX5WZXm3XxPqe5iKTfUKjNkCk9SaVuEZflJ" crossorigin="anonymous"></script> 
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <script src="<c:out value="${pageContext.request.contextPath}/js/countdown.js"/>"></script>
        <title>E-Auctions subastas electrónicas</title>
    </head>
    <body>
        <c:set var="usuario" scope="session" value="${info}"/> 
        <c:set var="articulo" scope="request" value="${art}"/>
        <div class="container-fluid">
            <nav class="navbar sticky-top  navbar-expand-lg navbar-light bg-light">
                <a class="navbar-brand col-3" href="${pageContext.request.contextPath}">
                    <img src="${contexto}/images/mazo.jpg" class="mr-4 d-inline-block">
                    <div class="d-inline-block"><h2>E-Auctions</h2></div>
                </a>
                <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo02" aria-controls="navbarTogglerDemo02" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>


                <div class="collapse navbar-collapse" id="navbarTogglerDemo02">
                    <ul class="navbar-nav mr-auto mt-2 mt-md-0">

                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" href="">Categorias</a>
                            <div class="dropdown-menu" aria-labelledby="dropdownMenu1">
                                <c:forEach items="${categorias}" var="cat" >
                                    <a class="dropdown-item" href="${contexto}/Categoria?valor=${cat.idCategoria}"><c:out value="${cat.denominacion}"/></a>                               
                                </c:forEach>
                            </div>

                        </li>
                    </ul>
                    <c:out value="Bienvenido ${info.nombre}"/>
                    <div class="dropdown open ml-5">
                        <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenu4" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            Mi perfil
                        </button>
                        <div class="dropdown-menu">
                            <a class="dropdown-item" href="<c:out value="${contexto}/jsp/profile.jsp"/>">Editar perfil</a>                                    
                            <a class="dropdown-item" href="${contexto}/MisPujas">Mis pujas</a>
                            <a class="dropdown-item" href="${contexto}/MisSubastas">Mis subastas</a>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" href="logOut">Cerrar sesión</a>
                        </div>
                    </div>


                </div>
            </nav>
            <hr>
            <div class="container-fluid">
                <div class="row">
                    <div class="col-12 sol-sm-3">
                        <h4>Subastador</h4>
                        <h5><c:out value="${art.cliente.nombre} ${art.cliente.apellido1} ${art.cliente.apellido2}"/></h5>

                    </div>
                    <div class="col-sm-8 col-12">

                        <h1 class="display-4"><c:out value="${articulo.descripcionCorta}"/></h1>
                        <div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
                            <ol class="carousel-indicators">
                                <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
                                <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
                            </ol>
                            <div class="carousel-inner" role="listbox">
                                <c:forEach items="${articulo.fotografias}" var="foto" varStatus="contador">
                                    <c:choose>
                                        <c:when test="${contador.count == 1}">
                                            <div class="carousel-item active">
                                                <img class="d-block w-100 img-responsive" height="400" src="${contexto}/images/${articulo.cliente.id}/articulos/${foto.fotografia}" alt="900x400" data-holder-rendered="true">
                                            </div>
                                        </c:when>
                                        <c:otherwise>
                                            <div class="carousel-item">
                                                <img class="d-block w-100 img-responsive" height="400" src="${contexto}/images/${articulo.cliente.id}/articulos/${foto.fotografia}" alt="900x400" data-holder-rendered="true" >
                                            </div>
                                        </c:otherwise>
                                    </c:choose>


                                </c:forEach>

                            </div>
                            <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
                                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                                <span class="sr-only">Previous</span>
                            </a>
                            <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
                                <span class="carousel-control-next-icon" aria-hidden="true"></span>
                                <span class="sr-only">Next</span>
                            </a>
                        </div>
                        <div class="mt-1">
                            <ul class="list-group">
                                <li class="list-group-item"><c:out value="Descripción"/></li>
                                <li class="list-group-item"><c:out value="${articulo.descripcion}"/></li>
                            </ul>

                            <c:forEach items="${articulo.caracteristicas}" var="car">
                                <ul class="list-group mt-1">
                                    <c:forEach items="${categorias}" var="cat">
                                        <c:forEach items="${cat.caracteristicas}" var="c">
                                            <c:if test="${c.idCaracteristica == car.idCaracteristica}">
                                                <li class="list-group-item"><c:out value="${c.denoninacion}"/></li>
                                                </c:if>
                                            </c:forEach>
                                        </c:forEach>

                                    <li class="list-group-item"><c:out value="${car.valor}"/></li>
                                </ul>
                            </c:forEach>
                        </div>
                    </div>
                    <div class="col-12 col-sm-3 mt-4">
                        <script>
                            var jsAtt = '${articulo.fechaFin}';
                            var d = new Date(jsAtt);
                            var myCountdown1 = new Countdown({
                                rangeHi: "day",
                                style: "flip",
                                year: d.getFullYear(),
                                month: d.getMonth(),
                                day: d.getDate()});
                        </script>   



                        <div class="mt-4 col-11 " >
                            <div class="form-group row">
                                <label for="actual" class="col-sm-4 col-form-label">Puja actual</label>
                                <div class="col-sm-8">
                                    <c:choose>
                                        <c:when test="${articulo.pujas[0] == null}">
                                            <p class="mt-1"><fmt:formatNumber value = "${articulo.importeSalida}" type = "currency"/></p>
                                        </c:when>
                                        <c:otherwise>
                                            <p class="mt-1"><fmt:formatNumber value = "${articulo.pujas[0].valor}" type = "currency"/></p>
                                        </c:otherwise>
                                    </c:choose>
                                    <c:if test="${articulo.pujas == null}">

                                    </c:if>
                                </div>
                            </div>
                        </div>
                        <c:if test="${articulo.cliente.id != info.id}">
                            <form action="${contexto}/Puja" method="post" class="puja">
                                <div class="form-group row" id="feedback-bg-info">

                                    <label for="puja" class="col-sm-2 col-form-label">
                                        <button type="submit" class="btn btn-danger">Pujar</button>
                                    </label>
                                    <input type="hidden" name="articulo" value="${articulo.id}" class="articulo">
                                    <c:choose>
                                        <c:when test="${articulo.pujas[0] == null}">
                                            <input type="number" class="col-sm-6 ml-3 form-control" name="puja" id="puja" min="<c:out value="${articulo.importeSalida+1}"/>" required="">   
                                        </c:when>
                                        <c:otherwise>
                                            <input type="number" class="col-sm-6 ml-3 form-control" name="puja" id="puja" min="<c:out value="${articulo.pujas[0].valor+1}"/>" required=""/>
                                        </c:otherwise>
                                    </c:choose>

                                </div>
                            </form>

                            <p class="text-center col-sm-6">Puja rápida</p>
                            <div class="form-group row">
                                <form action="${contexto}/Puja" method="post" class="puja">
                                    <c:choose>
                                        <c:when test="${articulo.pujas[0] == null}">
                                            <label for="pujaRapida" class="col-sm-2 col-form-label">
                                                <button type="submit" class="btn btn-danger"><fmt:formatNumber value ="${articulo.importeSalida * 0.01 + articulo.importeSalida}" type = "currency"/>
                                                    <input type="hidden" name="puja" class="form-control" id="pujaRapida" value="<c:out value="${articulo.importeSalida * 0.01 + articulo.importeSalida}"/>">
                                                </button>
                                            </label> 
                                        </c:when>
                                        <c:otherwise>
                                            <label for="pujaRapida" class="col-sm-2 col-form-label">
                                                <button type="submit" class="btn btn-danger"><fmt:formatNumber value ="${articulo.pujas[0].valor * 0.01 + articulo.pujas[0].valor}" type = "currency"/>
                                                    <input type="hidden" name="puja" class="form-control" id="pujaRapida" value="<c:out value="${articulo.pujas[0].valor * 0.01 + articulo.pujas[0].valor}"/>">
                                                </button>
                                            </label>    
                                        </c:otherwise>
                                    </c:choose>
                                    <input type="hidden" name="articulo" value="${articulo.id}" class="articulo">

                                </form>
                                <form action="${contexto}/Puja" method="post" class="puja">
                                    <input type="hidden" name="articulo" value="${articulo.id}" class="articulo">
                                    <c:choose>
                                        <c:when test="${articulo.pujas[0] == null}">
                                            <label for="pujaRapida" class="col-sm-2 col-form-label">
                                                <button type="submit" class="btn btn-danger"><fmt:formatNumber value ="${articulo.importeSalida * 0.05 + articulo.importeSalida}" type = "currency"/>
                                                    <input type="hidden" name="puja" class="form-control" id="pujaRapida" value="<c:out value="${articulo.importeSalida * 0.05 + articulo.importeSalida}"/>">
                                                </button>
                                            </label> 
                                        </c:when>
                                        <c:otherwise>
                                            <label for="pujaRapida" class="col-sm-2 col-form-label">
                                                <button type="submit" class="btn btn-danger"><fmt:formatNumber value ="${articulo.pujas[0].valor * 0.05 + articulo.pujas[0].valor}" type = "currency"/>
                                                    <input type="hidden" name="puja" class="form-control" id="pujaRapida" value="<c:out value="${articulo.pujas[0].valor * 0.05 + articulo.pujas[0].valor}"/>">
                                                </button>
                                            </label>    
                                        </c:otherwise>
                                    </c:choose>
                                </form>
                                <form action="${contexto}/Puja" method="post">
                                    <input type="hidden" name="articulo" value="${articulo.id}" class="articulo">

                                    <c:choose>
                                        <c:when test="${articulo.pujas[0] == null}">
                                            <label for="pujaRapida" class="col-sm-2 col-form-label">
                                                <button type="submit" class="btn btn-danger"><fmt:formatNumber value ="${articulo.importeSalida * 0.1 + articulo.importeSalida}" type = "currency"/>
                                                    <input type="hidden" name="puja" class="form-control" id="pujaRapida" value="<c:out value="${articulo.importeSalida * 0.1 + articulo.importeSalida}"/>">
                                                </button>
                                            </label> 
                                        </c:when>
                                        <c:otherwise>
                                            <label for="pujaRapida" class="col-sm-2 col-form-label">
                                                <button type="submit" class="btn btn-danger"><fmt:formatNumber value ="${articulo.pujas[0].valor * 0.1 + articulo.pujas[0].valor}" type = "currency"/>
                                                    <input type="hidden" name="puja" class="form-control" id="pujaRapida" value="<c:out value="${articulo.pujas[0].valor * 0.1 + articulo.pujas[0].valor}"/>">
                                                </button>
                                            </label>    
                                        </c:otherwise>
                                    </c:choose>
                                </form>
                            </div>

                        </c:if>
                        <div id="feedback-bg-info" class="col-sm-11 col-12">
                            <ul class="list-group">
                                <c:forEach items="${articulo.pujas}" var="p">

                                    <li class="list-group-item d-flex justify-content-between align-items-center">
                                        <c:out value="Pujador: ${p.idCliente}"/>
                                        <br>
                                        <c:out value="Fecha: ${p.fecha}"/>
                                        <span class="badge badge-danger badge-pill">Importe: <fmt:formatNumber value = "${p.valor}" 
                                                          type = "currency"/></span>
                                    </li>                                   


                                </c:forEach>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>

</html>
