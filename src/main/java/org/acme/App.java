package org.acme;

import com.sun.net.httpserver.HttpServer;
import io.dekorate.openshift.annotation.OpenshiftApplication;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

/**
 * Hello world!
 *
 */

@OpenshiftApplication(name = "hello-world-fwless")
public class App 
{
    public static void main(String[] args) throws IOException {
        int serverPort = 8080;
        HttpServer server = HttpServer.create(new InetSocketAddress(serverPort), 0);
        server.createContext("/api/hello", (exchange -> {
            String respText = "Hello FrameWorkless world!";
            exchange.sendResponseHeaders(200, respText.getBytes().length);
            OutputStream output = exchange.getResponseBody();
            output.write(respText.getBytes());
            output.flush();
            exchange.close();
        }));
        server.setExecutor(null); // creates a default executor
        System.out.println("Listening in port "+serverPort);
        server.start();
    }

}