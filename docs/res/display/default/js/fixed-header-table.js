$(document).ready(function() {
	
	$('#ogw_fixed_header').after('<table id="header-fixed"></table>');
	
	// cf. http://stackoverflow.com/questions/4709390/table-header-to-stay-fixed-at-the-top-when-user-scrolls-it-out-of-view-with-jque

	var tableOffset = $("#ogw_fixed_header").offset().top;
	
	$("#ogw_fixed_header > colgroup").each(function(){
		var fxh_header = $(this).clone();
		$("#header-fixed").append(fxh_header);
	});
	
	var fxh_header = $("#ogw_fixed_header > thead").clone();
	var fxh_fixedHeader = $("#header-fixed").append(fxh_header);
	
	$("#ogw_fixed_header > thead > tr > th").each(function(idx){
		var th_width = $(this).width();
		$("#header-fixed > thead > tr > th").each(function(fidx){
			if (fidx == idx) {
				$(this).width(th_width);
			}
		});
	});

	$(window).bind("scroll", function() {
	    var offset = $(this).scrollTop();
    
	    if (offset >= tableOffset && fxh_fixedHeader.is(":hidden")) {
	        fxh_fixedHeader.show();
	    } else if (offset < tableOffset) {
	        fxh_fixedHeader.hide();
	    }
	});

});