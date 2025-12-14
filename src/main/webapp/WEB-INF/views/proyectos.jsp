<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Gestión de Proyectos</title>
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
        <h1>📁 Gestión de Proyectos</h1>

        <c:if test="${not empty mensaje}">
            <div class="mensaje exito">${mensaje}</div>
        </c:if>
        <c:if test="${not empty error}">
            <div class="mensaje error">${error}</div>
        </c:if>

        <div class="content-wrapper">
            <div class="form-container">
                <h2>${proyecto != null ? 'Editar Proyecto' : 'Agregar Nuevo Proyecto'}</h2>
                <form action="/proyectos/guardar" method="post">
                    <c:if test="${proyecto != null}">
                        <input type="hidden" name="idProyecto" value="${proyecto.idProyecto}">
                    </c:if>
                    <div class="form-group">
                        <label>Cliente: *</label>
                        <select name="cliente.idCliente" required>
                            <option value="">Seleccione un cliente</option>
                            <c:forEach var="c" items="${clientes}">
                                <option value="${c.idCliente}" ${proyecto != null && proyecto.cliente.idCliente == c.idCliente ? 'selected' : ''}>
                                    ${c.empresaCliente}
                                </option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label>Nombre del Proyecto: *</label>
                        <input type="text" name="nombreProyecto" value="${proyecto.nombreProyecto}" required maxlength="200">
                    </div>
                    <div class="form-group">
                        <label>Descripción:</label>
                        <textarea name="descripcionProyecto">${proyecto.descripcionProyecto}</textarea>
                    </div>
                    <div class="form-group">
                        <label>Complejidad:</label>
                        <select name="complejidadProyecto">
                            <option value="">Seleccione</option>
                            <option value="Baja" ${proyecto != null && proyecto.complejidadProyecto == 'Baja' ? 'selected' : ''}>Baja</option>
                            <option value="Media" ${proyecto != null && proyecto.complejidadProyecto == 'Media' ? 'selected' : ''}>Media</option>
                            <option value="Alta" ${proyecto != null && proyecto.complejidadProyecto == 'Alta' ? 'selected' : ''}>Alta</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label>Fecha de Inicio: *</label>
                        <input type="date" name="fechaInicioProyecto" value="${proyecto.fechaInicioProyecto}" required>
                    </div>
                    <div class="form-group">
                        <label>Fecha de Fin:</label>
                        <input type="date" name="fechaFinProyecto" value="${proyecto.fechaFinProyecto}">
                    </div>
                    <div class="form-group">
                        <label>Estado:</label>
                        <select name="estadoProyecto">
                            <option value="">Seleccione</option>
                            <option value="Planificación" ${proyecto != null && proyecto.estadoProyecto == 'Planificación' ? 'selected' : ''}>Planificación</option>
                            <option value="En Progreso" ${proyecto != null && proyecto.estadoProyecto == 'En Progreso' ? 'selected' : ''}>En Progreso</option>
                            <option value="Completado" ${proyecto != null && proyecto.estadoProyecto == 'Completado' ? 'selected' : ''}>Completado</option>
                            <option value="Cancelado" ${proyecto != null && proyecto.estadoProyecto == 'Cancelado' ? 'selected' : ''}>Cancelado</option>
                        </select>
                    </div>
                    <button type="submit" class="btn-submit">${proyecto != null ? 'Actualizar' : 'Guardar'}</button>
                    <c:if test="${proyecto != null}">
                        <a href="/proyectos"><button type="button" class="btn-cancel">Cancelar</button></a>
                    </c:if>
                </form>
            </div>

            <div class="table-container">
                <h2>Lista de Proyectos</h2>
                <table>
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Cliente</th>
                            <th>Nombre</th>
                            <th>Complejidad</th>
                            <th>Fecha Inicio</th>
                            <th>Fecha Fin</th>
                            <th>Estado</th>
                            <th>Acciones</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="p" items="${proyectos}">
                            <tr>
                                <td>${p.idProyecto}</td>
                                <td>${p.cliente.empresaCliente}</td>
                                <td>${p.nombreProyecto}</td>
                                <td>${p.complejidadProyecto}</td>
                                <td>${p.fechaInicioProyecto}</td>
                                <td>${p.fechaFinProyecto}</td>
                                <td>${p.estadoProyecto}</td>
                                <td>
                                    <a href="/proyectos/editar/${p.idProyecto}"><button class="btn-edit">Editar</button></a>
                                    <form action="/proyectos/eliminar/${p.idProyecto}" method="post" style="display:inline;" onsubmit="return confirm('¿Estás seguro de eliminar este proyecto?');">
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
