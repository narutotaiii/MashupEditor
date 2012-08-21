// 設定
var mw_site = "http://zh.wikipedia.org/";
var mw_api = mw_site+"w/api.php";
var mw_article_base = mw_site+"zh-tw/";
var mw_source = "出處:自由的百科全書『維基百科』";
var mw_copyright = "available under the GNU Free Documentation License";
var mw_hover_backgroundColor = "#dfdfdf";
var mw_hover_color = "#000";

// 全域變數
var mw_summaries = {};
var mw_callbacks = {};
var mw_count = 0;

// 對API送出請求
function mw_sendRequest(keyword, callback){
	mw_count++;
	var request = mw_api+"?action=parse&prop=text&format=json&callback=mw_callbacks.f_" + mw_count + "&page=" + encodeURIComponent(keyword);
	var script = document.createElement("script");
	mw_callbacks["f_" + mw_count] = function(response){
		document.body.removeChild(script);
		try {
			var str = !response.failed ? response.parse.text["*"] : '<p>(摘要資料取得失敗)</p>';
		}
		catch(err) {
			var str = '<p>(摘要資料取得失敗)</p>';
		}
		callback(str);
	};
	script.setAttribute("type", "text/javascript");
	document.body.appendChild(script);
	script.src = request;
}

// 將從API取得的資料原封不動地顯示
function mw_showResponse(keyword){
	mw_sendRequest(keyword, function(str){
		alert(str);
	});
}

// 接收Wiki的文章文字，傳回摘要
function mw_getSummary(content){
	content = content.replace(/\<img\ .*?\>|\<a\ .*?\>|<\/a\>/g, "");
	var summary = "";
	var elm = document.createElement("div");
	elm.innerHTML = content;
	for (var i=0; i<elm.childNodes.length; i++) {
		var node = elm.childNodes[i];
		if ((node.textContent || node.innerHTML || "").replace(/\s+/g, "") == "")
			continue;
		if (node.tagName == "P" || node.tagName == "UL" || node.tagName == "OL")
			summary += "<"+node.tagName+">"+node.innerHTML+"</"+node.tagName+">";
		if (node.tagName == "H1" || node.tagName == "H2")
			break;
	}
	if (summary == "")
		summary = "(沒有摘要)";
	return summary;
}

// 顯示從API取得的資料摘要
function mw_showSummary(keyword){
	mw_sendRequest(keyword, function(str){
		alert(mw_getSummary(str));
	});
}

// 傳回元素的絕對座標
function mw_getAbsolutePos(elm){
	var pos = {x:0, y:0};
	while (elm && elm.nodeName != "BODY") {
		pos.x += elm.offsetLeft;
		pos.y += elm.offsetTop;
		elm = elm.offsetParent;
	}
	return pos;
}

// 顯示提示文字的準備
function mw_showTip(target){
	var keyword = target.innerHTML.replace(/\<.+?\>/g,"");
	if (mw_summaries[keyword]) {
		mw_showTipMain(target, keyword);
		return;
	}
	target.style.backgroundImage = "url(css/loading.gif)";
	target.style.backgroundColor = mw_hover_backgroundColor;
	target.style.color = mw_hover_color;
	
	mw_sendRequest(keyword, function(str){
		mw_summaries[keyword] = mw_getSummary(str);
		mw_showTipMain(target, keyword);
	});
}

// 顯示提示文字
function mw_showTipMain(target, keyword){
	var div = document.createElement("div");
	var pos = mw_getAbsolutePos(target);
	div.innerHTML = "<h6>"+keyword+"</h6>"
		+mw_summaries[keyword]
		+"<p align=\"center\"><a href=\""+mw_article_base+keyword+"\" target=\"_blank\">"+mw_source+"</a></p>"
		+"<div>"+mw_copyright+"</div>";
	div.className = "mw_tip";
	div.style.position = "absolute";
	div.style.left = (pos.x-3)+"px";
	div.style.top = (pos.y-1)+"px";
	div.onclick = function(){ document.body.removeChild(this) };
	document.body.appendChild(div);
	
	target.style.backgroundImage = "";
	target.style.backgroundColor = "";
	target.style.color = "";
}


/* Copyright 2009 CogniTom Academic Design & Tsutomu Kawamura
 * http://www.cognitom.com/
 * office@cognitom.com
 * ------------------------------------------------------------------
 *
 * MediaWiki Tips
 *
 * - version 1.0.0
 * - release 2009.6.8
 * 
 * ------------------------------------------------------------------
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */