<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html> 
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link rel="stylesheet" href="${contexto}/css/estilo.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css" integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb" crossorigin="anonymous">
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js" integrity="sha384-vFJXuSJphROIrBnz7yo7oB41mKfc8JzQZiCq4NCceLEaO4IHwicKwpJf9c9IpFgh" crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js" integrity="sha384-alpBpkh1PFOepccYVYDB4do5UnbKysX5WZXm3XxPqe5iKTfUKjNkCk9SaVuEZflJ" crossorigin="anonymous"></script> 
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <title>E-Auctions subastas electrónicas</title>
    </head>
    <body>
        <c:set var="usuarios" scope="session" value="${usuarios}"/>
        <c:set var="contexto" scope="session" value="${pageContext.request.contextPath}"/>
        <div class="container-fluid">
            <nav class="navbar sticky-top  navbar-expand-lg navbar-light bg-light">
                <a class="navbar-brand col-sm-9 col-2" href="${contexto}/jsp/admin.jsp">
                    <img src="<c:out value="${contexto}/images/mazo.jpg"/>" class="mr-4 d-inline-block">
                    <div class="d-inline-block"><h2>E-Auctions panel de administración</h2></div>
                </a>
                <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo02" aria-controls="navbarTogglerDemo02" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarTogglerDemo02">
                    <c:out value="Bienvenido ${info.nombre}"/>
                    <div class="dropdown open ml-4">
                        <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenu4" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            Panel de control
                        </button>
                        <div class="dropdown-menu">                                                                    
                            <a class="dropdown-item" href="#">Ver usuarios</a>
                            <a class="dropdown-item" href="#">Ver categorias</a>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" href="${contexto}/logOut">Cerrar sesión</a>
                        </div>
                    </div>
                </div>
            </nav>
            <hr>
            <div class="d-flex justify-content-center container">
                <table class="table">
                    <thead class="thead-light">
                        <tr>
                            <th>#Id</th>
                            <th>Nombre</th>
                            <th>Apellidos</th>
                            <th>Email</th>
                            <th>Acceso</th>
                        </tr>
                    </thead>



                    <c:forEach items="${usuarios}" var="user">
                        <tbody>
                            <tr>
                                <th scope="row">${user.id}</th>
                                <td>${user.nombre}</td>
                                <td>${user.apellido1} ${user.apellido2}</td>
                                <td>${user.email}</td>
                                <c:choose>
                                    <c:when test="${user.bloqueado == 'n'}">
                                        <td><a href="${contexto}/BloquearUsuario?id=${user.id}&acceso=${user.bloqueado}"><button type="button" class="btn btn-danger">Permitido</button></a></td>
                                    </c:when>
                                    <c:otherwise>                                        
                                        <td><a href="${contexto}/BloquearUsuario?id=${user.id}&acceso=${user.bloqueado}"><button type="button" class="btn btn-danger">Bloqueado</button></a></td>
                                    </c:otherwise>
                                </c:choose>
                            </tr>
                        </tbody>
                    </c:forEach>
                </table>
            </div> 

        </div>
    </body>
</html>
