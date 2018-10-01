<%@include file="Header.jsp"%>

<div class="container">
<table align="center" class="table">
<tr>
<td colspan="3"><center>Item Detail</center></td>
</tr>
<tr>
	<td rowspan="9">
			<img src="<c:url value="/resources/images/${itemInfo.itemid}.jpg"/>" width="300" height="200"/>
	</td>
</tr>
<tr>
	<td>Product ID</td>
	<td>${itemInfo.itemid}</td>
</tr>
<tr>
	<td>Product Name</td>
	<td>${itemInfo.itemname}</td>
</tr>
<tr>
	<td>Supplier ID</td>
	<td>${itemInfo.distributorid}</td>
</tr>
<tr>
	<td>Category</td>
	<td>${categoryname}</td>
</tr>
<tr>
	<td>Price</td>
	<td>${itemInfo.price}</td>
</tr>
<tr>
	<td>Stock</td>
	<td>${itemInfo.quantity}</td>
</tr>
<tr>
	<td>Product Description</td>
	<td>${itemInfo.itemdesc}</td>
</tr>

<form action="<c:url value="/addToCartItem/${itemInfo.itemid}"/>" method="get">
<tr>
	<td>Quantity</td>
	<td><input type="text" name="qty" required/></td>
</tr>
<tr>
	<td></td>
	<td> <input type="submit" value="ADDTOCartItem" class="btn btn-info"/></td>
</tr>
</form>
</table>
</div>

