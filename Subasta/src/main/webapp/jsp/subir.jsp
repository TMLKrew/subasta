
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
        <script src="<c:out value="${pageContext.request.contextPath}/js/registro.js"/>"></script>
        <script src="<c:out value="${pageContext.request.contextPath}/js/login.js"/>"></script>
        <script src="<c:out value="${pageContext.request.contextPath}/js/articulo.js"/>"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <title>E-Auctions subastas electrónicas</title>
    </head>
    <body>
        <c:set var="now" value="<%=new java.util.Date()%>" />
        <c:set var="usuario" scope="session" value="${info}"/>        
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
            <form action="${contexto}/SubirArticulo" method="post" enctype="multipart/form-data">
                <div class="form-group">
                    <label for="articulo">Artículo</label>
                    <input type="text" name="ARTarticulo" class="form-control" id="articulo" required="">
                </div>
                <div class="form-group">
                    <label for="descripcion">Descripción</label>
                    <textarea name="ARTdescripcion" class="form-control" id="descripcion" required=""></textarea>
                </div>

                <div class="form-group">
                    <label for="importe">Importe inicial</label>
                    <div class="input-group mb-2 mr-sm-2 mb-sm-0">                        
                        <input type="number" class="form-control" name="ARTimporte" id="importe" required="">
                        <div class="input-group-addon">€</div>
                    </div>
                </div>
                <div class="form-group">
                    <label for="fechaInc">Fecha inicio</label>
                    <input type="date" class="form-control fechaInc" id="fechaInc" name="ARTfechaInc" min="<fmt:formatDate pattern ="yyyy-MM-dd" value="${now}"/>" required="">
                </div>
                <div class="form-group">
                    <label for="fechaFin">Fecha fin</label>
                    <input type="date" class="form-control" id="fechaFin" name="ARTfechaFin" disabled="" required="">
                </div>
                <div class="form-group">
                    <label for="categoria">Selecciona la categoria</label>
                    <select name="ARTcategoria" id="categoria" class="categoria form-control">
                        <c:forEach items="${categorias}" var="cat" varStatus="contador">
                            <option value="<c:out value="${contador.count}"/>"><c:out value="${cat.denominacion}"/></option>                             
                        </c:forEach>
                    </select>

                    <c:forEach items="${categorias}" var="cat">
                        <div class="<c:out value="carac ${cat.idCategoria} form-control"/>">
                            <c:forEach items="${cat.caracteristicas}" var="c">                              
                                <label><c:out value="${c.denoninacion}"/></label>
                                <input type="text" class="<c:out value="${c.idCategoria} form-control car"/>" name="<c:out value="${c.idCaracteristica}"/>" required="">
                            </c:forEach>
                        </div>
                    </c:forEach>
                </div>  
                <div class="form-group">
                    <label for="file">Subir imágenes</label>
                    <input type="file" name="ARTfile[]" multiple id="file" class="form-control" required="">
                </div>
                <button type="submit" class="btn btn-danger">Subir artículo</button>
            </form>

        </div>
    </body>
</html>
