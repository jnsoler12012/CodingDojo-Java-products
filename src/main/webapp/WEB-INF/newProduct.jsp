<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Product</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">

</head>
<body>
	<header class="bg-primary p-2 mb-5">
		<h1>Products &amp; Categories</h1>
		<a href="/products" class="btn btn-info">View Products</a>
		<a href="/categories" class="btn btn-info">View Categories</a>
		<a href="/products/new" class="btn btn-info">Add Product</a>
		<a href="/categories/new" class="btn btn-info">Add Category</a>
	</header>
	<main class="w-50 m-auto text-center">
		<h2 class="mb-5">Add New Product</h2>
		<form:form action="/products" method="POST" modelAttribute="product">
			<div class="form-group row">
				<form:label class="col-3" path="name">Name</form:label>
				<form:input class="form-control col-5" path="name"/>
				<form:errors class="text-danger col-4" path="name"/>
			</div>
			<div class="form-group row">
				<form:label class="col-3" path="description">Description</form:label>
				<form:textarea class="form-control col-5" path="description"/>
				<form:errors class="text-danger col-4" path="description"/>
			</div>
			<div class="form-group row">
				<form:label class="col-3" path="price">Price</form:label>
				<form:input class="form-control col-5" type="number" step=".01" min="0" max="10000" path="price"/>
				<form:errors class="text-danger col-4" path="price"/>
			</div>
			<input type="submit" class="btn btn-success mt-3" value="Add Product">
		</form:form>
	</main>
</body>
</html>