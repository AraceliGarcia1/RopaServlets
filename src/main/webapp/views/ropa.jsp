<%--
  Created by IntelliJ IDEA.
  User: Araceli
  Date: 04/03/2023
  Time: 06:10 p. m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="context" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
    <title>Ropa</title>
    <meta charset="utf-8">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

</head>
<body>
<nav class="navbar navbar-light" style="background-color: #e3f2fd;">
    <div class="container-fluid">
        <a class="navbar-brand">ROPA</a>
        <div class="d-flex">
            <button class="btn btn-outline-danger" type="submit">Logout</button>
        </div>
    </div>
</nav>
<div class="container mt-5">
    <div class="d-flex" style="margin-right: 10px; margin-bottom: 20px; justify-content: right; align-items: flex-end;">
        <button class="btn btn-success"data-bs-toggle="modal" data-bs-target="#formModalR">Agregar +</button>
    </div>
    <div class="row justify-content-center">
        <table class="table table-striped table-hover">
            <thead>
            <tr>
                <th scope="col">Nombre</th>
                <th scope="col">Marca</th>
                <th scope="col">Talla</th>
                <th scope="col">Color</th>
                <th scope="col">Descuento</th>
                <th scope="col">Costo</th>
                <th scope="col">Stock</th>
                <th scope="col">Status</th>
                <th scope="col">Acciones</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td>
                    <button class="btn btn-warning" style="margin-right:10px ;" data-bs-toggle="modal" data-bs-target="#formModal">Modificar</button>
                    <button class="btn btn-danger"  style="margin-left: 10px;" type="submit">Eliminar</button>
                </td>
            </tr>
            </tbody>
        </table>
    </div>


    <!-- Modal -->
    <div class="modal fade" id="formModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel1">Modifica la Prenda</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <label>Nombre</label>
                    <input type="text" id="nombre1" name="nombre" style="margin-bottom:10px;">
                    <br>
                    <label>Marca</label>
                    <input type="text" id="marca1" name="marca" style="margin-bottom:10px;">
                    <br>
                    <label>Talla</label>
                    <input type="text" id="talla1" name="talla" style="margin-bottom:10px;">
                    <br>
                    <label>Color</label>
                    <input type="text" id="color1" name="color" style="margin-bottom:10px;">
                    <br>
                    <label>Descuento</label>
                    <input type="text" id="desc1" name="desc" style="margin-bottom:10px;">
                    <br>
                    <label>Costo</label>
                    <input type="number" id="costo1" name="costo" style="margin-bottom:10px;">
                    <br>
                    <label>Stock</label>
                    <input type="number" id="stock1" name="stock" style="margin-bottom:10px;">
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
                    <button type="button" class="btn btn-primary">Guardar</button>
                </div>
            </div>
        </div>
    </div>

    <!-- Modal -->
    <div class="modal fade" id="formModalR" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Registra una Prenda</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <label>Nombre</label>
                    <input type="text" id="nombre" name="nombre" style="margin-bottom:10px;">
                    <br>
                    <label>Marca</label>
                    <input type="text" id="marca" name="marca" style="margin-bottom:10px;">
                    <br>
                    <label>Talla</label>
                    <input type="text" id="talla" name="talla" style="margin-bottom:10px;">
                    <br>
                    <label>Color</label>
                    <input type="text" id="color" name="color" style="margin-bottom:10px;">
                    <br>
                    <label>Descuento</label>
                    <input type="text" id="desc" name="desc" style="margin-bottom:10px;">
                    <br>
                    <label>Costo</label>
                    <input type="number" id="costo" name="costo" style="margin-bottom:10px;">
                    <br>
                    <label>Stock</label>
                    <input type="number" id="stock" name="stock" style="margin-bottom:10px;">
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                    <button type="button" class="btn btn-primary">Guardar</button>
                </div>
            </div>
        </div>
    </div>

</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>

<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>

</body>
</html>
