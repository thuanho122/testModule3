<%--
  Created by IntelliJ IDEA.
  User: thuanho
  Date: 10/01/2022
  Time: 09:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Edit Product</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
  <link rel="stylesheet" href="ba1.css">
  <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css"
        integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous" />
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
          integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous">
  </script>


  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>

  <script src="https://cdn.datatables.net/1.10.20/js/jquery.dataTables.min.js"></script>
  <script src="https://cdn.datatables.net/1.10.20/js/dataTables.bootstrap4.min.js"></script>
  <script src="https://cdn.datatables.net/1.10.20/css/dataTables.bootstrap4.min.css"></script>

</head>

<body>

<div class="container py-5">
  <h4>Update Product</h4>
  <div class="row">
    <div class="col-md-12 mx-auto">
      <form method="post">
        <c:if test="${product != null}">
          <input type="hidden" name="id" value="<c:out value='${product.id}' />"/>
        </c:if>
        <div class="col-sm-12">
          <label for="nameProduct">Name</label>
          <input type="text" value="<c:out value='${product.name}' />" class="form-control" id="nameProduct" name="nameProduct" required>
        </div>
        <div class="col-sm-12">
          <label for="priceProduct">Price</label>
          <input type="number" value="<c:out value='${product.price}' />"  class="form-control" id="priceProduct" name="priceProduct" required>
        </div>
        <div class="col-sm-12">
          <label for="quantityProduct">Quantity</label>
          <input type="number" value="<c:out value='${product.quantity}' />" class="form-control" id="quantityProduct" name="quantityProduct" required>
        </div>
        <div class="col-sm-12">
          <label for="colorProduct">Color</label>
          <input type="text" value="<c:out value='${product.color}' />" class="form-control" id="colorProduct" name="colorProduct" required>
        </div>
        <div class="col-sm-12">
          <label for="descriptionProduct">Description</label>
          <input type="text" value="<c:out value='${product.description}' />" class="form-control" id="descriptionProduct" name="descriptionProduct" required>
        </div>
        <div class="col-sm-12">
          <label for="catoryProduct">Category</label>
          <select name="selectcategory" id="catoryProduct">
            <c:forEach var="category" items="${listcategory}">
              <option value="<c:out value='${category.id}'/>"><c:out value="${category.name}"/></option>
            </c:forEach>
          </select>
        </div>
        <div class="col-sm-3">
          <button id="createProduct" type="submit" class="btn btn btn-outline-primary px-4 ">Update</button>
          <a id="backproduct" href="#" type="button" class="btn btn btn-outline-primary px-4 ">Back</a>
        </div>
      </form>
    </div>
  </div>
</div>
</body>

</html>
