<%@include file="Header.jsp"%>

<form action="<c:url value="/updateCategory"/>" method="post">
<table align="center" class="table-bordered">
<tr>
	<td colspan="2">Category Detail</td>
</tr>

<tr>
	<td>Category Id</td>
	<td><input type="text" name="categoryid" value="${categoryData.categoryid}" readOnly/></td>
</tr>

<tr>
	<td>Category Name </td>
	<td><input type="text" name="categoryname" value="${categoryData.categoryname}"/></td>
</tr>

<tr>
	<td>Category Desc </td>
	<td><input type="text" name="categorydesc" value="${categoryData.categorydesc}"/></td>
</tr>

<tr>
	<td colspan="2">
		<input type="submit" value="Update Category"/>
	</td>
</tr>

</table>
</form>