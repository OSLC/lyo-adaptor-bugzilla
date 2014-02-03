<!DOCTYPE html>
<%--
 Copyright (c) 2011, 2012 IBM Corporation.

 All rights reserved. This program and the accompanying materials
 are made available under the terms of the Eclipse Public License v1.0
 and Eclipse Distribution License v. 1.0 which accompanies this distribution.
 
 The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 and the Eclipse Distribution License is available at
 http://www.eclipse.org/org/documents/edl-v10.php.
 
 Contributors:
 
    Sam Padgett	  	- initial API and implementation
    Michael Fiedler	- adapted for OSLC4J
--%>

<%@page import="org.eclipse.lyo.oslc4j.core.model.ServiceProvider"%>
<%@page import="java.util.List" %>
<%@page import="se.kth.md.it.bcm.resources.BugzillaChangeRequest"%>
<%--
Start of user code imports
--%>
<%-- 
End of user code 
--%>


<%@ page contentType="text/html" language="java" pageEncoding="UTF-8" %>

<%
	ServiceProvider serviceProvider = (ServiceProvider) request.getAttribute("serviceProvider");
%>
<%--
Start of user code getRequestAttributes
--%>
<%
	String bugzillaUri = "https://landfill.bugzilla.org/bugzilla-4.0-branch/";
%>
<%--
End of user code
--%>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
		<title>Resource Creator</title>
		<%-- 
Start of user code (RECOMMENDED) headStuff 
		--%>
		<link href="<%=bugzillaUri%>/skins/standard/global.css" rel="stylesheet" type="text/css">
		<link href="<%=bugzillaUri%>/skins/standard/index.css" rel="stylesheet" type="text/css">
		<link href="<%=bugzillaUri%>/skins/standard/global.css" rel="alternate stylesheet" title="Classic" type="text/css">
		<link href="<%=bugzillaUri%>/skins/standard/index.css" rel="alternate stylesheet" title="Classic" type="text/css">
		<link href="<%=bugzillaUri%>/skins/contrib/Dusk/global.css" rel="stylesheet" title="Dusk" type="text/css">
		<link href="<%=bugzillaUri%>/skins/contrib/Dusk/index.css" rel="stylesheet" title="Dusk" type="text/css">
		<link href="<%=bugzillaUri%>/skins/custom/global.css" rel="stylesheet" type="text/css">
		<link href="<%=bugzillaUri%>/skins/custom/index.css" rel="stylesheet" type="text/css">
		<%-- 
End of user code 
		--%>
	</head>
	<body style="padding: 10px;">
		<div id="bugzilla-body">
		<form id="Create" method="POST" class="enter_bug_form">
		<%-- 
Start of user code (RECOMMENDED) formStuff 
		--%>
		<%-- 
End of user code 
		--%>
				<table style="clear: both;">

						<tr>
							<td><%= BugzillaChangeRequest.affectedByDefectsAsHtmlForCreation(request, serviceProvider.getIdentifier())%></td>
						</tr>
						<tr>
							<td><%= BugzillaChangeRequest.createdAsHtmlForCreation(request, serviceProvider.getIdentifier())%></td>
						</tr>
						<tr>
							<td><%= BugzillaChangeRequest.versionAsHtmlForCreation(request, serviceProvider.getIdentifier())%></td>
						</tr>
						<tr>
							<td><%= BugzillaChangeRequest.tracksRequirementsAsHtmlForCreation(request, serviceProvider.getIdentifier())%></td>
						</tr>
						<tr>
							<td><%= BugzillaChangeRequest.tracksChangeSetsAsHtmlForCreation(request, serviceProvider.getIdentifier())%></td>
						</tr>
						<tr>
							<td><%= BugzillaChangeRequest.relatedTestPlansAsHtmlForCreation(request, serviceProvider.getIdentifier())%></td>
						</tr>
						<tr>
							<td><%= BugzillaChangeRequest.platformAsHtmlForCreation(request, serviceProvider.getIdentifier())%></td>
						</tr>
						<tr>
							<td><%= BugzillaChangeRequest.inprogressAsHtmlForCreation(request, serviceProvider.getIdentifier())%></td>
						</tr>
						<tr>
							<td><%= BugzillaChangeRequest.titleAsHtmlForCreation(request, serviceProvider.getIdentifier())%></td>
						</tr>
						<tr>
							<td><%= BugzillaChangeRequest.closeDateAsHtmlForCreation(request, serviceProvider.getIdentifier())%></td>
						</tr>
						<tr>
							<td><%= BugzillaChangeRequest.testedByTestCasesAsHtmlForCreation(request, serviceProvider.getIdentifier())%></td>
						</tr>
						<tr>
							<td><%= BugzillaChangeRequest.serviceProviderAsHtmlForCreation(request, serviceProvider.getIdentifier())%></td>
						</tr>
						<tr>
							<td><%= BugzillaChangeRequest.affectsRequirementsAsHtmlForCreation(request, serviceProvider.getIdentifier())%></td>
						</tr>
						<tr>
							<td><%= BugzillaChangeRequest.priorityAsHtmlForCreation(request, serviceProvider.getIdentifier())%></td>
						</tr>
						<tr>
							<td><%= BugzillaChangeRequest.dctermsTypesAsHtmlForCreation(request, serviceProvider.getIdentifier())%></td>
						</tr>
						<tr>
							<td><%= BugzillaChangeRequest.relatedTestExecutionRecordsAsHtmlForCreation(request, serviceProvider.getIdentifier())%></td>
						</tr>
						<tr>
							<td><%= BugzillaChangeRequest.approvedAsHtmlForCreation(request, serviceProvider.getIdentifier())%></td>
						</tr>
						<tr>
							<td><%= BugzillaChangeRequest.affectsTestResultsAsHtmlForCreation(request, serviceProvider.getIdentifier())%></td>
						</tr>
						<tr>
							<td><%= BugzillaChangeRequest.affectsPlanItemsAsHtmlForCreation(request, serviceProvider.getIdentifier())%></td>
						</tr>
						<tr>
							<td><%= BugzillaChangeRequest.relatedChangeRequestsAsHtmlForCreation(request, serviceProvider.getIdentifier())%></td>
						</tr>
						<tr>
							<td><%= BugzillaChangeRequest.reviewedAsHtmlForCreation(request, serviceProvider.getIdentifier())%></td>
						</tr>
						<tr>
							<td><%= BugzillaChangeRequest.componentAsHtmlForCreation(request, serviceProvider.getIdentifier())%></td>
						</tr>
						<tr>
							<td><%= BugzillaChangeRequest.contributorsAsHtmlForCreation(request, serviceProvider.getIdentifier())%></td>
						</tr>
						<tr>
							<td><%= BugzillaChangeRequest.rdfTypesAsHtmlForCreation(request, serviceProvider.getIdentifier())%></td>
						</tr>
						<tr>
							<td><%= BugzillaChangeRequest.creatorsAsHtmlForCreation(request, serviceProvider.getIdentifier())%></td>
						</tr>
						<tr>
							<td><%= BugzillaChangeRequest.instanceShapeAsHtmlForCreation(request, serviceProvider.getIdentifier())%></td>
						</tr>
						<tr>
							<td><%= BugzillaChangeRequest.closedAsHtmlForCreation(request, serviceProvider.getIdentifier())%></td>
						</tr>
						<tr>
							<td><%= BugzillaChangeRequest.productAsHtmlForCreation(request, serviceProvider.getIdentifier())%></td>
						</tr>
						<tr>
							<td><%= BugzillaChangeRequest.identifierAsHtmlForCreation(request, serviceProvider.getIdentifier())%></td>
						</tr>
						<tr>
							<td><%= BugzillaChangeRequest.fixedAsHtmlForCreation(request, serviceProvider.getIdentifier())%></td>
						</tr>
						<tr>
							<td><%= BugzillaChangeRequest.modifiedAsHtmlForCreation(request, serviceProvider.getIdentifier())%></td>
						</tr>
						<tr>
							<td><%= BugzillaChangeRequest.blocksTestExecutionRecordsAsHtmlForCreation(request, serviceProvider.getIdentifier())%></td>
						</tr>
						<tr>
							<td><%= BugzillaChangeRequest.shortTitleAsHtmlForCreation(request, serviceProvider.getIdentifier())%></td>
						</tr>
						<tr>
							<td><%= BugzillaChangeRequest.descriptionAsHtmlForCreation(request, serviceProvider.getIdentifier())%></td>
						</tr>
						<tr>
							<td><%= BugzillaChangeRequest.subjectsAsHtmlForCreation(request, serviceProvider.getIdentifier())%></td>
						</tr>
						<tr>
							<td><%= BugzillaChangeRequest.discussedByAsHtmlForCreation(request, serviceProvider.getIdentifier())%></td>
						</tr>
						<tr>
							<td><%= BugzillaChangeRequest.relatedTestCasesAsHtmlForCreation(request, serviceProvider.getIdentifier())%></td>
						</tr>
						<tr>
							<td><%= BugzillaChangeRequest.operatingSystemAsHtmlForCreation(request, serviceProvider.getIdentifier())%></td>
						</tr>
						<tr>
							<td><%= BugzillaChangeRequest.relatedTestScriptsAsHtmlForCreation(request, serviceProvider.getIdentifier())%></td>
						</tr>
						<tr>
							<td><%= BugzillaChangeRequest.verifiedAsHtmlForCreation(request, serviceProvider.getIdentifier())%></td>
						</tr>
						<tr>
							<td><%= BugzillaChangeRequest.statusAsHtmlForCreation(request, serviceProvider.getIdentifier())%></td>
						</tr>
						<tr>
							<td><%= BugzillaChangeRequest.implementsRequirementsAsHtmlForCreation(request, serviceProvider.getIdentifier())%></td>
						</tr>

					
					<tr>
						<td></td>
						<td>
							<input type="submit" value="Submit">
							<input type="reset">
						</td>
					</tr>
				</table>
				

				<div style="width: 500px;">
					
				</div>
				
			</form>

			<div style="clear: both;"></div>
		</div>
	</body>
</html>



