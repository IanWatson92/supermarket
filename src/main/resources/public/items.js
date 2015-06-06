function renderItems() {
	$.getJSON("/items" , function(data) {

		var tbl_body = "";
	    $.each(data, function() {
	        var tbl_row = "";
	        tbl_row += "<td>"+ this.name+"</td>";
	        tbl_row += "<td>"+ this.price+"</td>";

	        if(typeof this.weight === 'undefined') {
	        	tbl_row += "<td>n/a</td>";
	        } else {
	        	tbl_row += "<td>"+ this.weight+"</td>";
	        }
	        
	        
	        tbl_body += "<tr>"+tbl_row+"</tr>";
	    })
	    $("#items-table tbody").html(tbl_body);

	    var selectItems = "";
	    $.each(data, function() {
	    	var select = "";
	    	select += "<option value="+this.name+">"+this.name+"</option>";
	   		selectItems += select; 	
	    })
	    $("#dealItems").html(selectItems);
	});
}

function renderDeals() {
	$.getJSON("/deals" , function(data) {

		var tbl_body = "";
	    $.each(data, function() {
	    	console.log("quantity -> " + this.quantityNeeded);
	        var tbl_row = "";

	        var items = "";
	        $.each(this.itemsNeeded, function() {
	        	items += this.name + " ";
	        }) 
	        console.log("items -> " + items);
	        tbl_row += "<td>"+ items+"</td>";
	        tbl_row += "<td>"+ this.quantityNeeded+"</td>";
	        tbl_body += "<tr>"+tbl_row+"</tr>";
	        //tbl_body += "<tr>123</tr>";
	    })
	    $("#deals-table tbody").html(tbl_body);
	});
}



$(document).ready(function() {
    renderItems();
    renderDeals();


    $('#addItem').submit(function () {
		var path = "/items";
		$.ajax({
			type: "POST",
			url: path,
			data: $("#addItem").serialize(),
			success: function(data) {
				renderItems();
				$("#items-table tbody").reload();		
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
				renderDeals();
				$("#deals-table tbody").reload();
			}
		});
		
		return false;
	});
});
