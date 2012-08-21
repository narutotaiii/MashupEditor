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
			//alert( msg[0].columnNum );
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
			alert('FAIL');
		},
	});
	$('#searchResult').text("");
	
}//end searchService

function pickService() {
	/*
	 * 會把點選到的element複製到#pickedService
	 * 也會檢查裡面有沒有相同的服務data-serviceId
	 * 有的話就不加入到#pickedService裡面
	 * 並且放進來的.aService的onclick會改成dropService()
	 */
	var clone = $(this).clone();
	clone.click( dropService ).appendTo('#pickedService');
	
	associatedRecommendedService( $(this).attr('data-serviceId') );
}//end pickService

function associatedRecommendedService( o ) {
	/*
	 * 把data-serviceId藉由ajax的技術傳給servlet
	 * 把回傳後的結果放到#recommendService裡面
	 * 每個.aService按了都會觸發pickService()
	 * 以及associatedRecommendedService()
	 */
	$('#recommendService').text("");
	$.each( services, function() {
		if( o == this.resourceID ) {
			return null;
		}
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
				text: this.columNum
			}),
			$('<span>',{
				"class": "description",
				text: this.description
			})
		)
		.appendTo('#recommendService');
	});
	
}//end associatedRecommendedService

function dropService() {
	/*
	 * 移除點選的element
	 */
	$(this).remove();
}//end dropService
