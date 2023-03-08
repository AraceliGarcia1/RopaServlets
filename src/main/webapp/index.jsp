<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="context" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>

<html>
<head>
    <title>Inicio de sesión ejecutivo</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="${context}/assets/plugins/bootstrap/css/bootstrap.min.css">
</head>
<body>
<!DOCTYPE html>

<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="card">
                <div class="card-header">
                    <h4 class="text-center">Inicio de sesión</h4>
                </div>
                <div class="card-body">

                        <div class="form-group">
                            <label>Correo electrónico:</label>
                            <input type="text" class="form-control" id="usuario" name="usuario">
                        </div>
                        <div class="form-group">
                            <label >Contraseña:</label>
                            <input type="password" class="form-control" id="contrasena" name="contrasena">
                        </div>
                        <div style="margin-top: 10px" align="center">
                        <button onclick="iniciarSesion()" class="btn btn-primary btn-block">Iniciar Sesión</button>
                        </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="${context}/assets/plugins/bootstrap/js/bootstrap.min.js"></script>
<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
<script src="${context}/assets/dist/js/main.js"></script>
</body>

</html>