<%--
  Created by IntelliJ IDEA.
  User: wrg
  Date: 2023-09-27
  Time: 14:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>默认页面</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f0f0f0;
                margin: 0;
                padding: 0;
                display: flex;
                justify-content: center;
                align-items: center;
                height: 100vh;
            }

            .container {
                background-color: #fff;
                padding: 20px;
                border-radius: 10px;
                animation: borderChange 5s infinite alternate, colorChange 5s infinite alternate;
                box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.2);
                text-align: center;
            }

            h1 {
                margin: 0; /* 去掉标题上下的边距 */
            }

            @keyframes borderChange {
                0% {
                    border: 2px solid #ff5733; /* 初始边框颜色 */
                }
                100% {
                    border: 2px solid #33ff57; /* 结束边框颜色 */
                }
            }

            @keyframes colorChange {
                0% {
                    color: #ff5733; /* 初始颜色 */
                }
                100% {
                    color: #33ff57; /* 结束颜色 */
                }
            }
        </style>
    </head>
    <body>
        <div class="container">
            <h1>默认页面</h1>
        </div>
    </body>
</html>
