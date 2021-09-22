<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!doctype html>
<html lang="en">

<jsp:include page="head.jsp">
    <jsp:param name="title" value="Product Edit Form ${requestScope.product.name}"/>
</jsp:include>

<body>
<jsp:include page="navbar.jsp">
    <jsp:param name="pageName" value="Product Edit Form ${requestScope.product.name}"/>
</jsp:include>


<div class="container">
    <div class="row py-2">
        <div class="col-12">
            <c:url value="/products" var="productSubmitUrl"/>
            <form action="${productSubmitUrl}" method="post">
                <input value="${requestScope.product.id}" type="hidden" id="id" name="id">
                <div class="form-group">
                    <label>Name</label>
                    <label for="name"></label><input value="${requestScope.product.name}" type="text" class="form-control" id="name" name="name" placeholder="Enter name">
                </div>
                <div class="form-group">
                    <label>Price</label>
                    <label for="price"></label><input value="${requestScope.product.price}" type="number" class="form-control" id="price" name="price" placeholder="Enter price" >
                </div>
                <div class="form-group">
                    <label>Description</label>
                    <label for="description"></label><input value="${requestScope.product.description}" type="text" class="form-control" id="description" name="description" placeholder="Enter description" >
                </div>
                <button type="submit" class="btn btn-primary">Submit</button>
            </form>
        </div>
    </div>
</div>

<%@include file="scripts.jsp"%>

</body>

</html>