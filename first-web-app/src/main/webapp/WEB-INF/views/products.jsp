<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!doctype html>
<html lang="en">
<jsp:include page="head.jsp">
    <jsp:param name="title" value="Product List"/>
</jsp:include>

<body>

<jsp:include page="navbar.jsp">
    <jsp:param name="pageName" value="Product List"/>
</jsp:include>

<div class="container">

    <div class="row py-2">
        <div class="col-md-3 col-xs-12">
            <ul class="list-group my-2">
                <li class="list-group-item active">Category 1</li>
                <li class="list-group-item">Category 2</li>
                <li class="list-group-item">Category 3</li>
                <li class="list-group-item">Category 4</li>
                <li class="list-group-item">Category 5</li>
            </ul>
        </div>

        <div class="col-md-9 col-xs-12">
            <c:url value="/products/new" var="newProductUrl"/>
            <a class="btn btn-primary my-2" href="${newProductUrl}">Add Product</a>

            <table class="table table-bordered">
                <thead>
                <tr>
                    <th scope="col">Id</th>
                    <th scope="col">Name</th>
                    <th scope="col">Price</th>
                    <th scope="col">Description</th>
                    <th scope="col">Actions</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="prod" items="${requestScope.productList}">
                <tr>
                    <th scope="row">
                        <c:out value="${prod.id}"/>
                    </th>
                    <td>
                        <c:out value="${prod.name}"/>
                    </td>
                    <td>
                        <c:out value="${prod.price}"/>
                    </td>
                    <td>
                        <c:out value="${prod.description}"/>
                    </td>
                    <td>
                        <c:url value="/products/${prod.id}" var="productUrl"/>
                        <a class="btn btn-success" href="${productUrl}"><i class="fas fa-edit"></i></a>
                        <a class="btn btn-danger" href="#"><i class="far fa-trash-alt"></i></a>
                    </td>
                </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>

<%@include file="scripts.jsp"%>

</body>
</html>