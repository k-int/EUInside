<%--
  Created by IntelliJ IDEA.
  User: sagarmichael
  Date: 24/04/2013
  Time: 09:41
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
  <title>upload</title>
</head>
<body>
<g:uploadForm id="update" url="[action: 'transform']">
       Please choose a file to upload<br/>
       <input id="inputField" type="file" name="file"  enctype="multipart/form-data" />    <br/>
                 <g:submitButton name="Submit"/>
    </g:uploadForm>
</body>
</html>