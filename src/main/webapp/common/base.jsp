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
   Bootstrapボタンの色を元に戻す
   （グローバルa{}の上書きを防ぐ）
========================= */

/* =========================
   Bootstrapボタン（ライトモード）
   ※背景色と文字色をBootstrap標準に戻す
========================= */

/* --- Primary --- */
.btn-primary {
    background-color: #0d6efd !important; /* 標準ブルー */
    border-color: #0d6efd !important;
    color: #ffffff !important; /* 白文字 */
}

.btn-primary:hover,
.btn-primary:active,
.btn-primary:focus {
    background-color: #0b5ed7 !important; /* 標準ホバー色 */
    border-color: #0a58ca !important;
    color: #ffffff !important;
}

/* --- Secondary --- */
.btn-secondary {
    background-color: #6c757d !important; /* 標準グレー */
    border-color: #6c757d !important;
    color: #ffffff !important;
}

.btn-secondary:hover,
.btn-secondary:active,
.btn-secondary:focus {
    background-color: #5c636a !important; /* 標準ホバー色 */
    border-color: #565e64 !important;
    color: #ffffff !important;
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

input:not(.btn):not([type="checkbox"]):not([type="radio"]),
select,
textarea {
    background-color: var(--card-color) !important;
    color: var(--text-color) !important;
    border: 1px solid var(--border-color) !important;
}

/* Keep Bootstrap checkbox style */

.form-check-input {
	accent-color: #0d6efd;
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
	color: #0a58ca !important;
	
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
   サイドバー内リンク（ライトモード）
   ※ライトモードでは青色リンクに統一
========================= */

nav a {
    color: #0d6efd !important; /* Bootstrap標準の青 */
}

nav a:hover {
    color: #0a58ca !important; /* ホバー時の濃い青 */
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

/* =========================
   MENU CARDS
========================= */

/* =========================
   メニューカード（ライトモード）
   ※ライトモードで背景色が薄く見えにくかったため改善
========================= */

.student-card{
    background-color:#f4c7c3; /* 学生管理カード：淡い赤で視認性UP */
}

.score-card{
    background-color:#c8e6c9; /* 成績管理カード：淡い緑で視認性UP */
}

.class-card{
    background-color:#fff3c4; /* クラス管理カード：淡い黄色で視認性UP */
}

.subject-card{
    background-color:#c5d8ff; /* 科目管理カード：淡い青で視認性UP */
}

.teacher-card{
    background-color:#e0e0e0; /* 先生管理カード：明るいグレーで視認性UP */
}

.attendance-card{
    background-color:#d0f0f0; /* 出欠管理カード：淡い水色で視認性UP */
}

/* ライトモード時の文字色（濃い文字で読みやすくする） */
.menu-card a {
    color: #0d6efd !important; /* Bootstrap標準の青 */
}

/* ホバー時（ライトモード） */
.menu-card a:hover {
    color: #0a58ca !important; /* 少し濃い青 */
}


/* ---------- DARK ---------- */

.dark-mode .menu-card{
	border:1px solid #555;
}

.dark-mode .student-card{
	background-color:#5c3d3d;
}

.dark-mode .score-card{
	background-color:#3d5c3d;
}

.dark-mode .class-card{
	background-color:#5c5c3d;
}

.dark-mode .subject-card{
	background-color:#3d3d5c;
}

.dark-mode .teacher-card{
	background-color:#555;
}

.dark-mode .attendance-card{
	background-color:#2f4f4f;
}
.dark-mode .alert-success {
    background-color: #1f3b2e !important;
    color: #b6f3d0 !important;
    border-color: #2e5a43 !important;
}

.dark-mode .alert-danger {
    background-color: #3b1f1f !important;
    color: #f3b6b6 !important;
    border-color: #5a2e2e !important;
}

.dark-mode .alert-warning {
    background-color: #4a3a1f !important;
    color: #ffe9b3 !important;
    border-color: #6b5328 !important;
}

.dark-mode .alert-info {
    background-color: #1f3a4a !important;
    color: #b3e5ff !important;
    border-color: #28536b !important;
}

/* =========================
   メニューカード内リンク（ダークモード）
   ※ホバー時も青色を維持して視認性UP
========================= */

.dark-mode .menu-card a {
    color: #6cb6ff !important; /* 通常時：青 */
}

.dark-mode .menu-card a:hover {
    color: #9fd0ff !important; /* ホバー時：明るい青 */
    text-shadow: 0 0 6px rgba(159, 208, 255, 0.6);
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

			document.documentElement.classList.add("dark-mode");

			toggleBtn.innerHTML = "☀️";

		}

		toggleBtn.addEventListener("click", function () {

			document.documentElement.classList.toggle("dark-mode");

			if (document.documentElement.classList.contains("dark-mode")) {

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