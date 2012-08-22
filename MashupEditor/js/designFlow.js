/**
 * @author Aminmi
 */

var selectServices = 
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
	}
];

function overallRecommendedService() {
	$('.recommendDesignFlow').fadeToggle();
	$('.recommendDesignFlow .aOverallRecommend:first-child')
	.stop(false, true)
	.fadeIn()
	.siblings()
	.hide();
	
	$('.recommendDesignFlow .aSelectService').click( function() {
			var $this = $(this),
				clickTab = $this.text();
			$('.recommendDesignFlow #'+ clickTab)
			.stop(false, true)
			.fadeIn()
			.siblings()
			.hide();
	})
}//end overallRecommendedService

function createNewLevel() {
	var $level = $(this).parent().parent().parent().parent();
	
	$('<div/>', {
		'class': 'aLevel'
	})
	.append(
		$('<div/>', {
			'class': 'aLevelMenu'
		})
		.append(
			$('<div class="pattern">'+'<select>'+'<option>Sequence</option><option>Selection</option>'+'<option>Parallel</option>'+'</select>'+'</div>'),
			$('<div/>', {
				'class':'aPlus',
				text: '+'
			})
			.append(
				$('<div/>', {
					'class': 'menu'
				})
				.append(
					$('<div/>', {
						click: createNewLevel,
						text: 'Creat a new level',
						'class': 'menus'
					}),
					$('<div/>', {
						text: 'Insert a service',
						click: collectService,
						'class': 'menus'
					}),
					$('<div/>', {
						text: 'Parallel Recommend',
						click: parallelRecommend,
						'class': 'menus'
					})
				)
			)
		)
	)
	.insertAfter($level);
}

function parallelRecommend() {
		/*
<div class="aLevel">
	<div class="aNode">
		<h3>Start</h3>
	</div>
	<div class="aLevelMenu">
		<div class="pattern">
			<select>
				<option>Sequence</option>
				<option>Parallel</option>
			</select>
		</div>
		<div class="aPlus">
			+
			<div class="menu">
				<div>Creat a new level</div>
				<div>Insert a service</div>
				<div>Parallel Recommend</div>
			</div>
		</div>
	</div>
</div>
	 */
	var $level = $(this).parent().parent().parent().parent();
	
	$('<div/>', {
		'class': 'aNode'
	})
	.append(
		$('<h4/>', {
			text: 'Recommend services'
		}),
		$('<select/>')
		.append(
			$('<option/>', {
				text: 'Hotels'
			}),
			$('<option/>', {
				text: 'Restaurant'
			})
		)
	)
	.appendTo($level);
}

function collectService() {
	
	if( $(this).find('div').length ) {
		return null;
	}
	var $nodes = $('<div/>');
	
	$.each( selectServices , function() {
		$nodes.append(
			$('<div/>', {
				'class': 'choose',
				text: this.resourceName,
				'data-serviceid': this.resourceID,
				click: insertService
			})
		);
	});
	
	$nodes.appendTo( $(this) );
	
}//end collectService

function insertService() {
	var $level = $(this).parent().parent().parent().parent().parent().parent();
	
	var t = $(this).text();
	var id = $(this).attr('data-serviceId');
	
	$('<div/>', {
		'class': 'aNode',
		'data-serviceid': id
	})
	.append(
		$('<h3/>', {
			text: t
		})
	)
	.appendTo($level);
	
	$(this).remove();
}//end insertService

$(function() {
	$('.aPlus .menu div:first-child').click( createNewLevel );
});

function getDesignFlow() {
	/*
	 * collect all data of design flow
	 * and use ajax technique to POST
	 * to servlet
	 */
	
	/*
	{
		resourceName: ‘A’,
		from: [‘START’],
		to: [‘SELECT’],
		pattern: ‘sequence’,
		input: []
	}
	 */
	var $levels = $('div.designFlow').children();
	var designFlowData = [];
	$.each( $levels, function() {
		 var $nodes = $(this).find('.aNode');
		 //new a JSON object
		 var nodes = {};
		 var data = [];
		 
		 $.each( $nodes, function() {
		 	data.push( $(this).find('h3').text() );
		 });
		 nodes.resourceName = data;
		 
		 //from
		 var preData = [];
		 var $prevNodes = $(this).prev().find('.aNode');
		 $.each( $prevNodes, function() {
		 	preData.push( $(this).find('h3').text() );
		 });
		 nodes.from = preData;
		 
		 //to
		 var nextData = [];
		 var $nextNodes = $(this).next().find('.aNode');
		 $.each( $nextNodes, function() {
		 	nextData.push( $(this).find('h3').text() );
		 });
		 nodes.to = nextData;
		 
		 //pattern
		 var pattern = $(this).find('.aLevelMenu .pattern select').val();
		 if( pattern != undefined ) {
		 	nodes.pattern = pattern;
		 }//end if
		 
		 //input
		 nodes.input = ['departure','destination'];
		 
		 designFlowData.push( nodes );
		 
	});//end each
	alert( JSON.stringify( designFlowData ) );
	
	uploadDesignFlow( designFlowData );
}//end getDesignFlow

function uploadDesignFlow( uploadData ) {
	$.ajax({
		type: 'POST',
		url: 'http://140.121.197.106:8080/restfulService/systemFlow.do',
		data: {
				service: 'flowSetup',
				data: JSON.stringify( uploadData )
			},
		dataType: "json",
		scriptCharset: "utf-8",
		//contentType: "application/json; charset=utf-8",
		//traditional: true,
		success: function(msg) {
			alert( JSON.stringify(msg) );
		},
		error: function() {
			alert('ajax:uploadDesignFlow failed');
		}
	});//end ajax
}