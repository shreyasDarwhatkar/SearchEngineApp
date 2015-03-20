<html>

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<body>

	<table style="width: 100%">
	<tr><td>&nbsp</td></tr>
		<tr>
			<td>
				<div class="container col-md-offset-1">
					<div class="row" style="80%">
						<div id="custom-search-input">
							<div class="input-group">
								<input type="text" class="search-query form-control" style="width:100%"
									placeholder="Search" /> <span class="input-group-btn">
									<button class="btn btn-danger" type="button">
										<span class=" glyphicon glyphicon-search"></span>
									</button>
								</span>
							</div>
						</div>
					</div>
				</div>
				<hr>
			<td>
		</tr>
		<tr>
			<td>
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
										</h4>
										<br /> <span>${result.desc}</span></td>
								</tr>
							</c:forEach>
						</table>
					</div>
				</div>
			</td>
		</tr>
	</table>
</body>
</html>