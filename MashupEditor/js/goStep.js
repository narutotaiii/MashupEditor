/**
 * @author Aminmi
 */

var step = 1;

function goNextStep() {
	if( step >= 7 ) {
		return null;
	}//end if
	
	var cur = $("div[data-step='" + step + "']");
	step++;
	var next = $("div[data-step='"+ step + "']");
	
	if( step == 2 ) {
		createTabs();
	}//end if
	
	cur.fadeToggle("fast", function() {
		next.fadeToggle();	
	});	
}//end goNextStep

function goBackStep() {
	if( step <= 1 ) {
		return null;
	}//end if
	
	var cur = $("div[data-step='" + step + "']");
	step--;
	var next = $("div[data-step='"+ step + "']");
	
	cur.fadeToggle("fast", function() {
		next.fadeToggle();	
	});
	
}//end goBackStep
