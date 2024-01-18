<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>All Products</title>
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
	<main class="text-center">
		<h2 class="mb-5"><c:out value="${ product.name }"/></h2>
		<div class="row w-50 m-auto">
			<div class="col">
				<h4>Price</h4>
			</div>
			<div class="col">
				<h4>Description</h4>
			</div>
		</div>
		<div class="row w-50 m-auto">
			<div class="col">
				<h4>$${ product.price }</h4>
			</div>
			<div class="col">
				<p>${ product.description }</p>
			</div>
		</div>
		<div class="row mt-5">
			<div class="col">
				<h3>Categories:</h3>
				<ul>
					<c:forEach items="${ product.categories }" var="category">
					<li>
						<h6>${ category.name }</h6>
					</li>
					</c:forEach>
				</ul>
			</div>
			<div class="col">
				<h3>Add to Category:</h3>
				<form action="/addProductToCategory/${ product.id }" method="POST">
					<select class="form-control w-25 m-auto" name="categoryId">
						<c:forEach items="${ categories }" var="category">
						<option value="${ category.id }">${ category.name }</option>
						</c:forEach>
					</select>
					<input type="submit" class="btn btn-success mt-3" value="Add">
				</form>
			</div>
		</div>
	</main>
</body>
</html>