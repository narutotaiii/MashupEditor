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
				click: insertService
			})
		);
	});
	
	$nodes.appendTo( $(this) );
	
}//end collectService

function insertService() {
	var $level = $(this).parent().parent().parent().parent().parent().parent();
	
	var t = $(this).text();
	
	$('<div/>', {
		'class': 'aNode'
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
