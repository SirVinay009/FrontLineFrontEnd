<%@include file="Header.jsp"%>

<table align="center" width="80%" class="table table-bordered">
<tr>
	<td colspan="5"><h3 align="center">Your Cart</h3></td>
</tr>
<tr>
	<td>Item Name</td>
	<td>Price</td>
	<td>Quantity</td>
	<td>Total Price</td>
	<td>Operations</td>
</tr>
<c:forEach items="${cartItemsPurchase}" var="cartitempurchase">
<form action="<c:url value="/updateCartItemPurchase/${cartitempurchase.cartitempurchaseid}"/>" method="get">
<tr>
	<td>${cartitempurchase.itemname}</td>
	<td>${cartitempurchase.price}</td>
	<td><input type="text" value="${cartitempurchase.quantity}" name="qty" required/></td>
	<td>${cartitempurchase.quantity * cartitempurchase.price}</td>
	<td>
		<input type="submit" value="UPDATE" class="btn btn-info">
		<a href="<c:url value="/deleteCartItemPurchase/${cartitempurchase.cartitempurchaseid}"/>" class="btn btn-danger">DELETE</a>
	</td>
</tr>
</form>
</c:forEach>
<tr>
	<td colspan="3"> Grand Total </td>
	<td colspan="2"> ${grandTotal}</td>
</tr>
<tr>
	<td colspan="3"><a href="<c:url value="/itemCatalog"/>" class="btn btn-info">Continue Shopping</a>
	<td colspan="2"><a href="<c:url value="/checkout"/>" class="btn btn-info">Checkout</a>
</tr>

</table>