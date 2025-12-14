<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Gestión de Clientes</title>
    <style>
        * { margin: 0; padding: 0; box-sizing: border-box; }
        body { font-family: Arial, sans-serif; background: #f5f5f5; padding: 20px; }
        .container { max-width: 1400px; margin: 0 auto; }
        h1 { color: #333; margin-bottom: 30px; }
        .btn-home { display: inline-block; background: #667eea; color: white; padding: 10px 20px; text-decoration: none; border-radius: 5px; margin-bottom: 20px; }
        .btn-home:hover { background: #764ba2; }
        .content-wrapper { display: flex; gap: 20px; }
        .form-container { background: white; padding: 30px; border-radius: 10px; box-shadow: 0 2px 10px rgba(0,0,0,0.1); flex: 0 0 350px; }
        .form-group { margin-bottom: 15px; }
        .form-group label { display: block; margin-bottom: 5px; color: #333; font-weight: bold; }
        .form-group input { width: 100%; padding: 10px; border: 1px solid #ddd; border-radius: 5px; font-size: 14px; }
        .btn-submit { background: #28a745; color: white; padding: 12px 30px; border: none; border-radius: 5px; cursor: pointer; font-size: 16px; font-weight: bold; width: 100%; }
        .btn-submit:hover { background: #218838; }
        .btn-cancel { background: #6c757d; color: white; padding: 12px 30px; border: none; border-radius: 5px; cursor: pointer; font-size: 16px; font-weight: bold; width: 100%; margin-top: 10px; }
        .btn-cancel:hover { background: #5a6268; }
        .table-container { background: white; padding: 30px; border-radius: 10px; box-shadow: 0 2px 10px rgba(0,0,0,0.1); flex: 1; }
        table { width: 100%; border-collapse: collapse; }
        th { background: #667eea; color: white; padding: 12px; text-align: left; }
        td { padding: 12px; border-bottom: 1px solid #ddd; }
        tr:hover { background: #f9f9f9; }
        .btn-edit { background: #ffc107; color: #333; padding: 5px 15px; border: none; border-radius: 5px; cursor: pointer; margin-right: 5px; }
        .btn-edit:hover { background: #e0a800; }
        .btn-delete { background: #dc3545; color: white; padding: 5px 15px; border: none; border-radius: 5px; cursor: pointer; }
        .btn-delete:hover { background: #c82333; }
        .mensaje { padding: 15px; margin-bottom: 20px; border-radius: 5px; }
        .mensaje.exito { background: #d4edda; color: #155724; border: 1px solid #c3e6cb; }
        .mensaje.error { background: #f8d7da; color: #721c24; border: 1px solid #f5c6cb; }
    </style>
</head>
<body>
    <div class="container">
        <a href="/" class="btn-home">← Volver al Inicio</a>
        <h1>📋 Gestión de Clientes</h1>

        <c:if test="${not empty mensaje}">
            <div class="mensaje exito">${mensaje}</div>
        </c:if>
        <c:if test="${not empty error}">
            <div class="mensaje error">${error}</div>
        </c:if>

        <div class="content-wrapper">
            <div class="form-container">
                <h2>${cliente != null ? 'Editar Cliente' : 'Agregar Nuevo Cliente'}</h2>
                <form action="/clientes/guardar" method="post">
                    <c:if test="${cliente != null}">
                        <input type="hidden" name="idCliente" value="${cliente.idCliente}">
                    </c:if>
                    <div class="form-group">
                        <label>Empresa:</label>
                        <input type="text" name="empresaCliente" value="${cliente.empresaCliente}" required maxlength="150">
                    </div>
                    <div class="form-group">
                        <label>RFC:</label>
                        <input type="text" name="rfcCliente" value="${cliente.rfcCliente}" required maxlength="13">
                    </div>
                    <div class="form-group">
                        <label>Email:</label>
                        <input type="email" name="emailCliente" value="${cliente.emailCliente}" required maxlength="100">
                    </div>
                    <div class="form-group">
                        <label>Teléfono:</label>
                        <input type="text" name="telefonoCliente" value="${cliente.telefonoCliente}" maxlength="15">
                    </div>
                    <button type="submit" class="btn-submit">${cliente != null ? 'Actualizar' : 'Guardar'}</button>
                    <c:if test="${cliente != null}">
                        <a href="/clientes"><button type="button" class="btn-cancel">Cancelar</button></a>
                    </c:if>
                </form>
            </div>

            <div class="table-container">
                <h2>Lista de Clientes</h2>
                <table>
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Empresa</th>
                            <th>RFC</th>
                            <th>Email</th>
                            <th>Teléfono</th>
                            <th>Acciones</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="c" items="${clientes}">
                            <tr>
                                <td>${c.idCliente}</td>
                                <td>${c.empresaCliente}</td>
                                <td>${c.rfcCliente}</td>
                                <td>${c.emailCliente}</td>
                                <td>${c.telefonoCliente}</td>
                                <td>
                                    <a href="/clientes/editar/${c.idCliente}"><button class="btn-edit">Editar</button></a>
                                    <form action="/clientes/eliminar/${c.idCliente}" method="post" style="display:inline;" onsubmit="return confirm('¿Estás seguro de eliminar este cliente?');">
                                        <button type="submit" class="btn-delete">Eliminar</button>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</body>
</html>
