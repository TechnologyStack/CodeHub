<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
  <title>Bootstrap Example</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<!-- <body>
<form method="POST" action="uploadMultipleFiles" enctype="multipart/form-data">
        File1 to upload: <input type="file" name="file"><br /> 
        <input type="submit" value="Upload">
    </form>
</body> -->

<body>

<div class="container">
  <h2>File Upload Form</h2>
  <div class="col-md-12">
  <form class="form-inline" method="POST" action="uploadMultipleFiles" enctype="multipart/form-data">
    <div id="fileUpload" class="form-group" >
        <input type="file" name="file" class="form-control"/><br/>
    </div>
    
    <div class="form-group">
    <input type="submit" name="submit" class="btn btn-primary" value="Upload"/>
    </div>
  
  </form>
</div>
  <div>
    <a href="javascript:addfield()"> <span class="glyphicon glyphicon-plus"></span></a>
    <a href="javascript:removefield()"> <span class="glyphicon glyphicon-minus"></span></a>
  </div>
</div>
<script>
function addfield(){
    var inputfield = document.createElement("input");
    var br = document.createElement("br");
    inputfield.setAttribute("type","file");
    inputfield.setAttribute("name","file");
    inputfield.setAttribute("class","form-control");
    var div = document.getElementById("fileUpload");
       div.appendChild(inputfield);
       div.appendChild(br);

}
function removefield(){
var div = document.getElementById("fileUpload");
div.removeChild(div.lastChild);


}

</script>
</body>
</html>