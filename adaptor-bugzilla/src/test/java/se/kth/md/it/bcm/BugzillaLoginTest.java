package se.kth.md.it.bcm;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

import com.j2bugzilla.base.BugzillaConnector;
import com.j2bugzilla.rpc.LogIn;
import org.junit.jupiter.api.Test;
import org.mockserver.integration.ClientAndServer;
import org.mockserver.verify.VerificationTimes;

class BugzillaLoginTest {
  @Test
  void authenticatesThroughBugzillaXmlRpc() throws Exception {
    ClientAndServer server = ClientAndServer.startClientAndServer(0);
    try {
      server.when(request().withMethod("POST").withPath("/xmlrpc.cgi"))
          .respond(response().withHeader("Content-Type", "text/xml").withBody("""
              <?xml version="1.0"?>
              <methodResponse><params><param><value><struct>
                <member><name>id</name><value><int>42</int></value></member>
                <member><name>token</name><value><string>dummy-token</string></value></member>
              </struct></value></param></params></methodResponse>
              """));
      BugzillaConnector connector = new BugzillaConnector();
      connector.connectTo("http://localhost:" + server.getLocalPort() + "/xmlrpc.cgi");
      LogIn login = new LogIn("test@example.test", "dummy-password");
      connector.executeMethod(login);
      assertEquals(42, login.getUserID());
      assertEquals("dummy-token", login.getToken());
      server.verify(request().withMethod("POST").withPath("/xmlrpc.cgi")
          .withBody(org.mockserver.model.XPathBody.xpath("/methodCall[methodName='User.login']")),
          VerificationTimes.exactly(1));
    } finally {
      server.stop();
    }
  }
}
