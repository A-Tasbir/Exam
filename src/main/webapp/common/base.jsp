<%-- 共通テンプレート --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html lang="ja">

<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- Bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65"
	crossorigin="anonymous">

<title>${param.title}</title>

<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>

${param.scripts}

<style>

/* =========================
   LIGHT THEME
========================= */

:root {
	--bg-color: #ffffff;
	--text-color: #212529;
	--card-color: #ffffff;
	--header-color: #dbe9ff;
	--nav-color: #f8f9fa;
	--footer-color: #e9ecef;
	--border-color: #ced4da;
}

/* =========================
   DARK THEME
========================= */

.dark-mode {
	--bg-color: #121212;
	--text-color: #f1f1f1;
	--card-color: #1e1e1e;
	--header-color: #1f2a38;
	--nav-color: #1a1a1a;
	--footer-color: #1a1a1a;
	--border-color: #444444;
}

/* =========================
   GLOBAL
========================= */

body {
	background-color: var(--bg-color);
	color: var(--text-color);
	transition: all 0.3s ease;
}

#wrapper {
	background-color: var(--bg-color);
	color: var(--text-color);
}

/* =========================
   HEADER
========================= */

header {
	background-color: var(--header-color) !important;
	color: var(--text-color);
}

/* =========================
   NAVIGATION
========================= */

nav {
	background-color: var(--nav-color);
	color: var(--text-color);
}

/* =========================
   MAIN
========================= */

main {
	background-color: var(--bg-color);
	color: var(--text-color);
	min-height: 40rem;
	padding: 1rem;
}

/* =========================
   FOOTER
========================= */

footer {
	background-color: var(--footer-color) !important;
	color: var(--text-color);
}

/* =========================
   TEXT
========================= */

h1, h2, h3, h4, h5, h6,
p, span, div, label,
td, th, li {
	color: var(--text-color) !important;
}

/* =========================
   TABLES
========================= */

table,
.table {
	color: var(--text-color) !important;
}

/* =========================
   FORMS
========================= */

input,
select,
textarea {
	background-color: var(--card-color) !important;
	color: var(--text-color) !important;
	border: 1px solid var(--border-color) !important;
}

/* =========================
   LINKS
========================= */

a {
	color: #4da3ff !important;
	text-decoration: none;
	position: relative;
	font-weight: 500;
	transition:
		color 0.2s ease,
		text-shadow 0.2s ease;
}

/* Hover */

a:hover {
	color: #8ec5ff !important;
	text-shadow: 0 0 8px rgba(120, 180, 255, 0.7);
}

/* Underline animation */

a::after {
	content: "";
	position: absolute;
	left: 0;
	bottom: -2px;
	width: 0%;
	height: 2px;
	background-color: currentColor;
	transition: width 0.2s ease;
}

a:hover::after {
	width: 100%;
}

/* Visited */

a:visited {
	color: #7ab8ff !important;
}

/* =========================
   DARK MODE LINKS
========================= */

.dark-mode a {
	color: #6cb6ff !important;
}

.dark-mode a:hover {
	color: #a8d4ff !important;
	text-shadow: 0 0 10px rgba(120, 180, 255, 0.8);
}

.dark-mode a:visited {
	color: #8fc7ff !important;
}

/* =========================
   BUTTONS
========================= */

.btn-outline-dark {
	border-color: var(--text-color) !important;
	color: var(--text-color) !important;
}

.btn-outline-dark:hover {
	background-color: var(--text-color) !important;
	color: var(--bg-color) !important;
}

/* =========================
   BORDERS
========================= */

.border,
.border-top,
.border-bottom,
.border-start {
	border-color: var(--border-color) !important;
}

</style>

</head>

<body>

	<div id="wrapper" class="container">

		<header
			class="d-flex flex-wrap justify-content-center py-3 px-5 mb-4 border-bottom border-2 bg-primary bg-opacity-10 bg-gradient">

			<c:import url="/common/header.jsp" />

		</header>

		<div class="row justify-content-center">

			<c:choose>

				<%-- ログイン済みの場合 --%>
				<c:when test="${user.isAuthenticated()}">

					<nav class="col-3" style="height:40rem;">

						<c:import url="/common/navigation.jsp" />

					</nav>

					<main class="col-9 border-start">

						${param.content}

					</main>

				</c:when>

				<%-- 未ログインの場合 --%>
				<c:otherwise>

					<main class="col-8">

						${param.content}

					</main>

				</c:otherwise>

			</c:choose>

		</div>

		<footer class="py-2 my-4 bg-dark bg-opacity-10 border-top border-3 align-bottom">

			<c:import url="/common/footer.jsp" />

		</footer>

	</div>

	<script>

	document.addEventListener("DOMContentLoaded", function () {

		const toggleBtn = document.getElementById("themeToggle");

		if (!toggleBtn) {
			return;
		}

		const savedTheme = localStorage.getItem("theme");

		if (savedTheme === "dark") {

			document.body.classList.add("dark-mode");

			toggleBtn.innerHTML = "☀️";

		}

		toggleBtn.addEventListener("click", function () {

			document.body.classList.toggle("dark-mode");

			if (document.body.classList.contains("dark-mode")) {

				localStorage.setItem("theme", "dark");

				toggleBtn.innerHTML = "☀️";

			} else {

				localStorage.setItem("theme", "light");

				toggleBtn.innerHTML = "🌙";

			}

		});

	});

	</script>

</body>

</html>