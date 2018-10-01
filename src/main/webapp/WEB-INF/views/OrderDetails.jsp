<%@include file="Header.jsp"%>

<table align="center" width="80%" class="table table-bordered">
<tr>
	<td colspan="4"><h3 align="center">Your Order</h3></td>
</tr>
<tr>
	<td>Item Name</td>
	<td>Price</td>
	<td>Quantity</td>
	<td>Total Price</td>
</tr>

<c:forEach items="${cartItemsPurchase}" var="cartitempurchase">
<tr>
	<td>${cartitempurchase.itemname}</td>
	<td>${cartitempurchase.price}</td>
	<td><input type="text" value="${cartitempurchase.quantity}" name="qty" required/></td>
	<td>${cartitempurchase.quantity * cartitempurchase.price}</td>
</tr>
</c:forEach>
<tr>
	<td colspan="3"> Grand Total </td>
	<td> ${grandTotal}</td>
</tr>
<tr>
	<td colspan="3"><a href="<c:url value="/cart"/>" class="btn btn-info">Move to Cart</a>
	<td colspan="2"><a href="<c:url value="/payment"/>" class="btn btn-info">Proceed Payment</a>
</tr>

</table>