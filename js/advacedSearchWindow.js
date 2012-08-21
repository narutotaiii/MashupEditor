$(document).ready(function() {
	// 進階搜尋視窗的屬性
	var asw = $("#advancedSearchWindow");
	var handleHeight = asw.height(); 
	var handleWidth = asw.width();
	var originOffsetRight = $(window).width()/2;
	var centerY = $(window).height()/2 - handleHeight/2;
	// 設定預設為不顯示及縮在網頁上方
	asw.hide().css({"left": originOffsetRight - handleWidth/2, "top": -10-handleHeight, opacity: 0});
	
	// 使進階搜尋視窗可移動
	asw.draggable({
		handle: '#handle',
		zIndex: 1000
	});
	$("#handle").disableSelection();

	// 進階搜尋視窗的開關按鈕設定
	$("#openHandle").click( function() {
		if ( asw.is(":hidden") )
			asw.show().animate({ "top": centerY, opacity: 100 }, 500);
		else if ( !(asw.is(":hidden")) )
			asw.animate({ "top": -10-handleHeight, "left": originOffsetRight - handleWidth/2, opacity: 0 }, 500).hide(500);
		return false;
	});
	$("#closeHandle").click( function(){
		asw.animate({ "top": -10-handleHeight, "left": originOffsetRight - handleWidth/2, opacity: 0 }, 500).hide(500);
		return false;
	});
	
	// 當瀏覽器視窗大小更動後，重新設定進階搜尋視窗的屬性
	var resizeWindow = function() {
		originOffsetRight = $(window).width()/2;
		centerY = $(window).height()/2 - handleHeight/2;
		if ( !(asw.is(":hidden")) ) {
			var docY = $(document).
			asw.show().animate({ "top": centerY, "left": originOffsetRight - handleWidth/2, opacity: 100 });
			//var t = setTimeout("aswMove()", 500);
		}
	};

	window.onresize = resizeWindow;
	
	function getTypeSelectHtml( count ){
		var typeSelect = 
			" <select style=\"width: 77px;\" id=\"advancedSearchMovieType\" name=\"type" + count + "\">\n" +
			" <option value=\"\">不指定</option>\n" +
			" <option value=\"劇情片\">劇情片</option>\n" +
			" <option value=\"浪漫愛情\">浪漫愛情</option>\n" +
			" <option value=\"動作冒險\">動作冒險</option>\n" +
			" <option value=\"喜劇搞笑\">喜劇搞笑</option>\n" +
			" <option value=\"恐怖驚悚\">恐怖驚悚</option>\n" +
			" <option value=\"奇想科幻\">奇想科幻</option>\n" +
			" <option value=\"歷史戰爭\">歷史戰爭</option>\n" +
			" <option value=\"傳記\">傳記</option>\n" +
			" <option value=\"音樂歌舞\">音樂歌舞</option>\n" +
			" <option value=\"動畫短片\">動畫短片</option>\n" +
			" <option value=\"紀錄片\">紀錄片</option>\n" +
			" <option value=\"影集\">影集</option>\n" +
			" <option value=\"電視電影\">電視電影</option>\n" +
			" <option value=\"其他\">其他</option>\n" +
			" </select>\n";

		return typeSelect;
	}
	function getCharInputHtml( count ) {
		var charInput= "<input type=\"text\" size=\"7\" value=\"\" name=\"character" + 
			   count +
			   "\"></input>"
		return charInput;
	}
		
	// 進階搜尋增加條件
	var aswTypeCount = 2;
	$("#aswAddType").click( function() {
		if ( aswTypeCount < 4 ) {
			$(this).before(getTypeSelectHtml(aswTypeCount));
			aswTypeCount = aswTypeCount + 1;
		}
		return false;
	});	
	
	var aswCharCount = 2;
	$("#aswAddChar").click( function() {
		if ( aswCharCount < 4 ) {
			$("#advancedSearchCast").attr('size',7);
			$(this).before(getCharInputHtml(aswCharCount));
			aswCharCount = aswCharCount + 1;
		}
		return false;
	});
	
});