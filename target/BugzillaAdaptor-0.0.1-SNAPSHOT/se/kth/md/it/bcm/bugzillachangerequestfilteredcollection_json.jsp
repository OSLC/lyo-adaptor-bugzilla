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

<%@ page import="java.net.*" %> 
<%@ page import="java.util.*" %>
<%@page import="org.eclipse.lyo.oslc4j.core.model.ServiceProvider"%>
<%@page import="se.kth.md.it.bcm.resources.BugzillaChangeRequest"%>
<%--
Start of user code imports
--%>
<%-- 
End of user code 
--%>

<%@ page contentType="application/json" language="java" pageEncoding="UTF-8" %>

{
<%
	ServiceProvider serviceProvider = (ServiceProvider) request.getAttribute("serviceProvider");
	String selectionUri = (String) request.getAttribute("selectionUri");
    List<BugzillaChangeRequest> resources = (List<BugzillaChangeRequest>) request.getAttribute("resources");
	String terms = (String) request.getAttribute("terms");
%>
<%--
Start of user code getRequestAttributes
--%>
<%--
End of user code
--%>
results: [
<% int i = 0; for (BugzillaChangeRequest r : resources) { %>
   <% if (i > 0) { %>,<% } %>
   {  "label" : "<%= r.toString() %>",
      "resourceUrl" : "<%= r.getAbout() %>"
   }
<% i++; } %>
]
}
