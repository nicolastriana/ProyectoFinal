<%-- 
    Document   : index
    Created on : 28/11/2018, 11:30:59 PM
    Author     : Nicolas Triana
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ATG Ltda</title>
        <style>
            form{
                margin: auto;
                /*width: 18%;*/
                max-width: 500px;
                background: #F3F3F3;
                padding: 12px;
                border: 2px solid rgba(0,0,0,0.2);
            }

            input{
                display: block;
                padding: 10px;
                width: 95%;
                margin: 30px 0;
                font-size: 20px;

            }

            input[type="submit"]{
                background: linear-gradient(#FFDA63, #FFB940);
                border: 0;
                color: brown;
                opacity: 0.8;
                cursor: pointer;
                border-radius: 20px;
                margin-bottom: 0;
                width: 100%;
            }

            input[type="submit"]:hover{
                opacity: 1;
            }

            input[type="submit"]:active{
                transform: scale(0.95); 
            }   
        </style>
    </head>
    <body>
        <form action="">
                <h1 align="center"> Formulario </h1>
                <input type="text" placeholder="&#128272; Usuario" name="usuario">
                <input type="password" placeholder="&#128272; Contraseña" name="clave">
                <input type="submit" value="Ingresar">
        </form>
    </body>
</html>
