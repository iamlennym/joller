
<%@ page import="joller.Mens" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'mens.label', default: 'Mens')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-mens" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-mens" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="naam" title="${message(code: 'mens.naam.label', default: 'Naam')}" />
					
						<g:sortableColumn property="geslag" title="${message(code: 'mens.geslag.label', default: 'Geslag')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${mensInstanceList}" status="i" var="mensInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${mensInstance.id}">${fieldValue(bean: mensInstance, field: "naam")}</g:link></td>
					
						<td>${fieldValue(bean: mensInstance, field: "geslag")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${mensInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
