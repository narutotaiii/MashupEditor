<script src="js/jquery.ui.autocomplete.js" type="text/javascript"></script>
<script type="text/javascript">
// AutoComplete
$(document).ready(function() {
	// Autocomplete
	$("input#keyword").attr('autocomplete','off');
	$("input#keyword").autocomplete({
		source: "AutoComplete.do"
	});
});
</script>
<script src="js/jquery.rating.js" type="text/javascript"></script>
<link rel="stylesheet" href="css/jquery.rating.css" type="text/css" />
<style type="text/css">
	.ui-autocomplete {
	font-size: 12px;
	max-height: 200px;
	overflow-y: auto;
}
</style>
<div id="advancedSearchWindow">
	<p id="handle">進階搜尋</p>
	<form action="advanced_search.jsp">
		<label>關鍵字串: </label>
		<input type="text" size="24" value="" id="keyword" name="keyword" ></input><br />
		<label>電影類型: </label>
		<select id="advancedSearchMovieType" name="type1" style="width: 77px;">
			<option value="">不指定</option>
			<option value="劇情片">劇情片</option>
			<option value="浪漫愛情">浪漫愛情</option>
			<option value="動作冒險">動作冒險</option>
			<option value="喜劇搞笑">喜劇搞笑</option>
			<option value="恐怖驚悚">恐怖驚悚</option>
			<option value="奇想科幻">奇想科幻</option>
			<option value="歷史戰爭">歷史戰爭</option>
			<option value="傳記">傳記</option>
			<option value="音樂歌舞">音樂歌舞</option>
			<option value="動畫短片">動畫短片</option>
			<option value="紀錄片">紀錄片</option>
			<option value="影集">影集</option>
			<option value="電視電影">電視電影</option>
			<option value="其他">其他</option>
		</select>
		<a href="#" id="aswAddType">[+]</a><br />
		<label>台灣分級: </label>
		<select id="advancedSearchLevel" name="level">
			<option value="">不指定</option>
			<option value="普遍級">普遍級</option>
			<option value="輔導級">輔導級</option>
			<option value="保護級">保護級</option>
			<option value="限制級">限制級</option>
		</select><br />
		<label>電影國別: </label>
		<input type="text" size="24" value="" id="advancedSearchNation" name="nation"></input>
		<br />
		<label>演員、導演、編劇: </label>
		<input type="text" size="24" value="" id="advancedSearchCast" name="character1"></input>
		<a href="#" id="aswAddChar">[+]</a><br />
		<label style="float: left;">電影評分: </label>
		<input id="score" name="score" type="radio" class="star" style="float: left;" value="1" />
		<input id="score" name="score" type="radio" class="star" style="float: left;" value="2" />
		<input id="score" name="score" type="radio" class="star" style="float: left;" value="3" />
		<input id="score" name="score" type="radio" class="star" style="float: left;" value="4" />
		<input id="score" name="score" type="radio" class="star" style="float: left;" value="5" />
		<br />
		<br />
		<input id="advancedSearchSubmit" style="margin-left: 10px; margin-left: 30%;" class="formbutton" value="搜尋" type="submit" />
		<input id="closeHandle" style="margin-left: 10px;" class="formbutton" value="關閉" type="submit" />
	</form>
</div>