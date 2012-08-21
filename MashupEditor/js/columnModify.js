/**
 * @author Aminmi
 */

function createTabs() {
	/*
	
	$('<div/>', {
		"class": "aSelectService",
		text: "Service"
	})
	.appendTo('#columnModifyUI .selectServices');
	
	if( !$('#pickedService div').length ) {
		return null;
	}
	var selectService = $('#pickedService div');
	var list = [];
	$.each( selectService, function() {
		list.push( $(this).attr('data-serviceid') );
	});
	
	*/
	
	$('#columnModifyUI .aServiceColumn:first-child')
	.stop(false, true)
	.fadeIn()
	.siblings()
	.hide();
	
	$('#columnModifyUI .aSelectService').click( function() {
			var $this = $(this),
				clickTab = $this.text();
							
			$('#columnModifyUI div[data-service="' + clickTab + '"]')
			.stop(false, true)
			.fadeIn()
			.siblings()
			.hide();
	})
}