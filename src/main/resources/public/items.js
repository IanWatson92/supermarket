function renderItems() {
	$.getJSON("/items" , function(data) {

		var tbl_body = "";
	    $.each(data, function() {
	        var tbl_row = "";
	        
	        $.each(this, function(k , v) {
	            tbl_row += "<td>"+v+"</td>";
	        })
	        tbl_body += "<tr>"+tbl_row+"</tr>";
	    })
	    $("#my-ajax-table tbody").html(tbl_body);
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

});