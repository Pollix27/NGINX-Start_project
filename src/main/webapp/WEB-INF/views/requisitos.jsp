<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Gestión de Requisitos</title>
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
        .form-group input, .form-group select, .form-group textarea { width: 100%; padding: 10px; border: 1px solid #ddd; border-radius: 5px; font-size: 14px; }
        .form-group textarea { resize: vertical; min-height: 80px; }
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
        <h1>📝 Gestión de Requisitos</h1>

        <c:if test="${not empty mensaje}">
            <div class="mensaje exito">${mensaje}</div>
        </c:if>
        <c:if test="${not empty error}">
            <div class="mensaje error">${error}</div>
        </c:if>

        <div class="content-wrapper">
            <div class="form-container">
                <h2>${requisito != null ? 'Editar Requisito' : 'Agregar Nuevo Requisito'}</h2>
                <form action="/requisitos/guardar" method="post">
                    <c:if test="${requisito != null}">
                        <input type="hidden" name="idRequisitos" value="${requisito.idRequisitos}">
                    </c:if>
                    <div class="form-group">
                        <label>Proyecto: *</label>
                        <select name="proyecto.idProyecto" required>
                            <option value="">Seleccione un proyecto</option>
                            <c:forEach var="p" items="${proyectos}">
                                <option value="${p.idProyecto}" ${requisito != null && requisito.proyecto.idProyecto == p.idProyecto ? 'selected' : ''}>
                                    ${p.nombreProyecto}
                                </option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label>Sprint (Opcional):</label>
                        <select name="sprint.idSprint">
                            <option value="">Sin asignar</option>
                            <c:forEach var="s" items="${sprints}">
                                <option value="${s.idSprint}" ${requisito != null && requisito.sprint != null && requisito.sprint.idSprint == s.idSprint ? 'selected' : ''}>
                                    ${s.nombreSprint} - ${s.proyecto.nombreProyecto}
                                </option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label>Descripción:</label>
                        <textarea name="descripcionRequisitos">${requisito.descripcionRequisitos}</textarea>
                    </div>
                    <div class="form-group">
                        <label>Prioridad:</label>
                        <select name="prioridadRequisitos">
                            <option value="">Seleccione</option>
                            <option value="Baja" ${requisito != null && requisito.prioridadRequisitos == 'Baja' ? 'selected' : ''}>Baja</option>
                            <option value="Media" ${requisito != null && requisito.prioridadRequisitos == 'Media' ? 'selected' : ''}>Media</option>
                            <option value="Alta" ${requisito != null && requisito.prioridadRequisitos == 'Alta' ? 'selected' : ''}>Alta</option>
                        </select>
                    </div>
                    <button type="submit" class="btn-submit">${requisito != null ? 'Actualizar' : 'Guardar'}</button>
                    <c:if test="${requisito != null}">
                        <a href="/requisitos"><button type="button" class="btn-cancel">Cancelar</button></a>
                    </c:if>
                </form>
            </div>

            <div class="table-container">
                <h2>Lista de Requisitos</h2>
                <table>
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Proyecto</th>
                            <th>Sprint</th>
                            <th>Descripción</th>
                            <th>Prioridad</th>
                            <th>Acciones</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="r" items="${requisitos}">
                            <tr>
                                <td>${r.idRequisitos}</td>
                                <td>${r.proyecto.nombreProyecto}</td>
                                <td>${r.sprint != null ? r.sprint.nombreSprint : 'Sin asignar'}</td>
                                <td>${r.descripcionRequisitos}</td>
                                <td>${r.prioridadRequisitos}</td>
                                <td>
                                    <a href="/requisitos/editar/${r.idRequisitos}"><button class="btn-edit">Editar</button></a>
                                    <form action="/requisitos/eliminar/${r.idRequisitos}" method="post" style="display:inline;" onsubmit="return confirm('¿Estás seguro de eliminar este requisito?');">
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
