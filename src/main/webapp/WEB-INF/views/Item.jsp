<%@include file="Header.jsp"%>

<c:if test="${flag}">
	<form:form action="addItem" modelAttribute="item" method="post" enctype="multipart/form-data">
<table align="center">
	<tr>
		<td colspan="2"><center>Item Detail</center></td>
	</tr>
	<tr>
		<td>Item Name</td>
		<td><form:input path="itemname" /></td>
	</tr>
	<tr bgcolor="pink">
		<td>Price</td>
		<td><form:input path="price" /></td>
	</tr>
	<tr>
		<td>Quantity</td>
		<td><form:input path="quantity" /></td>
	</tr>

	<tr bgcolor="pink">
		<td>Category</td>
		<td><form:select path="categoryid">
				<form:option value="0" label="--Select List--" />
				<form:options items="${categoryList}" />
			</form:select></td>
	</tr>
	<tr>
		<td>Distributor</td>
		<td><form:input path="distributorid" /></td>
	</tr>
	<tr bgcolor="pink">
		<td>Item Description</td>
		<td><form:input path="itemdesc" /></td>
	</tr>
	<tr>
		<td>Item Image</td>
		<td><form:input type="file" path="picmage"/></td>
	</tr>
	
	<tr>
		<td colspan="2"><center>
				<input type="submit" value="Insert Item" />
				<center>
		</td>
	</tr>
	</table>
	</form:form>
	</c:if>
	
	<c:if test="${!flag}">
	<form:form action="http://localhost:8085/FrontLineFrontEnd/updateItem" modelAttribute="item" method="post">
	<table align="center">
	<tr>
		<td colspan="2"><center>Item Detail</center></td>
	</tr>
	<tr>
		<td>Item Name</td>
		<td><form:input path="itemname" /></td>
	</tr>
	<tr bgcolor="pink">
		<td>Price</td>
		<td><form:input path="price" /></td>
	</tr>
	<tr>
		<td>Quantity</td>
		<td><form:input path="quantity" /></td>
	</tr>
	<tr bgcolor="pink">
		<td>Category</td>
		<td><form:select path="categoryid">
				<form:option value="0" label="--Select List--" />
				<form:options items="${categoryList}" />
			</form:select></td>
	</tr>
	<tr>
		<td>Distributor</td>
		<td><form:input path="distributorid" /></td>
	</tr>
	<tr bgcolor="pink">
		<td>Product Description</td>
		<td><form:input path="distributordesc" /></td>
	</tr>
	<tr>
		<td colspan="2"><center>
				<input type="submit" value="Update Item" />
				<center>
		</td>
	</tr>

	</table>
	</form:form>
</c:if>


<table align="center" class="table">
<tr><td colspan="6"><center>Item Detail</center></td></tr>
<tr>
	<td> Item ID</td>
	<td> Item Name</td>
	<td> Price </td>
	<td> Quantity</td>
	<td> Category</td>
	<td> Operation</td>
</tr>
<c:forEach items="${itemList}" var="item">
<tr>
		<td>${item.itemid}</td>
		<td>${item.itemname}</td>
		<td>${item.price}</td>
		<td>${item.quantity}</td>
		<td>${item.categoryid}</td>
		<td>
		<a href="<c:url value="/deleteItem/${item.itemid}"/>">Delete</a>
		<a href="<c:url value="/editItem/${item.itemid}"/>">Edit</a>
		</td>
</tr>
</c:forEach>

</table>


	