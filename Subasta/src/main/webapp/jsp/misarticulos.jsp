<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
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
        <title>E-Auctions subastas electrónicas</title>
    </head>
    <body>
        <c:set var="usuario" scope="session" value="${info}"/> 
        <c:set var="articulos" scope="request" value="${articulos}"/>
        <c:set var="now" scope="request" value="${now}"/>
        <div class="container-fluid">
            <nav class="navbar sticky-top  navbar-expand-lg navbar-light bg-light">
                <a class="navbar-brand" href="${pageContext.request.contextPath}">
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
                            <a class="dropdown-item" href="${contexto}/logOut">Cerrar sesión</a>
                        </div>
                    </div>


                </div>
            </nav>
            <hr>
            <div class="row">
                <table class="table">
                    <thead>
                        <tr>
                            <th>ID Articulo</th>
                            <th>Artículo</th>
                            <th>Última puja</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${articulos}" var="a">
                            <tr>
                                <th scope="row"> <c:out value="${a.id} "/></th>
                                <td> <c:out value="${a.descripcionCorta}"/></td>
                                <c:choose>
                                    <c:when test="${a.pujas[0] == null}">
                                        <td><c:out value="${a.importeSalida}€"/></td>
                                    </c:when>
                                    <c:otherwise>
                                        <td><c:out value="${a.pujas[fn:length(a.pujas)-1].valor}€"/></td>
                                    </c:otherwise>
                                </c:choose>
                                <td><a href="${contexto}/articulo?art=${a.id}">Ir a subasta</a></td>
                                <c:if test="${a.fechaFin < now}">
                                    <td><a href="${contexto}/beneficio?art=${a.id}">Calcular beneficio</a></td>
                                </c:if>
                            </tr>

                        </c:forEach>

                    </tbody>
                </table>


            </div>
    </body>
</html>
