<link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>"/>
<header>
    <div id="head">
        <div id="logo">
            <img src="/resources/images/${logo.logoName}"/>
        </div>
        <div id="actions">
            <c:if test="${user.companyMail != null}">
            <div id="authentication">
                <sec:authorize access="isAuthenticated()">
                    <p>Zalogowany jako:</p>
                    <p>${user.companyMail}</p>
                    <button id="contact-submit"><a href="/user/about">Panel</a></button>
                </sec:authorize>
            </div>
            </c:if>
            <c:if test="${user.companyMail == null}">
                <div id="authentication">
                <ul>
                    <li><a href="/user/about">logowanie</a></li>
                    <li><a href="/register">Rejestracja</a></li>
                </ul>
                </div>
            </c:if>
        </div>
        <div style="clear: both;"></div>
    </div>

    <div id="nav">
        <ul>
            <li><a href="/">Start</a> </li>
            <li><a href="/about-company">O nas</a></li>
            <li><a href="/our-offer">Nasza oferta</a></li>
            <li><a href="/designers">Nasi projektanci</a></li>
            <li><a href="/realisations">Realizacje</a></li>
            <li><a href="/contact">Kontakt</a></li>
        </ul>
    </div>
</header>