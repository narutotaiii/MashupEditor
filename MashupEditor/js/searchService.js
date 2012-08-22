/**
 * @author Aminmi
 */

function searchService() {
	/*
	 * get the input value and then use ajax to
	 * connect to servlet.
	 * 
	 * get the result and use 'each' to insert the
	 * data to the <div> #searchResult
	 */
	
	//get the input value
	var key = $('input.searchService').val();
	//use ajax technique
	$.ajax({
		type: 'POST',
		url: 'http://140.121.197.106:8080/restfulService/systemFlow.do',
		data: {
				service: 'search',
				query: key
			},
		dataType: "json",
		scriptCharset: "utf-8" ,
		success: function(msg) {
			$('#searchResult').text("");
			$.each( msg, function() {
				$('<div/>', {
					"class": "aService",
					"data-serviceId": this.resourceID,
					click: pickService
				})
				.append(
					$('<span>',{
						"class": "serviceName",
						text: this.resourceName
					}),
					$('<span>',{
						"class": "serviceType",
						text: this.resourceType
					}),
					$('<span>',{
						"class": "columNum",
						text: this.columnNum
					}),
					$('<span>',{
						"class": "description",
						text: this.description,
						title: this.description
					})
				)
				.appendTo('#searchResult');
			});
		},
		error: function() {
			alert('ajax:search failed');
		},
	});
	
}//end searchService

function pickService() {
	//call associatedRecommendedService
	associatedRecommendedService( $(this).attr('data-serviceId') );
	//check the picked service is not duplicate.
	var $services = $('#pickedService').children();
	var currentId = $(this).attr('data-serviceId');
	var isUnique = true;
	$.each( $services, function() {
		var id = $(this).attr('data-serviceId');
		if( id == currentId ) {
			alert('Can\'t pick the same serive!');
			isUnique = false;
			return false;
		}//end if
	});//end each
	
	if( !isUnique ) {
		return null;
	}//end if
	
	/*
	 * $.clone( true );
	 * means that all attributes will totally be cloned.
	 * see http://api.jquery.com/clone/
	 */
	var clone = $(this).clone( true );
	clone.unbind('click').click( dropService ).appendTo('#pickedService');
}//end pickService

function associatedRecommendedService( serviceId ) {
	//ajax
	$.ajax({
		type: 'POST',
		url: 'http://140.121.197.106:8080/restfulService/systemFlow.do',
		data: {
				service: 'relationalRecommand',
				data: serviceId
			},
		dataType: "json",
		scriptCharset: "utf-8" ,
		success: function(msg) {
			$('#recommendService').text("");
			$.each( msg, function() {
				$('<div/>', {
					"class": "aService",
					"data-serviceId": this.resourceID,
					click: pickService
				})
				.append(
					$('<span>',{
						"class": "serviceName",
						text: this.resourceName
					}),
					$('<span>',{
						"class": "serviceType",
						text: this.resourceType
					}),
					$('<span>',{
						"class": "columNum",
						text: this.columnNum
					}),
					$('<span>',{
						"class": "description",
						text: this.description,
						title: this.description
					})
				)
				.appendTo('#recommendService');
			});
		},
		error: function() {
			alert('ajax:relationalRecommand failed');
		}
	});//end ajax
}//end associatedRecommendedService

function dropService() {
	/*
	 * remove the picked service
	 */
	$(this).remove();
}//end dropService
