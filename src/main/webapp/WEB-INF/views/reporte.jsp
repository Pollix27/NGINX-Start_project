<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Generar Reporte XML</title>
    <style>
        * { margin: 0; padding: 0; box-sizing: border-box; }
        body { font-family: Arial, sans-serif; background: linear-gradient(135deg, #667eea 0%, #764ba2 100%); min-height: 100vh; display: flex; align-items: center; justify-content: center; padding: 20px; }
        .container { background: white; padding: 50px; border-radius: 15px; box-shadow: 0 10px 40px rgba(0,0,0,0.2); max-width: 600px; width: 100%; }
        h1 { color: #333; margin-bottom: 10px; text-align: center; }
        .subtitle { color: #666; text-align: center; margin-bottom: 30px; }
        .btn-home { display: inline-block; background: #6c757d; color: white; padding: 10px 20px; text-decoration: none; border-radius: 5px; margin-bottom: 30px; }
        .btn-home:hover { background: #5a6268; }
        .form-group { margin-bottom: 20px; }
        .form-group label { display: block; margin-bottom: 8px; color: #333; font-weight: bold; font-size: 16px; }
        .form-group select { width: 100%; padding: 12px; border: 2px solid #ddd; border-radius: 8px; font-size: 15px; background: white; }
        .form-group select:focus { outline: none; border-color: #667eea; }
        .btn-generate { background: #28a745; color: white; padding: 15px 40px; border: none; border-radius: 8px; cursor: pointer; font-size: 18px; font-weight: bold; width: 100%; margin-top: 10px; }
        .btn-generate:hover { background: #218838; }
        .btn-generate:disabled { background: #ccc; cursor: not-allowed; }
        .mensaje { padding: 15px; margin-bottom: 20px; border-radius: 5px; text-align: center; }
        .mensaje.error { background: #f8d7da; color: #721c24; border: 1px solid #f5c6cb; }
        .info-box { background: #e7f3ff; border-left: 4px solid #2196F3; padding: 15px; margin-top: 20px; border-radius: 5px; }
        .info-box p { color: #0c5460; margin: 5px 0; font-size: 14px; }
    </style>
    <script>
        function cargarProyectos() {
            const clienteId = document.getElementById('clienteSelect').value;
            const proyectoSelect = document.getElementById('proyectoSelect');
            const btnGenerar = document.getElementById('btnGenerar');
            
            proyectoSelect.innerHTML = '<option value="">Cargando...</option>';
            proyectoSelect.disabled = true;
            btnGenerar.disabled = true;
            
            if (!clienteId) {
                proyectoSelect.innerHTML = '<option value="">Primero seleccione un cliente</option>';
                return;
            }
            
            fetch('/api/clientes/' + clienteId)
                .then(response => response.json())
                .then(cliente => {
                    proyectoSelect.innerHTML = '<option value="">Seleccione un proyecto</option>';
                    
                    if (cliente.proyectos && cliente.proyectos.length > 0) {
                        cliente.proyectos.forEach(proyecto => {
                            const option = document.createElement('option');
                            option.value = proyecto.idProyecto;
                            option.textContent = proyecto.nombreProyecto + ' - ' + proyecto.estadoProyecto;
                            proyectoSelect.appendChild(option);
                        });
                        proyectoSelect.disabled = false;
                    } else {
                        proyectoSelect.innerHTML = '<option value="">Este cliente no tiene proyectos</option>';
                    }
                })
                .catch(error => {
                    console.error('Error:', error);
                    proyectoSelect.innerHTML = '<option value="">Error al cargar proyectos</option>';
                });
        }
        
        function habilitarBoton() {
            const proyectoId = document.getElementById('proyectoSelect').value;
            document.getElementById('btnGenerar').disabled = !proyectoId;
        }
        
        function generarReporte() {
            const proyectoId = document.getElementById('proyectoSelect').value;
            if (proyectoId) {
                window.location.href = '/api/reportes/proyecto/' + proyectoId + '/xml';
            }
        }
    </script>
</head>
<body>
    <div class="container">
        <a href="/" class="btn-home">← Volver al Inicio</a>
        <h1>📊 Generar Reporte XML</h1>
        <p class="subtitle">Seleccione un cliente y proyecto para generar el reporte</p>

        <c:if test="${not empty error}">
            <div class="mensaje error">${error}</div>
        </c:if>

        <div class="form-group">
            <label>1. Seleccione un Cliente:</label>
            <select id="clienteSelect" onchange="cargarProyectos()">
                <option value="">-- Seleccione un cliente --</option>
                <c:forEach var="cliente" items="${clientes}">
                    <option value="${cliente.idCliente}">${cliente.empresaCliente} (${cliente.rfcCliente})</option>
                </c:forEach>
            </select>
        </div>

        <div class="form-group">
            <label>2. Seleccione un Proyecto:</label>
            <select id="proyectoSelect" onchange="habilitarBoton()" disabled>
                <option value="">Primero seleccione un cliente</option>
            </select>
        </div>

        <button id="btnGenerar" class="btn-generate" onclick="generarReporte()" disabled>
            Generar Reporte XML
        </button>

        <div class="info-box">
            <p><strong>ℹ️ Información:</strong></p>
            <p>• El reporte incluirá todos los datos del proyecto seleccionado</p>
            <p>• Se descargará automáticamente como archivo XML</p>
            <p>• Incluye: Cliente, Requisitos, Sprints, Colaboradores y Presupuestos</p>
        </div>
    </div>
</body>
</html>
