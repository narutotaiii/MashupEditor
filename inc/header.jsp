<%@page info="Release Date Table"
		contentType="text/html;charset=utf8" 
		errorPage="errorPage.jsp"
		
%>


	<div id="header">
    	<h1><a href="index.jsp">影<strong>JOY</strong></a></h1>
        <h2>Enjoy Movie! 享受電影</h2>
		
        <div class="clear"></div>
		<span>
			<form>
				<input id="openHandle" style=" float: right;" type="submit" class="formbutton" value="進階搜尋" />
			</form>
			<form method="get" class="searchform" action="simple_search.jsp" >
				<input id="search" style=" float: right;" type="submit" class="formbutton" value="搜尋" />
				<input id="keyword" style=" float: right;" name="keyword" type="text" size="24" value="" class="s" />
			</form>
		</span>
    </div>
	
	<c:choose>
		<c:when test="${access_token != null}">
			<jsp:include page="/inc/nav_logged.inc" />
		</c:when>
		<c:otherwise>
			<jsp:include page="/inc/nav.inc" />
		</c:otherwise>
	</c:choose>
	