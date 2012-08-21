/**
 * @author Aminmi
 */

var services = 
[
	{
		'resourceID' : '1',
		'resourceName' : 'Flights',
		'resourceType' : 'RESTful',
		'columNum' : '4',
		'description' : 'About Flights…',
		'input' : ['departure','destination'],
		'output': ['flightname','departure','destination','date']
	},
	{
		'resourceID' : '2',
		'resourceName' : 'Hotels',
		'resourceType' : 'RESTful',
		'columNum' : '4',
		'description' : 'About Hotels…',
		'input' : ['adress'],
		'output': ['hotelname','location','adress','phone']
	},
	{
		'resourceID' : '3',
		'resourceName' : 'Weather',
		'resourceType' : 'RESTful',
		'columNum' : '2',
		'description' : 'About Weather…',
		'input' : ['location','date'],
		'output': ['location','date']
	},
	{
		'resourceID' : '4',
		'resourceName' : 'Restaurant',
		'resourceType' : 'RESTful',
		'columNum' : '3',
		'description' : 'About Restaurant…',
		'input' : ['adress'],
		'output': ['restaurantname','adress','phone']
	},
];

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
	//檢查選取的服務是否重複
	var $services = $('#pickedService').children();
	var currentId = $(this).attr('data-serviceId');
	var isUnique = true;
	$.each( $services, function() {
		var id = $(this).attr('data-serviceId');
		if( id == currentId ) {
			alert('您選的服務已經選取過了！');
			isUnique = false;
			return false;
		}//end if
	});//end each
	
	if( !isUnique ) {
		return null;
	}//end if
	
	var clone = $(this).clone( true );
	//var clone = $.extend( {}, $(this) );
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
		},
	});//end ajax
}//end associatedRecommendedService

function dropService() {
	/*
	 * 移除點選的element
	 */
	$(this).remove();
}//end dropService
