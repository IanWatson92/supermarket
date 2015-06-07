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
	   
	        var tbl_row = "";

	        var items = "";
	        $.each(this.itemsNeeded, function() {
	        	items += this.name + " ";
	        }) 
	      
	        tbl_row += "<td>"+ this.dealType+"</td>";
	        tbl_row += "<td>"+ items+"</td>";
	        tbl_row += "<td>"+ this.quantityNeeded+"</td>";
	        tbl_row += "<td>"+ this.dealInfo+"</td>";
	        tbl_body += "<tr>"+tbl_row+"</tr>";
	        //tbl_body += "<tr>123</tr>";
	    })
	    $("#deals-table tbody").html(tbl_body);
	});

	$.getJSON("/deals/types" , function(data) {

	    var selectItems = "";
	
	    $.each(data, function(index, object) {
	
	    	var select = "";
	    	if (index == 0) {
	    		select += "<option value=\""+object.id+"\" selected>"+object.name+"</option>";
	   		} else {
	   			select += "<option value=\""+object.id+"\">"+object.name+"</option>";
	   		}
	   		selectItems += select; 	
	    })
	    $("#dealTypes").html(selectItems);

	    var type = $("#dealTypes option[selected]").attr("value")
	   
	    updateDealForm(type);
	});
}

function updateDealForm(type) {

	if (type == 1) {
	 	
		$("#insertInto").empty();
	 	$("#insertInto").append("Items Free:<br><input type=\"number\" name=\"itemsFree\" id=\"itemsFree\" min=\"1\" step=\"1\">");
	} else if (type == 2) {

		$("#insertInto").empty();
		$("#insertInto").append("Discount price:<br><input type=\"number\" name=\"discountPrice\" id=\"discountPrice\" min=\"0.01\" step=\"0.01\">");
	}
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
				//$("#deals-table tbody").reload();
			}
		});
		
		return false;
	});

	$('#dealTypes').on('change', function() {

		var type = this.value;

		updateDealForm(type);
	});

});
