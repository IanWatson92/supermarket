function renderItems() {
	$.getJSON("/items" , function(data) {

		var tbl_body = "";
	    $.each(data, function() {
	        var tbl_row = "";
	        tbl_row += "<td>"+ this.name+"</td>";
	        tbl_row += "<td>"+ this.price+"</td>";

	        if(typeof this.weight === 'undefined') {
	        	tbl_row += "<td></td>";
	        } else {
	        	tbl_row += "<td>"+ this.weight+"</td>";
	        }
	        
	        
	        tbl_body += "<tr>"+tbl_row+"</tr>";
	    })
	    $("#my-ajax-table tbody").html(tbl_body);

	    var selectItems = "";
	    $.each(data, function() {
	    	var select = "";
	    	select += "<option value="+this.name+"\">"+this.name+"</option>";
	   		selectItems += select; 	
	    })
	    $("#dealItems").html(selectItems);
	});
}



$(document).ready(function() {
    renderItems();


    $('#addItem').submit(function () {
		var path = "/items";
		$.ajax({
			type: "POST",
			url: path,
			data: $("#addItem").serialize(),
			success: function(data) {
				renderItems();
				$("#my-ajax-table tbody").reload();		
			}
		});
		
		return false;
	});

	$( "#addDeal" ).submit(function () {
		var path = "/deals";
		$.ajax({
			type: "POST",
			url: path,
			data: $("#addDeal").serializeArray(),
			success: function(data) {
				console.log("success -> " + data)
			}
		});
		
		return false;
	});
});
