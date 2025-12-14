<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Gestión de Presupuestos</title>
    <style>
        * { margin: 0; padding: 0; box-sizing: border-box; }
        body { font-family: Arial, sans-serif; background: #f5f5f5; padding: 20px; }
        .container { max-width: 1600px; margin: 0 auto; }
        h1 { color: #333; margin-bottom: 30px; }
        .btn-home { display: inline-block; background: #667eea; color: white; padding: 10px 20px; text-decoration: none; border-radius: 5px; margin-bottom: 20px; }
        .btn-home:hover { background: #764ba2; }
        .content-wrapper { display: flex; gap: 20px; }
        .form-container { background: white; padding: 30px; border-radius: 10px; box-shadow: 0 2px 10px rgba(0,0,0,0.1); flex: 0 0 400px; max-height: 90vh; overflow-y: auto; }
        .form-group { margin-bottom: 15px; }
        .form-group label { display: block; margin-bottom: 5px; color: #333; font-weight: bold; }
        .form-group input, .form-group select, .form-group textarea { width: 100%; padding: 10px; border: 1px solid #ddd; border-radius: 5px; font-size: 14px; }
        .form-group textarea { resize: vertical; min-height: 60px; }
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
        <h1>💰 Gestión de Presupuestos</h1>

        <c:if test="${not empty mensaje}">
            <div class="mensaje exito">${mensaje}</div>
        </c:if>
        <c:if test="${not empty error}">
            <div class="mensaje error">${error}</div>
        </c:if>

        <div class="content-wrapper">
            <div class="form-container">
                <h2>${presupuesto != null ? 'Editar Presupuesto' : 'Agregar Nuevo Presupuesto'}</h2>
                <form action="/presupuestos/guardar" method="post">
                    <c:if test="${presupuesto != null}">
                        <input type="hidden" name="idPresupuesto" value="${presupuesto.idPresupuesto}">
                    </c:if>
                    <div class="form-group">
                        <label>Proyecto: *</label>
                        <select name="proyecto.idProyecto" required>
                            <option value="">Seleccione un proyecto</option>
                            <c:forEach var="p" items="${proyectos}">
                                <option value="${p.idProyecto}" ${presupuesto != null && presupuesto.proyecto.idProyecto == p.idProyecto ? 'selected' : ''}>
                                    ${p.nombreProyecto}
                                </option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label>Requisito (Opcional):</label>
                        <select name="requisito.idRequisitos">
                            <option value="">Sin asignar</option>
                            <c:forEach var="r" items="${requisitos}">
                                <option value="${r.idRequisitos}" ${presupuesto != null && presupuesto.requisito != null && presupuesto.requisito.idRequisitos == r.idRequisitos ? 'selected' : ''}>
                                    ${r.descripcionRequisitos}
                                </option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label>Colaborador (Opcional):</label>
                        <select name="colaborador.idColaborador">
                            <option value="">Sin asignar</option>
                            <c:forEach var="col" items="${colaboradores}">
                                <option value="${col.idColaborador}" ${presupuesto != null && presupuesto.colaborador != null && presupuesto.colaborador.idColaborador == col.idColaborador ? 'selected' : ''}>
                                    ${col.nombreColaborador} - ${col.rolColaborador}
                                </option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label>Subtotal:</label>
                        <input type="number" step="0.01" name="subtotal" value="${presupuesto.subtotal}">
                    </div>
                    <div class="form-group">
                        <label>IVA:</label>
                        <input type="number" step="0.01" name="iva" value="${presupuesto.iva}">
                    </div>
                    <div class="form-group">
                        <label>Total:</label>
                        <input type="number" step="0.01" name="total" value="${presupuesto.total}">
                    </div>
                    <div class="form-group">
                        <label>Fecha de Elaboración:</label>
                        <input type="date" name="fechaElaboracionPresupuesto" value="${presupuesto.fechaElaboracionPresupuesto}">
                    </div>
                    <div class="form-group">
                        <label>Estado:</label>
                        <select name="estadoPresupuesto">
                            <option value="">Seleccione</option>
                            <option value="Borrador" ${presupuesto != null && presupuesto.estadoPresupuesto == 'Borrador' ? 'selected' : ''}>Borrador</option>
                            <option value="En Revisión" ${presupuesto != null && presupuesto.estadoPresupuesto == 'En Revisión' ? 'selected' : ''}>En Revisión</option>
                            <option value="Aprobado" ${presupuesto != null && presupuesto.estadoPresupuesto == 'Aprobado' ? 'selected' : ''}>Aprobado</option>
                            <option value="Rechazado" ${presupuesto != null && presupuesto.estadoPresupuesto == 'Rechazado' ? 'selected' : ''}>Rechazado</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label>Método de Pago:</label>
                        <select name="metodoPagoPresupuesto">
                            <option value="">Seleccione</option>
                            <option value="Transferencia" ${presupuesto != null && presupuesto.metodoPagoPresupuesto == 'Transferencia' ? 'selected' : ''}>Transferencia</option>
                            <option value="Cheque" ${presupuesto != null && presupuesto.metodoPagoPresupuesto == 'Cheque' ? 'selected' : ''}>Cheque</option>
                            <option value="Efectivo" ${presupuesto != null && presupuesto.metodoPagoPresupuesto == 'Efectivo' ? 'selected' : ''}>Efectivo</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label>Servicios Adicionales:</label>
                        <textarea name="serviciosAdicionalesPresupuesto">${presupuesto.serviciosAdicionalesPresupuesto}</textarea>
                    </div>
                    <div class="form-group">
                        <label>Descripción:</label>
                        <textarea name="descripcionPresupuesto">${presupuesto.descripcionPresupuesto}</textarea>
                    </div>
                    <button type="submit" class="btn-submit">${presupuesto != null ? 'Actualizar' : 'Guardar'}</button>
                    <c:if test="${presupuesto != null}">
                        <a href="/presupuestos"><button type="button" class="btn-cancel">Cancelar</button></a>
                    </c:if>
                </form>
            </div>

            <div class="table-container">
                <h2>Lista de Presupuestos</h2>
                <table>
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Proyecto</th>
                            <th>Subtotal</th>
                            <th>IVA</th>
                            <th>Total</th>
                            <th>Fecha</th>
                            <th>Estado</th>
                            <th>Método Pago</th>
                            <th>Acciones</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="pre" items="${presupuestos}">
                            <tr>
                                <td>${pre.idPresupuesto}</td>
                                <td>${pre.proyecto.nombreProyecto}</td>
                                <td>$${pre.subtotal}</td>
                                <td>$${pre.iva}</td>
                                <td>$${pre.total}</td>
                                <td>${pre.fechaElaboracionPresupuesto}</td>
                                <td>${pre.estadoPresupuesto}</td>
                                <td>${pre.metodoPagoPresupuesto}</td>
                                <td>
                                    <a href="/presupuestos/editar/${pre.idPresupuesto}"><button class="btn-edit">Editar</button></a>
                                    <form action="/presupuestos/eliminar/${pre.idPresupuesto}" method="post" style="display:inline;" onsubmit="return confirm('¿Estás seguro de eliminar este presupuesto?');">
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
