<%@include file="Header.jsp"%>

<h2 align="center">Register Page</h2>

<div class="container">

            <h3 class="text-center">
               Sign Up To Register
            </h3>
	
           <form class="form-horizontal" method ="POST" action="Header.jsp" >
        		
    <div class="form-group">
      <label class="control-label col-md-5" for="username">Name: </label>
<div class="col-md-6">
      <input type="text" class="form-control" id="username" placeholder="Enter your name here" name="username"  pattern="[0-9A-z_.]{4,15}" 
              title="Atleast 4 characters (use only alphabets,numbers and '.','_')" required>
</div>
    </div>
	
    <div class="form-group">
      <label class="control-label col-md-5" for="mobileNo">Phone: </label>
	  <div class="col-md-6">
      <input type="text" class="form-control" id="mobileNo"  name="mobileNo"  pattern="{1,15}"  required>
	  </div>
    </div>
	
	<div class="form-group">
	<label class="control-label col-md-5" for="emailId">Email: </label>
	<div class="col-md-6">
	<input type="email" class="form-control" id="emailId" name="emailId" required>
	</div>
</div>

<div class="form-group">
	<label class="control-label col-md-5" for="password">Password:</label>
	<div class="col-md-6">
	<input type="password" class="form-control" id="password" name="password"  pattern="[0-9]{1,15}"  required>
	</div>
</div>

<div class="form-group">
	<label class="control-label col-md-5" for="customerName">Customer Name:</label>
	<div class="col-md-6">
	<input type="password" class="form-control" id="customerName" name="customerName"  pattern="[0-9]{1,15}"  required>
	</div>
</div>
                 
        <center>
        <br><br><br>
        <button type="submit" class="btn btn-md btn-success" name="submit" value="Submit">Submit</button>
        </center>
        
           </form>
        </div>
  
