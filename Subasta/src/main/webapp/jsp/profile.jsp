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
        <link rel="stylesheet" href="<c:out value="${pageContext.request.contextPath}/css/estilo.css"/>">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css" integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb" crossorigin="anonymous">
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js" integrity="sha384-vFJXuSJphROIrBnz7yo7oB41mKfc8JzQZiCq4NCceLEaO4IHwicKwpJf9c9IpFgh" crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js" integrity="sha384-alpBpkh1PFOepccYVYDB4do5UnbKysX5WZXm3XxPqe5iKTfUKjNkCk9SaVuEZflJ" crossorigin="anonymous"></script> 
        <script src="<c:out value="${pageContext.request.contextPath}/js/registro.js"/>"></script>
        <script src="<c:out value="${pageContext.request.contextPath}/js/login.js"/>"></script>
        <script src="<c:out value="${pageContext.request.contextPath}/js/avatar.js"/>"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <title>E-Auctions subastas electrónicas</title>
    </head>
    <body>
        <c:set var="usuario" scope="session" value="${info}"/>

        <div class="container-fluid">
            <nav class="navbar sticky-top  navbar-expand-lg navbar-light bg-light">
                <a class="navbar-brand col-3" href="${contexto}">
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
            <div class="row">
                <div class="col-3">
                    <label for="avatar">
                        <c:if test="${info.avatar == 'sin.jpg'}">
                            <img class="img-fluid rounded-circle" src="<c:out value="${pageContext.request.contextPath}/images/avatar/sin.jpg"/>"/>
                        </c:if>
                        <c:if test="${info.avatar != 'sin.jpg'}">
                            <img class="img-fluid rounded-circle" src="<c:out value="${pageContext.request.contextPath}/images/${info.id}/avatar/${info.avatar}"/>" title="Si la imagen no carga, recargue la página"/>
                        </c:if>
                    </label> 
                    <form action="${contexto}/subirAvatar" enctype="multipart/form-data" method="post" id="avatarF">
                        <input type="file" name="avatar" id="avatar" accept="image/x-png,image/gif,image/jpeg">                     
                    </form>
                </div>   
                <div class="col-sm-5 col-9">     
                    <div class="form-group">
                        <p>Nombre y apellidos</p>
                        <p class="form-control" id="formGroupExampleInput"><c:out value="${info.nombre} ${info.apellido1} ${info.apellido2}"/></p>
                    </div>
                    <div class="form-group">
                        <p>NIF</p>
                        <p class="form-control"><c:out value="${info.nif}"/></p>
                    </div>
                    <div class="form-group">
                        <p>Dirección</p>
                        <p class="form-control"><c:out value="${info.direccion}"/></p>
                    </div>
                    <div class="form-group">
                        <p>Teléfono</p>
                        <p class="form-control"><c:out value="${info.telefono}"/></p>
                    </div>    
                    <!-- Button trigger modal -->
                    <button type="button" class="btn btn-danger col-12" data-toggle="modal" data-target="#exampleModal3">
                        Editar perfil
                    </button>
                    
                    <button type="button" class="btn btn-danger col-12 mt-2" data-toggle="modal" data-target="#exampleModal4">
                        Cambiar contraseña
                    </button>

                    <!-- Modal -->
                    <div class="modal fade" id="exampleModal3" tabindex="-1" role="dialog" aria-labelledby="exampleModal3Label" aria-hidden="true">
                        <div class="modal-dialog" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="exampleModal3Label">Editar perfil</h5>
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                        <span aria-hidden="true">×</span>
                                    </button>
                                </div>
                                <form action="${contexto}/editarPerfil" method="post">
                                    <input type="hidden" name="email" value="${info.email}"/>
                                    <div class="modal-body">
                                        <div class="input-group input-group-lg">
                                            <span class="input-group-addon" id="sizing-addon1">Nombre</span>
                                            <input type="text" class="form-control" name="nombre" value="${info.nombre}" aria-describedby="sizing-addon1">
                                        </div>
                                        <div class="input-group input-group-lg">
                                            <span class="input-group-addon" id="sizing-addon1">1º Apellido</span>
                                            <input type="text" class="form-control" name="apellido1" value="${info.apellido1}" aria-describedby="sizing-addon1">
                                        </div>

                                        <div class="input-group input-group-lg">
                                            <span class="input-group-addon" id="sizing-addon1">2º Apellido</span>
                                            <input type="text" class="form-control" name="apellido2" value="${info.apellido2}" aria-describedby="sizing-addon1">
                                        </div>
                                        <div class="input-group input-group-lg">
                                            <span class="input-group-addon" id="sizing-addon1">NIF</span>
                                            <input type="text" class="form-control" name="nif" value="${info.nif}" aria-describedby="sizing-addon1">
                                        </div>
                                        <div class="input-group input-group-lg">
                                            <span class="input-group-addon" id="sizing-addon1">Direccion</span>
                                            <input type="text" class="form-control" name="direccion" value="${info.direccion}" aria-describedby="sizing-addon1">
                                        </div>
                                        <div class="input-group input-group-lg">
                                            <span class="input-group-addon" id="sizing-addon1">Teléfono</span>
                                            <input type="text" class="form-control" name="telefono" value="${info.telefono}" aria-describedby="sizing-addon1">
                                        </div>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
                                        <button type="submit" class="btn btn-danger">Cambiar datos</button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                    <!-- Modal -->
                    <div class="modal fade" id="exampleModal4" tabindex="-1" role="dialog" aria-labelledby="exampleModal3Label" aria-hidden="true">
                        <div class="modal-dialog" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="exampleModal3Label">Cambiar contraseña</h5>
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                        <span aria-hidden="true">×</span>
                                    </button>
                                </div>
                                <form action="${contexto}/EditarPassword" method="post">
                                    <input type="hidden" name="email" value="${info.email}"/>
                                    <div class="modal-body">
                                        <div class="input-group input-group-lg">
                                            <span class="input-group-addon" id="sizing-addon1">Contraseña actual</span>
                                            <input type="password" class="form-control" name="password" placeholder="****" aria-describedby="sizing-addon1">
                                        </div>
                                        <div class="input-group input-group-lg">
                                            <span class="input-group-addon" id="sizing-addon1">Contraseña nueva</span>
                                            <input type="password" class="form-control" name="password1" aria-describedby="sizing-addon1">
                                        </div>

                                        <div class="input-group input-group-lg">
                                            <span class="input-group-addon" id="sizing-addon1">Repita la contraseña</span>
                                            <input type="password" class="form-control" name="password2" aria-describedby="sizing-addon1">
                                        </div>                                       
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
                                            <button type="submit" class="btn btn-danger">Cambiar datos</button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
    </body>
</html>
