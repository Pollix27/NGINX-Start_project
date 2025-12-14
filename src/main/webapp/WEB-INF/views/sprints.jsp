<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Gestión de Sprints</title>
    <style>
        * { margin: 0; padding: 0; box-sizing: border-box; }
        body { font-family: Arial, sans-serif; background: #f5f5f5; padding: 20px; }
        .container { max-width: 1600px; margin: 0 auto; }
        h1 { color: #333; margin-bottom: 30px; }
        .btn-home { display: inline-block; background: #667eea; color: white; padding: 10px 20px; text-decoration: none; border-radius: 5px; margin-bottom: 20px; }
        .btn-home:hover { background: #764ba2; }
        .content-wrapper { display: flex; gap: 20px; }
        .form-container { background: white; padding: 30px; border-radius: 10px; box-shadow: 0 2px 10px rgba(0,0,0,0.1); flex: 0 0 400px; }
        .form-group { margin-bottom: 15px; }
        .form-group label { display: block; margin-bottom: 5px; color: #333; font-weight: bold; }
        .form-group input, .form-group select { width: 100%; padding: 10px; border: 1px solid #ddd; border-radius: 5px; font-size: 14px; }
        .btn-submit { background: #28a745; color: white; padding: 12px 30px; border: none; border-radius: 5px; cursor: pointer; font-size: 16px; font-weight: bold; width: 100%; }
        .btn-submit:hover { background: #218838; }
        .btn-cancel { background: #6c757d; color: white; padding: 12px 30px; border: none; border-radius: 5px; cursor: pointer; font-size: 16px; font-weight: bold; width: 100%; margin-top: 10px; }
        .btn-cancel:hover { background: #5a6268; }
        .table-container { background: white; padding: 30px; border-radius: 10px; box-shadow: 0 2px 10px rgba(0,0,0,0.1); flex: 1; overflow-x: auto; }
        table { width: 100%; border-collapse: collapse; }
        th { background: #667eea; color: white; padding: 12px; text-align: left; white-space: nowrap; }
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
        <h1>🏃 Gestión de Sprints</h1>

        <c:if test="${not empty mensaje}">
            <div class="mensaje exito">${mensaje}</div>
        </c:if>
        <c:if test="${not empty error}">
            <div class="mensaje error">${error}</div>
        </c:if>

        <div class="content-wrapper">
            <div class="form-container">
                <h2>${sprint != null ? 'Editar Sprint' : 'Agregar Nuevo Sprint'}</h2>
                <form action="/sprints/guardar" method="post">
                    <c:if test="${sprint != null}">
                        <input type="hidden" name="idSprint" value="${sprint.idSprint}">
                    </c:if>
                    <div class="form-group">
                        <label>Proyecto: *</label>
                        <select name="proyecto.idProyecto" required>
                            <option value="">Seleccione un proyecto</option>
                            <c:forEach var="p" items="${proyectos}">
                                <option value="${p.idProyecto}" ${sprint != null && sprint.proyecto.idProyecto == p.idProyecto ? 'selected' : ''}>
                                    ${p.nombreProyecto}
                                </option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label>Nombre del Sprint:</label>
                        <input type="text" name="nombreSprint" value="${sprint.nombreSprint}" maxlength="100">
                    </div>
                    <div class="form-group">
                        <label>Estado:</label>
                        <select name="estadoSprint">
                            <option value="">Seleccione</option>
                            <option value="Planificado" ${sprint != null && sprint.estadoSprint == 'Planificado' ? 'selected' : ''}>Planificado</option>
                            <option value="En Progreso" ${sprint != null && sprint.estadoSprint == 'En Progreso' ? 'selected' : ''}>En Progreso</option>
                            <option value="Completado" ${sprint != null && sprint.estadoSprint == 'Completado' ? 'selected' : ''}>Completado</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label>Fecha de Inicio:</label>
                        <input type="date" name="fechaInicioSprint" value="${sprint.fechaInicioSprint}">
                    </div>
                    <div class="form-group">
                        <label>Fecha de Fin:</label>
                        <input type="date" name="fechaFinSprint" value="${sprint.fechaFinSprint}">
                    </div>
                    <button type="submit" class="btn-submit">${sprint != null ? 'Actualizar' : 'Guardar'}</button>
                    <c:if test="${sprint != null}">
                        <a href="/sprints"><button type="button" class="btn-cancel">Cancelar</button></a>
                    </c:if>
                </form>
            </div>

            <div class="table-container">
                <h2>Lista de Sprints</h2>
                <table>
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Proyecto</th>
                            <th>Nombre</th>
                            <th>Estado</th>
                            <th>Fecha Inicio</th>
                            <th>Fecha Fin</th>
                            <th>Acciones</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="s" items="${sprints}">
                            <tr>
                                <td>${s.idSprint}</td>
                                <td>${s.proyecto.nombreProyecto}</td>
                                <td>${s.nombreSprint}</td>
                                <td>${s.estadoSprint}</td>
                                <td>${s.fechaInicioSprint}</td>
                                <td>${s.fechaFinSprint}</td>
                                <td>
                                    <a href="/sprints/editar/${s.idSprint}"><button class="btn-edit">Editar</button></a>
                                    <form action="/sprints/eliminar/${s.idSprint}" method="post" style="display:inline;" onsubmit="return confirm('¿Estás seguro de eliminar este sprint?');">
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
