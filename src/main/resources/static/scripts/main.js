console.log("Started");
$(document).ready(function()
{	
	//alert auto close and delay    
    $(".alert").delay(3500).slideUp(1000, function() {
    $(this).alert('close');
	});
	
	//send product id to del modal upon clicking delete
    $(document).on("click", "#del-btn", function () 
    {
        var productid = $(this).val();
        $(".modal-footer #delId").val(productid);
        $(".modal-body #delId").val(productid);
    });
    $(document).on("click", "#delByUser-btn", function () 
    {
        var productid = $(this).val();
        $(".modal-footer #delbyUserId").val(productid);
        $(".modal-body #delbyUserId").val(productid);
    });
    //send product id to edit modal upon clicking edit
    $(document).on("click", "#edit-btn", function () 
    {
        let productId = $(this).val();
        $(".modal-footer #send-edit-id").val(productId);
        $(".edit-product-form #send-edit-id").val(productId);
   });
    //send product id to purchase modal upon clicking purchase
    $(document).on("click", "#purchase-btn", function () 
    {
        let productId = $(this).val();
        $(".modal-footer #send-purchase-id").val(productId);
        $(".edit-product-form #send-purchase-id").val(productId);

        console.log(purchaseQuantity);
    });
    // ==== ADD FRIEND ==== //
    $(document).on("click", "#add-friend-btn", function ()
    {
        let friendUsername = $("#fname").val();
        $(".row #send-req-username").val(friendUsername);
    });

    // ==== REMOVE FRIEND ==== //
    $(document).on("click", "#del-btn", function ()
    {
        // let getTr = $("#current-friend-username").parentsUntil("tr");
        // let  = $("#current-friend-username").text();
        let friendUsername = $(this).siblings("input").val();
        console.log(friendUsername);
    });

    // ==== REMOVE REQUEST ==== //
    $(document).on("click", "#del-req-btn", function ()
    {
        let friendUsername = $(this).siblings("input").val();
        console.log(friendUsername);
    });
    
   //reopen modal if validation errors exist
	if ($('#editError').val() == "true") 
	{
		$('#editError').val("0");
		$(".edit-product-form #send-edit-id").val($('#edit-btn').val());
		console.log($('#send-edit-id').val())
		//$('#editModal').modal('toggle');
	}
  	//reopen modal if validation errors exist		
	if ($('#addError').val() == "true") 
	{
		$('#addError').val("0");
		$('#addModal').modal('toggle');
	}
  		
});
   


