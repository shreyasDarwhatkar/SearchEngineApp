<html>

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<body>

	<table style="width: 100%">
		<tr>
			<td>
				<div class="container">
					<div class="row">
						<div id="custom-search-input">
							<div class="input-group col-md-16">
								<input type="text" class="  search-query form-control"
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
				<h3>Search Results</h3>
				<table style="width: 100%">
					<c:forEach items="${results}" var="result">
						<tr>
							<td><h5><a href="${result.url}">${result.title}</a></h5><br/>
							<span>${result.desc}</span>
							</td>
						</tr>
					</c:forEach>
				</table>
			</td>
		</tr>
	</table>
</body>
</html>