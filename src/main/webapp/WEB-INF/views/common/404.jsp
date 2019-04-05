<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
		 pageEncoding="ISO-8859-1" %>


<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Insert title here</title>

</head>
<body data-spy="scroll" data-target=".bs-docs-sidebar">
<header>
	<!-- Navbar
	================================================== -->
	<div class="navbar navbar-fixed-top">
		<div class="navbar-inner">
			<div class="container">
				<!-- logo -->
				<a class="brand logo" href="/home"><img src="/img/logo.png" alt=""
				/></a>
				<!-- end logo -->
				<!-- top menu -->
				<div class="navigation">
					<nav>
						<ul class="nav topnav">
							<li class="dropdown">
								<a href="/home">Home</a>
							</li>
							<li class="dropdown active">
								<a href="/error">404</a>
							</li>
							<li class="dropdown ">
								<a href="/logout">Logout</a>
							</li>
						</ul>
					</nav>
				</div>
				<!-- end menu -->
			</div>
		</div>
	</div>
</header>
<!-- Subhead
================================================== -->
<section id="subintro">
	<div class="jumbotron subhead" id="overview">
		<div class="container">
			<div class="row">
				<div class="span12">
					<div class="centered">
						<h3 >404</h3>

						<p>
							The page you are looking for might have been removed, had its name changed, or is temporarily unavailable.
						</p>
					</div>
				</div>
			</div>
		</div>
	</div>
</section>
<section id="maincontent">
	<div class="container">
		<div class="row">
			<div class="span12">
				<div class="centered">
					<h2 class="error">404</h2>
					<h3>Sorry, that page doesn't exist!</h3>
					<p>
						The page you are looking for might have been removed, had its name changed, or is temporarily unavailable.
					</p>
				</div>
			</div>
		</div>
	</div>
</section>
<!-- Footer
================================================== -->




</body>
</html>