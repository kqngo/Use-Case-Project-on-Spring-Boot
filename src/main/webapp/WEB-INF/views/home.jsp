<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="taglib.jsp"%>

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">
<meta http-equiv="Content-Language" content="en_US">
<meta http-equiv="x-ua-compatible" content="ie=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="https://js-agent.newrelic.com/nr-spa-1123.min.js"></script>
<script async="" src="//www.google-analytics.com/analytics.js"></script>
<script type="text/javascript" async=""
	src="https://www.gstatic.com/recaptcha/api2/v1563777128698/recaptcha__en.js"></script>
<title>Food Waste : ReFED | Rethink Food Waste</title>

<%@ include file="links.jsp"%>
<style>
.parallax {
	/* The image used */
	background-image: url("img_parallax.jpg");
	/* Set a specific height */
	height: 500px;
	/* Create the parallax scrolling effect */
	background-attachment: fixed;
	background-position: center;
	background-repeat: no-repeat;
	background-size: cover;
}

.jumbotron {
	position: relative;
	overflow: hidden;
	height: 250px;
}

.container {
	position: relative;
	color: #ffffff;
	z-index: 2; /* Show content above video */
}

#video-background {
	position: absolute;
	height: auto;
	width: auto;
	min-height: 100%;
	min-width: 100%;
	left: 50%;
	top: 50%;
	-webkit-transform: translate3d(-50%, -50%, 0);
	transform: translate3d(-50%, -50%, 0);
	z-index: 1;
}


</style>
</head>

<body>

	<div class="jumbotron">
		<video id="video-background" preload muted autoplay loop>
			<source src="<spring:url value="/resources/images/backyard.MOV"/> "
				type="video/mp4">
		</video>
		<div class="container">
			<div class="col-md-10 col-md-offset-1" data-form-type="formoid">
				<h2 class="styled">Reduce Waste | Promote Healthy Living</h2>
				<p>Join us to reduce food waste and improve access to healthy
					food to our local communities.</p>
			</div>
		</div>
	</div>



	<%@ include file="navBar.jsp"%>



	<!-- Citations -->
	<div class="card bg-transparent">
		<div class="card-columns">
			<div class="card">
				<img class="card-img-top"
					src="https://i.guim.co.uk/img/media/8b1da3526e526e14393ab3863aa8152d020737a1/0_172_5696_3419/master/5696.jpg?width=620&quality=45&auto=format&fit=max&dpr=2&s=5eddc29938814fb0ba0dbc535a678ee2"
					alt="Disgarded veggies">

				<div class="blockquote card-body">
					<p class="card-text">Growing food that goes to waste currently
						uses 21% of our freshwater, 19% of our fertilizer, and 18% of our
						cropland.</p>

					<footer class="blockquote-footer">
						<small class="text-muted"> <a
							href="https://www.refed.com/?sort=economic-value-per-ton">ReFED</a>
						</small>
					</footer>

				</div>
			</div>
			<div class="card">
				<blockquote class="blockquote card-body">
					<p>Sixty (60) million tons of produce worth about $160 billion
						is wasted by retailers and consumers every year</p>

					<footer class="blockquote-footer">
						<small class="text-muted"> <a
							href="https://www.theguardian.com/environment/2016/jul/13/us-food-waste-ugly-fruit-vegetables-perfect">The
								Guardian</a>
						</small>
					</footer>
				</blockquote>
			</div>

			<div class="card text-center">
				<blockquote class="blockquote">
					<p>According to the U.S. Department of Agriculture’s Food Loss
						Project, we throw away more than 25 percent—some 25.9 million
						tons—of all the food we produce for domestic sale and consumption.</p>
					<footer class="blockquote-footer">
						<small> <a
							href="https://www.scientificamerican.com/article/earth-talk-waste-land">
								Scientific American </a>
						</small>
					</footer>
				</blockquote>
			</div>


			<div class="card  text-right">
				<blockquote class="blockquote">
					<p>Once this food gets to the landfill, it then generates
						methane, a greenhouse gas 23 times as potent as carbon dioxide in
						trapping heat within our atmosphere.</p>
					<footer class="blockquote-footer">
						<small class="text-muted"><a
							href="https://www.scientificamerican.com/article/earth-talk-waste-lan">
								Scientific American </a> </small>
					</footer>
				</blockquote>
			</div>

			<div class="card  text-right">
				<blockquote class="blockquote ">
					<p>According to the U.S. Environmental Protection Agency,
						landfills account for 34 percent of all methane emissions in the
						U.S.</p>
					<footer class="blockquote-footer">
						<small class="text-muted"> <a
							href="https://www.scientificamerican.com/article/earth-talk-waste-land">
								Scientific American </a>
						</small>
					</footer>
				</blockquote>
			</div>

			<div class="card">
				<img class="card-img-top"
					src="https://proxy.duckduckgo.com/iu/?u=https%3A%2F%2Fcleanmalaysia.com%2Fwp-content%2Fuploads%2F2017%2F07%2FPilot-scheme-shows-promise-in-repurposing-commercial-food-wastes-790x422.jpg&f=1"
					alt="Card image cap">
				<div class="card-body">
					<p class="card-text">When food ends up in the landfill, it
						produces methane, a greenhouse gas 23 times as potent as CO2.</p>
					<p class="card-text">
					<footer class="blockquote-footer">

						<small class="text-muted"> <a
							href="https://www.scientificamerican.com/article/earth-talk-waste-land">Scientific
								American</a>

						</small>
					</footer>

					</p>
				</div>
			</div>
			<div class="card">
				<div class="card-body">
					<p class="card-text">Reducing food waste is one of the top
						three ways to reverse global warming.</p>
					<p class="card-text">
					<footer class="blockquote-footer">
						<small class="text-muted"> <a
							href="https://www.drawdown.org/solutions-summary-by-rank">Project
								Drawdown</a>
						</small>
					</footer>
					</p>
				</div>
			</div>
		</div>
	</div>


	<!-- Testimony -->
	<div class="card bg-info">
		<section
			class="mbr-slider mbr-section mbr-section__container carousel slide testimonials mbr-section-nopadding mbr-parallax-background"
			data-ride="carousel" data-keyboard="false" data-wrap="true"
			data-interval="4000" id="testimonials2-2a" data-rv-view="110"
			style="background-image: none; position: relative; background-attachment: scroll; background-size: auto;">

			<div id="jarallax-container-2"
				style="position: absolute; top: 0px; left: 0px; width: 100%; height: 100%; overflow: hidden; pointer-events: none; transition: transform 0s linear -1ms, -webkit-transform 0s linear -1ms; visibility: hidden; z-index: -100;">
				<div
					style="background-position: 50% 120%; background-repeat: no-repeat; background-image: url(&amp;quot;https://www.webnots.com/bootstrap/assets-demo/images/testimonials2.jpg&amp;quot;); position: fixed; top: 0px; left: 0px; width: 5468px; height: 1836px; overflow: hidden; pointer-events: none; transition: transform 0s linear -1ms, -webkit-transform 0s linear -1ms; background-size: 5468px 3645.33px; visibility: visible; transform: translate3d(0px, 144.46px, 0px);">
				</div>
			</div>

			<div class=" container boxed-slider"
				style="padding-top: 50px; padding-bottom: 50px;">
				<div>
					<div>
						<ol class="carousel-indicators">
							<li data-app-prevent-settings="" data-target="#testimonials2-2a"
								data-slide-to="0" class=""></li>
							<li data-app-prevent-settings="" data-target="#testimonials2-2a"
								data-slide-to="1" class=""></li>
							<li data-app-prevent-settings="" data-target="#testimonials2-2a"
								class="active" data-slide-to="2"></li>
						</ol>
						<div class="carousel-inner" role="listbox">
							<div
								class="mbr-section mbr-section-hero carousel-item dark center">
								<div class="mbr-table-cell">
									<div class="container-slide">
										<div class="row">
											<div class="col-md-offset-1 col-md-10 col-xs-12 page_head">
												<blockquote>
													<p>Our family struggles to fit fruits and veggies into
														our budget. This service will help us to provide healthy
														options to our growing teenagers.</p>
												</blockquote>
												<h5 class="author">Quynh Pham</h5>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div
								class="mbr-section mbr-section-hero carousel-item dark center">
								<div class="mbr-table-cell">

									<div class="container-slide">
										<div class="row">
											<div class="col-md-offset-1 col-md-10 col-xs-12 page_head">
												<blockquote>
													<p>I can get my grocery at a discount while helping to
														reduce food waste ? Sign me up!</p>
												</blockquote>
												<h5 class="author">Kasidit Bualoeng</h5>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div
								class="mbr-section mbr-section-hero carousel-item dark center active"">
								<div class="mbr-table-cell">

									<div class="container-slide">
										<div class="row">
											<div class="col-md-offset-1 col-md-10 col-xs-12 page_head">
												<blockquote>
													<p>This is a great concept. I can't wait to be able to
														place my first Order.</p>
												</blockquote>
												<h5 class="author">Karma Tsering</h5>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- End carousel container -->

		</section>
	</div>

	<%@ include file="footer.jsp"%>
</body>

</html>
