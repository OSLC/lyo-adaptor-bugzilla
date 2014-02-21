<!DOCTYPE html>
<%--
 Copyright (c) 2011, 2012 IBM Corporation and others.

 All rights reserved. This program and the accompanying materials
 are made available under the terms of the Eclipse Public License v1.0
 and Eclipse Distribution License v. 1.0 which accompanies this distribution.
 
 The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 and the Eclipse Distribution License is available at
 http://www.eclipse.org/org/documents/edl-v10.php.
 
 Contributors:
 
    Sam Padgett	  	- initial API and implementation
    Michael Fiedler	- adapted for OSLC4J
	Jad El-khoury        - initial implementation of code generator (https://bugs.eclipse.org/bugs/show_bug.cgi?id=422448)

 This file is generated by org.eclipse.lyo.oslc4j.codegenerator
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
    BugzillaChangeRequest aBugzillaChangeRequest = (BugzillaChangeRequest) request.getAttribute("aBugzillaChangeRequest");
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
		<title><%= serviceProvider.getTitle() %></title>
		<%-- 
Start of user code (RECOMMENDED) headStuff 
		--%>
		<link href="<%= bugzillaUri %>/skins/standard/global.css" rel="stylesheet" type="text/css">
		<link href="<%= bugzillaUri %>/skins/standard/index.css" rel="stylesheet" type="text/css">
		<link href="<%= bugzillaUri %>/skins/standard/global.css" rel="alternate stylesheet" title="Classic" type="text/css">
		<link href="<%= bugzillaUri %>/skins/standard/index.css" rel="alternate stylesheet" title="Classic" type="text/css">
		<link href="<%= bugzillaUri %>/skins/contrib/Dusk/global.css" rel="stylesheet" title="Dusk" type="text/css">
		<link href="<%= bugzillaUri %>/skins/contrib/Dusk/index.css" rel="stylesheet" title="Dusk" type="text/css">
		<link href="<%= bugzillaUri %>/skins/custom/global.css" rel="stylesheet" type="text/css">
		<link href="<%= bugzillaUri %>/skins/custom/index.css" rel="stylesheet" type="text/css">
		<link rel="shortcut icon" href="<%= bugzillaUri %>/images/favicon.ico">		
		<%-- 
End of user code 
		--%>
	</head>
	<body onload="">
		<div id="header">
			<div id="banner"></div>
			<table border="0" cellspacing="0" cellpadding="0" id="titles">
				<tr>
					<td id="title">
						<p>
							<%= serviceProvider.getTitle() %>
						</p>
					</td>
					<td id="information">
						<p class="header_addl_info">
							version 0.1
						</p>
					</td>
				</tr>
			</table>
		</div>
		
		<div id="bugzilla-body">  
			<div id="page-index">
				<%-- 
Start of user code (RECOMMENDED) bodyStuff1 
				--%>
				<img src="../../images/resources/bugzilla.gif" alt="icon" width="80" height="80" />				
				<%-- 
End of user code 
				--%>

				<h1>Resource Presentation</h1>
                <p>Summary: <%= aBugzillaChangeRequest.toHtml() %><br /></p>
				<h2>Properties</h2>
				<table style="clear: both;">
					<tr>
						<td><%= aBugzillaChangeRequest.creatorsToHtml()%></td>
					</tr>
					<tr>
						<td><%= aBugzillaChangeRequest.titleToHtml()%></td>
					</tr>
					<tr>
						<td><%= aBugzillaChangeRequest.closeDateToHtml()%></td>
					</tr>
					<tr>
						<td><%= aBugzillaChangeRequest.platformToHtml()%></td>
					</tr>
					<tr>
						<td><%= aBugzillaChangeRequest.discussedByToHtml()%></td>
					</tr>
					<tr>
						<td><%= aBugzillaChangeRequest.closedToHtml()%></td>
					</tr>
					<tr>
						<td><%= aBugzillaChangeRequest.affectsPlanItemsToHtml()%></td>
					</tr>
					<tr>
						<td><%= aBugzillaChangeRequest.affectedByDefectsToHtml()%></td>
					</tr>
					<tr>
						<td><%= aBugzillaChangeRequest.componentToHtml()%></td>
					</tr>
					<tr>
						<td><%= aBugzillaChangeRequest.fixedToHtml()%></td>
					</tr>
					<tr>
						<td><%= aBugzillaChangeRequest.inprogressToHtml()%></td>
					</tr>
					<tr>
						<td><%= aBugzillaChangeRequest.identifierToHtml()%></td>
					</tr>
					<tr>
						<td><%= aBugzillaChangeRequest.versionToHtml()%></td>
					</tr>
					<tr>
						<td><%= aBugzillaChangeRequest.createdToHtml()%></td>
					</tr>
					<tr>
						<td><%= aBugzillaChangeRequest.reviewedToHtml()%></td>
					</tr>
					<tr>
						<td><%= aBugzillaChangeRequest.serviceProviderToHtml()%></td>
					</tr>
					<tr>
						<td><%= aBugzillaChangeRequest.implementsRequirementsToHtml()%></td>
					</tr>
					<tr>
						<td><%= aBugzillaChangeRequest.operatingSystemToHtml()%></td>
					</tr>
					<tr>
						<td><%= aBugzillaChangeRequest.descriptionToHtml()%></td>
					</tr>
					<tr>
						<td><%= aBugzillaChangeRequest.contributorsToHtml()%></td>
					</tr>
					<tr>
						<td><%= aBugzillaChangeRequest.relatedChangeRequestsToHtml()%></td>
					</tr>
					<tr>
						<td><%= aBugzillaChangeRequest.relatedTestExecutionRecordsToHtml()%></td>
					</tr>
					<tr>
						<td><%= aBugzillaChangeRequest.modifiedToHtml()%></td>
					</tr>
					<tr>
						<td><%= aBugzillaChangeRequest.priorityToHtml()%></td>
					</tr>
					<tr>
						<td><%= aBugzillaChangeRequest.tracksRequirementsToHtml()%></td>
					</tr>
					<tr>
						<td><%= aBugzillaChangeRequest.testedByTestCasesToHtml()%></td>
					</tr>
					<tr>
						<td><%= aBugzillaChangeRequest.dctermsTypesToHtml()%></td>
					</tr>
					<tr>
						<td><%= aBugzillaChangeRequest.relatedTestScriptsToHtml()%></td>
					</tr>
					<tr>
						<td><%= aBugzillaChangeRequest.shortTitleToHtml()%></td>
					</tr>
					<tr>
						<td><%= aBugzillaChangeRequest.verifiedToHtml()%></td>
					</tr>
					<tr>
						<td><%= aBugzillaChangeRequest.approvedToHtml()%></td>
					</tr>
					<tr>
						<td><%= aBugzillaChangeRequest.blocksTestExecutionRecordsToHtml()%></td>
					</tr>
					<tr>
						<td><%= aBugzillaChangeRequest.affectsTestResultsToHtml()%></td>
					</tr>
					<tr>
						<td><%= aBugzillaChangeRequest.statusToHtml()%></td>
					</tr>
					<tr>
						<td><%= aBugzillaChangeRequest.relatedTestPlansToHtml()%></td>
					</tr>
					<tr>
						<td><%= aBugzillaChangeRequest.instanceShapeToHtml()%></td>
					</tr>
					<tr>
						<td><%= aBugzillaChangeRequest.affectsRequirementsToHtml()%></td>
					</tr>
					<tr>
						<td><%= aBugzillaChangeRequest.subjectsToHtml()%></td>
					</tr>
					<tr>
						<td><%= aBugzillaChangeRequest.rdfTypesToHtml()%></td>
					</tr>
					<tr>
						<td><%= aBugzillaChangeRequest.productToHtml()%></td>
					</tr>
					<tr>
						<td><%= aBugzillaChangeRequest.tracksChangeSetsToHtml()%></td>
					</tr>
					<tr>
						<td><%= aBugzillaChangeRequest.relatedTestCasesToHtml()%></td>
					</tr>
				</table>

			</div>
		</div>
		
		<div id="footer">
			<div class="intro"></div>
			<div class="outro">
				<div style="margin: 0 1em 1em 1em; line-height: 1.6em; text-align: left">
					<b>OSLC Tools Adapter Server 0.1</b> brought to you by <a href="http://eclipse.org/lyo">Eclipse Lyo</a><br />
				</div>
			</div>
		</div>
		

		
	</body>
</html>




