package advisor;

public class Util {
    public String ACCESS_TOKEN = "";
    public String AUTH_CODE = "";

    public String AUTH_SERVER;
    //public static final String API_SERVER = "https://api.spotify.com";

    private final String CLIENT_ID = "8e8547fb06414c2cb62c9f91a2b86041";
    private final String CLIENT_SECRET = "00dff940af8542f4a104b4c74e8c5d17";

    public final String AUTHORIZE_PART = "/authorize";
    public final String RESPONSE_TYPE = "code";
    public final String TOKEN_PART = "/api/token";
    public final String GRANT_TYPE = "authorization_code";
    public final String REDIRECT_URI = "http://localhost:8080";

    public Util(String authServer) {
        this.AUTH_SERVER = authServer;
    }

    public String getAuthLink() {
        return AUTH_SERVER + AUTHORIZE_PART //https://accounts.spotify.com/authorize
                + "?client_id=" + CLIENT_ID //?client_id=3e0adb95e04b4acc920b0aad8a64883c
                + "&redirect_uri=" + REDIRECT_URI //&redirect_uri=http://localhost:8080
                + "&response_type=" + RESPONSE_TYPE; //&response_type=code
    }

    public String getCLIENT_ID() {
        return CLIENT_ID;
    }

    public String getCLIENT_SECRET() {
        return CLIENT_SECRET;
    }
}

