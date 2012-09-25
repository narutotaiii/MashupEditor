/**
 * @author Aminmi
 */

var selectServices;

function overallRecommendedService() {
	
	var uploadData = getDesignFlow();
	//$('div#log').text("").text( JSON.stringify( uploadData ) );
	$.ajax({
		type: 'POST',
		url: 'http://140.121.197.106:8080/restfulService/systemFlow.do',
		data: {
				service: 'overallRecommend',
				data: JSON.stringify( uploadData )
			},
		dataType: "json",
		scriptCharset: "utf-8",
		//contentType: "application/json; charset=utf-8",
		//traditional: true,
		success: function(msg) {
			alert( JSON.stringify( msg ) );
			//$('div#log').text("").text( JSON.stringify( msg ) );
		},
		error: function() {
			alert('ajax:overallRecommendedService failed');
		}
	});//end ajax
	/*
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
	*/
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
			$('<div class="pattern">'+'<select>'+'<option>sequence</option><option>selection</option>'+'<option>parallel</option>'+'</select>'+'</div>'),
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
}//end createNewLevel

function createNewNode( $target, name, id, input, output) {
	
	//check the chose service is not duplicate.
	var $nodes = $target.find('.aNode');
	var isUnique = true;	
	$.each( $nodes, function() {
		var thisServiceId = $(this).attr('data-serviceId');
		if( thisServiceId == id ) {
			alert('Can\'t choose the service twice in a level!');
			isUnique = false;
			return false;
		}//end if
	});//end each
	
	if( !isUnique ) {
		return null;
	}//end if
	
	$('<div/>', {
		'class': 'aNode',
		'data-serviceid': id,
		'data-input': input,
		'data-output': output,
	})
	.append(
		$('<h3/>', {
			text: name
		})
	)
	.append(
		$('<div>', {
			'class': 'closeButton',
			click: removeService,
			title: 'Remove this service'
		})
	)
	.append(
		$('<div>', {
			'class': 'questionButton',
			click: seeDetail,
			title: 'See detail'
		})
	)
	.appendTo($target);
}//end createNewNode

function parallelRecommend() {
	var $level = $(this).parent().parent().parent().parent();
	$level.find('.aRecommend')
	.fadeOut( 'fast',function() {
		$(this).remove();
	});
	//alert( $level.find('.aNode').length );
	
	/*
	 * Get aNodes in the level and send request
	 * by ajax.
	 * 
	 * List the recommend for user to choose
	 */
	
	var $nodes = $level.find('.aNode');
	if( !$nodes.length ) {
		alert('You have to insert other service first!');
		return null;
	}
	var nodeList = [];
	$.each( $nodes, function() {
		var id = {};
		id.resourceID = $(this).attr('data-serviceId');
		nodeList.push( id );
	});//end each
	
	//alert( JSON.stringify( nodeList ) );
	
	
	
	var recommendService;
	$.ajax({
		type: 'POST',
		url: 'http://140.121.197.106:8080/restfulService/systemFlow.do',
		data: {
				service: 'patternRecommend',
				data: JSON.stringify( nodeList )
			},
		dataType: "json",
		scriptCharset: "utf-8",
		success: function(msg) {
			//alert( JSON.stringify(msg) );
			if( !$(msg).length ) {
				alert('No recommend!');
				return null;
			}
			var $select = $('<select/>');
			$select.append(
				$('<option/>',{
					text: 'Choose a service'
				})
			);
			
			$.each( msg, function() {
				var $option = $('<option/>',{
								text: this.resourceName,
								'data-serviceId': this.resourceID,
								'data-input': this.input,
								'data-output': this.output
							});
				$select.append( $option );
			});
			
			$select.change(function() {
				var name = $(this).find('option:selected').text();
				var id = $(this).find('option:selected').attr('data-serviceId');
				var input = $(this).find('option:selected').attr('data-input');
				var output = $(this).find('option:selected').attr('data-output');
				createNewNode( $level, name, id, input, output );
				$(this).parent().remove();
			});
			
			$('<div/>', {
				'class': 'aRecommend'
			})
			.append(
				$('<h4/>', {
					text: 'Recommend services'
				}),
				$select
			)
			.append(
				$('<div>', {
					'class': 'closeButton',
					click: removeService,
					title: 'Remove this item'
				})
			)
			.appendTo($level);
		},
		error: function() {
			alert('ajax:parallelRecommend failed');
		}
	});
}

function collectService() {
	var $services = $('.pickedService').children().find('.aService');
	//if there is no picked service, end this method
	if( !$services.length ) {
		alert('You have to choose some services.');
		return null;
	}
	//if the list exists, end this method
	var $list = $(this);
	if( $list.find('div').length ) {
		return null;
	}
	var $nodes = $('<div/>');
	
	//collectionService from .pickedService
	//and collect their ID to get input/output by ajax
	if( selectServices == null ) {
		var serviceId = [];
		$.each( $services, function() {
			var id = {};
			id.resourceID = $(this).attr('data-serviceId');
			serviceId.push( id );
		});
		//$('div#log').text("").text( JSON.stringify( serviceId ) );
		$.ajax({
			type: 'POST',
			url: 'http://140.121.197.106:8080/restfulService/systemFlow.do',
			data: {
					service: 'choose',
					resources: JSON.stringify( serviceId )
				},
			dataType: "json",
			scriptCharset: "utf-8",
			success: function(msg) {
				//alert( JSON.stringify(msg) );
				$.each( msg , function() {
					$nodes.append(
						$('<div/>', {
							'class': 'choose',
							text: this.resourceName,
							'data-serviceid': this.resourceID,
							'data-input': this.input,
							'data-output': this.output,
							click: insertService
						})
					);
				});				
				$nodes.appendTo( $list );
			},
			error: function() {
				alert('ajax:collectService failed');
			}
		});		
	}	
}//end collectService

function insertService() {
	var $level = $(this).parent().parent().parent().parent().parent().parent();
	var t = $(this).text();
	var currentId = $(this).attr('data-serviceId');	
	var input = $(this).attr('data-input');
	var output = $(this).attr('data-output');
	
	createNewNode( $level, t, currentId, input, output );
	
	//$(this).remove();
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
	
	//clean empty level
	var $levels = $('div.designFlow').children();
	$.each( $levels, function() {
		 var isEmpty = $(this).find('.aNode').length ? false : true;
		 if( isEmpty ) {
		 	$(this).remove();
		 }//end if
		 
		 var $recommend = $(this).find('.aRecommend');
		 $.each( $recommend, function() {
		 	$(this).remove();
		 });//end each
	});//end each
	
	var designFlowData = [];
	$.each( $levels, function() {
		var $nodes = $(this).find('.aNode');
		$.each( $nodes, function() {
			//new a JSON object
			var nodes = {};
			nodes.resourceName = $(this).find('h3').text();
			
			//id
			var id = $(this).attr('data-serviceId');
			if( id != undefined ) {
				nodes.resourceID = id;
			}
			else {
				nodes.resourceID = "";
			}
			//data.push( $(this).find('h3').text() );
			
			//from
			var preData = [];
			var $prevNodes = $(this).parent().prev().find('.aNode');
			$.each( $prevNodes, function() {
				preData.push( $(this).find('h3').text() );
			});
			nodes.from = preData;
			
			//to
			var nextData = [];
			var $nextNodes = $(this).parent().next().find('.aNode');
			$.each( $nextNodes, function() {
				nextData.push( $(this).find('h3').text() );
			});
			nodes.to = nextData;
			
			//pattern
			var pattern = $(this).parent().find('.aLevelMenu .pattern select').val();
			if( pattern != undefined ) {
				nodes.pattern = pattern;
			}
			else {
				nodes.pattern = "";
			}//end else if
			
			//input
			
			var data = $(this).attr('data-input');
			var input = [];
			if( data != undefined ) {
				input = data.split(",");
			}
			nodes.input = input;
					
			designFlowData.push( nodes );
		});		 
	});//end each
	//alert( JSON.stringify( designFlowData ) );
	//$('div#log').text("").text( JSON.stringify( designFlowData ) );
	return designFlowData;
}//end getDesignFlow

function uploadDesignFlow() {
	var uploadData = getDesignFlow();
	alert( JSON.stringify( uploadData ) );
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
			var $button = $('.designFlowMenu .aButton').last();
			$button.text('');
			var $link = $( '<a>', {
					href: msg.url,
					text: 'Download the file',
					download: "XMLDocument.xml"
			});
			$button.append( $link );
			$button.attr('onclick', '');
			//$('div#log').text("").text( JSON.stringify( msg ) );
		},
		error: function() {
			alert('ajax:uploadDesignFlow failed');
		}
	});//end ajax
}//end uploadDesignFlow

function removeService() {
	$(this)
	.parent()
	.fadeOut( 'slow',
		function() {
			$(this).remove();
		}
	);
}//end removeService

function seeDetail() {
	var $node = $(this).parent();
	var detail = "";
	detail += $node.attr('data-serviceId') + "\n";
	detail += $node.attr('data-input') + "\n";
	detail += $node.attr('data-output') + "\n";
	alert( detail );
}
