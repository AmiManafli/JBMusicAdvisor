package advisor;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Base64;
import java.util.function.Consumer;

public class HttpRequestHandler {
    HttpServer server;
    private String accessCode;
    private String accessToken;
    private String query;

    HttpServer serverSetup() {
        try {
            server = HttpServer.create();
            server.bind(new InetSocketAddress(8080), 0);
            return server;
        } catch (IOException e) {}
        return server;
    }

    void createContext(String clientID, String client_secret, String serverPath, Consumer<String> successCallback) {
        server.createContext("/", httpExchange -> {
            if ("GET".equals(httpExchange.getRequestMethod())) {
                query = httpExchange.getRequestURI().getQuery();
                String webMessage = "";
                System.out.println("Received query: " + query);

                if (query != null && query.startsWith("code=")) {
                    webMessage = "Got the code. Return back to your program.";
                } else {
                    webMessage = "Not found authorization code. Try again."; //Authorization code not found. Try again
                }
                accessCode = query.split("=")[1];

                httpExchange.sendResponseHeaders(200, webMessage.length());
                httpExchange.getResponseBody().write(webMessage.getBytes());
                httpExchange.getResponseBody().close();
                httpExchange.close();
            }

            HttpClient client = HttpClient.newHttpClient();
            String base64client = clientID + ":" + client_secret;
            base64client = Base64.getEncoder().encodeToString(base64client.getBytes());
            HttpRequest request = HttpRequest.newBuilder()
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .header("Authorization", "Basic " + base64client)
                    .uri(URI.create(serverPath + "/api/token"))
                    .POST(HttpRequest.BodyPublishers.ofString("grant_type=authorization_code&code=" + accessCode +
                            "&redirect_uri=http://localhost:8080"))
                    .build();
            try {
                System.out.println("making http request for access token...");
                HttpResponse<String> response = client.send(request,
                        HttpResponse.BodyHandlers.ofString());
                accessToken = response.body();
                successCallback.accept(accessToken);
                server.stop(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    public String getAccessCode() {
        return accessCode;
    }

    public String getAccessToken() {
        return accessToken;
    }
}
