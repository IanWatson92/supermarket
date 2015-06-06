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
	        tbl_row += "<td>"+ this.dealType+"</td>";
	        tbl_row += "<td>"+ items+"</td>";
	        tbl_row += "<td>"+ this.quantityNeeded+"</td>";
	        tbl_body += "<tr>"+tbl_row+"</tr>";
	        //tbl_body += "<tr>123</tr>";
	    })
	    $("#deals-table tbody").html(tbl_body);
	});

	$.getJSON("/deals/types" , function(data) {

	    var selectItems = "";
	    $.each(data, function(index, object) {
	    	console.log("Index -> " + index)
	    	console.log("Name -> " + object)
	    	var select = "";
	    	if (index == 0) {
	    		select += "<option value=\""+object+"\" selected>"+object+"</option>";
	   		} else {
	   			select += "<option value=\""+object+"\">"+object+"</option>";
	   		}
	   		selectItems += select; 	
	    })
	    $("#dealTypes").html(selectItems);

	    var type = $("#dealTypes option[selected]").attr("value")

	    if (type === "Buy X Get Y Free") {
	    	console.log("in if!");
	    	$("#insertInto").append("Items Free:<br><input type=\"number\" name=\"itemsFree\" id=\"itemsFree\" min=\"1\" step=\"1\">");
	    }
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
