<html>
<head>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
<title>Search Engine Optimization</title>


</head>
<body>

	<table style="width: 100%">
		<tr>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td><form action="startSearch" method="get">
					<div class="container col-md-offset-1">
						<div class="row" style="">
							<div id="custom-search-input">
								<div class="input-group">

									<input type="text" name="data"
										class="  search-query form-control"
										placeholder="Type Search Keyword"
										style="width: 98%; border-radius: 5px;" /><span
										class="input-group-btn"> <span
										class=" glyphicon glyphicon-search"></span>&nbsp;&nbsp;&nbsp;<input
										type="submit" class=" btn  btn-primary pull-left"
										value="Search" style="width: 100%; border-radius: 5px;"
										type="button">
									</span>
								</div>
							</div>
						</div>
					</div>
					<hr>
				</form>
			<td>
		</tr>
		<tr>
			<td><c:choose>
					<c:when test="${results == null }">
						<h3 align="center">  <font style="italic" color="#0066FF" >*No Result Found...Search Again...</font></h3>
					</c:when>
					<c:otherwise>

						<div class="panel panel-info col-md-offset-1" style="width: 84%">
							<div class="panel-heading">
								<h3 class="panel-title">Search Results</h3>
							</div>
							<div class="panel-body">
								<table style="width: 100%">
									<c:forEach items="${results}" var="result">
										<tr>
											<td><h4>
													<a href="${result.url}">${result.title}</a>
												</h4> <br /> <span>${result.desc}</span></td>
										</tr>
									</c:forEach>
								</table>
							</div>
						</div>
					</c:otherwise>
				</c:choose></td>
		</tr>
	</table>
</body>
</html>