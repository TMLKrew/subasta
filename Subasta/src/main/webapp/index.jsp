<%-- 
    Document   : index
    Created on : 01-dic-2017, 16:33:59
    Author     : Simon
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html> 

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link rel="stylesheet" href="css/estilo.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css" integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb" crossorigin="anonymous">
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js" integrity="sha384-vFJXuSJphROIrBnz7yo7oB41mKfc8JzQZiCq4NCceLEaO4IHwicKwpJf9c9IpFgh" crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js" integrity="sha384-alpBpkh1PFOepccYVYDB4do5UnbKysX5WZXm3XxPqe5iKTfUKjNkCk9SaVuEZflJ" crossorigin="anonymous"></script> 
        <script src="js/registro.js"></script>
        <script src="js/login.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <title>E-Auctions subastas electrónicas</title>
    </head>
    <body>
        <c:set var="usuario" scope="session" value="${info}"/>
        <c:set var="contexto" scope="session" value="${pageContext.request.contextPath}"/>
        <div class="container-fluid">
            <nav class="navbar sticky-top  navbar-expand-lg navbar-light bg-light">
                <a class="navbar-brand col-3" href="${contexto}">
                    <img src="<c:out value="${contexto}/images/mazo.jpg"/>" class="mr-4 d-inline-block">
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
                                    <c:choose>
                                        <c:when test="${info != null}">
                                            <a class="dropdown-item" href="${contexto}/Categoria?valor=${cat.idCategoria}"><c:out value="${cat.denominacion}"/></a>                               

                                        </c:when>
                                        <c:otherwise>
                                            <a class="dropdown-item disabled"><c:out value="${cat.denominacion}"/></a>                              

                                        </c:otherwise>
                                    </c:choose> 
                                </c:forEach>
                            </div>

                        </li>
                    </ul>
                    <!-- Button trigger modal -->
                    <c:choose>
                        <c:when test="${info != null}">

                            <c:out value="Bienvenido ${info.nombre}"/>
                            <a href="<c:out value="${contexto}/jsp/subir.jsp"/>" class="btn btn-outline-dark ml-4">Subir artículo</a>
                            <div class="dropdown open ml-4">
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

                        </c:when>
                        <c:otherwise>
                            <button type="button" class="btn btn-danger" data-toggle="modal" data-target="#login">
                                Iniciar sesión
                            </button>  
                        </c:otherwise>
                    </c:choose>

                </div>
            </nav>
            <hr>

            <div id="carouselExampleSlidesOnly" class="carousel slide" data-ride="carousel">
                <div class="carousel-inner d-flex justify-content-center">
                    <div class="carousel-item active">
                        <img class="d-block w-100" src="images/slider1.jpg" alt="First slide">
                    </div>
                    <div class="carousel-item">
                        <img class="d-block w-100" src="images/slider2.jpg" alt="Second slide">
                    </div>
                    <div class="carousel-item">
                        <img class="d-block w-100" src="images/slider3.jpg" alt="Third slide">
                    </div>
                </div>

            </div>

        </div>
        <!-- Modal -->
        <div class="modal fade" id="login" tabindex="-1" role="dialog" aria-labelledby="loginLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <form action="registro" method="post" onsubmit="return false">
                        <div class="modal-header">
                            <h5 class="modal-title" id="loginLabel">Logueate</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <div class="container">
                                <div id="loginError"></div>
                                <div class="form-group row">
                                    <label for="emailLogin" class="col-sm-2 col-form-label">Email</label>
                                    <div class="col-sm-10">
                                        <input type="email" class="form-control" id="emailLogin" name="emailLogin" placeholder="Email">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="passLogin" class="col-sm-2 col-form-label">Password</label>
                                    <div class="col-sm-10">
                                        <input type="password" class="form-control" id="passLogin" name="passLogin" placeholder="Password">
                                    </div>
                                </div>

                            </div>
                            <div class="d-flex justify-content-center">
                                <p> ¿No estás registrado? </p>
                            </div>
                            <div class="d-flex justify-content-center">
                                <p> Registrate <a href="" data-toggle="modal" data-target="#registro">aquí</a></p>

                            </div>

                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
                            <button type="submit" name="login" class="btn btn-danger" onclick="loginJS()">Iniciar sesión</button>
                        </div>
                    </form>
                </div>

            </div>
        </div>   
        <!-- Modal -->
        <div class="modal fade" id="registro" tabindex="-1" role="dialog" aria-labelledby="registroLabel" aria-hidden="true">
            <div class="modal-dialog modal-lg" role="document">

                <div class="modal-content">
                    <form>
                        <div class="modal-header">
                            <h5 class="modal-title" id="registroLabel">Registro</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">×</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <div class="container">
                                <p class="statusMsg"></p>
                                <p id="emailError"></p>
                                <div class="form-group row">
                                    <label for="email" class="col-sm-2 col-form-label">Email</label>
                                    <div class="col-sm-10">
                                        <input type="email" class="form-control" id="email" name="email" placeholder="Email">
                                    </div>
                                </div>
                                <p id="passwordError"></p>
                                <div class="form-group row">
                                    <label for="password" class="col-sm-2 col-form-label">Password</label>
                                    <div class="col-sm-10">
                                        <input type="password" class="form-control" id="password" name="password" placeholder="Password">
                                    </div>
                                </div>
                                <p id="nombreError"></p>
                                <div class="form-group row">
                                    <label for="nombre" class="col-sm-2 col-form-label">Nombre</label>
                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" id="nombre" name="nombre" placeholder="Nombre">
                                    </div>
                                </div>
                                <p id="apellido1Error"></p>
                                <div class="form-group row">
                                    <label for="apellido1" class="col-sm-2 col-form-label">Primer apellido</label>
                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" id="apellido1" name="apellido1" placeholder="Primer apellido">
                                    </div>
                                </div>
                                <p id="apellido2Error"></p>
                                <div class="form-group row">
                                    <label for="apellido2" class="col-sm-2 col-form-label">Segundo apellido</label>
                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" id="apellido2" name="apellido2" placeholder="Segundo apellido">
                                    </div>
                                </div>
                                <p id="nifError"></p>
                                <div class="form-group row">
                                    <label for="nif" class="col-sm-2 col-form-label">NIF</label>
                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" id="nif" name="nif" placeholder="12345678A">
                                    </div>
                                </div>
                                <p id="direccionError"></p>
                                <div class="form-group row">
                                    <label for="direccion" class="col-sm-2 col-form-label">Dirección</label>
                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" id="direccion" name="direccion" placeholder="Dirección">
                                    </div>
                                </div>
                                <p id="telefonoError"></p>
                                <div class="form-group row">
                                    <label for="telefono" class="col-sm-2 col-form-label">Teléfono</label>
                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" id="telefono" name="telefono" placeholder="Teléfono">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal" onclick="reset()">Salir</button>
                            <button type="button" name="registrar" class="btn btn-danger submitBtn" onclick="validarRegistroJS()">Registrar</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>

    </body>

</html>
