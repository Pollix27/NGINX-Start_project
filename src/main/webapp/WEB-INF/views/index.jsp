<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>NGINX Start Project</title>
    <style>
        * { margin: 0; padding: 0; box-sizing: border-box; }
        body { font-family: Arial, sans-serif; background: linear-gradient(135deg, #667eea 0%, #764ba2 100%); min-height: 100vh; display: flex; align-items: center; justify-content: center; }
        .container { background: white; padding: 50px; border-radius: 15px; box-shadow: 0 10px 40px rgba(0,0,0,0.2); text-align: center; max-width: 600px; }
        h1 { color: #333; margin-bottom: 20px; font-size: 2.5em; }
        p { color: #666; margin-bottom: 30px; font-size: 1.1em; }
        .menu { display: flex; flex-wrap: wrap; gap: 15px; justify-content: center; }
        .menu a { background: #667eea; color: white; padding: 12px 25px; text-decoration: none; border-radius: 8px; transition: all 0.3s; font-weight: bold; }
        .menu a:hover { background: #764ba2; transform: translateY(-2px); box-shadow: 0 5px 15px rgba(0,0,0,0.2); }
    </style>
</head>
<body>
    <div class="container">
        <h1>🚀 NGINX Start Project</h1>
        <p>Sistema de Gestión de Proyectos con Spring Boot</p>
        <div class="menu">
            <a href="/clientes">Clientes</a>
            <a href="/proyectos">Proyectos</a>
            <a href="/requisitos">Requisitos</a>
            <a href="/sprints">Sprints</a>
            <a href="/colaboradores">Colaboradores</a>
            <a href="/presupuestos">Presupuestos</a>
            <a href="/reporte">Reportes</a>
        </div>
    </div>
</body>
</html>
