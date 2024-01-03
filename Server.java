import java.net.HttpURLConnection;
import java.net.URL;

public class Server {
    public static void req(String move) {
        int l = move.length();
        move = move.substring(1, l - 1);
        String[] pos = move.split(", ");

        String url = "http://localhost:3333/java/" + pos[1] + "/" + pos[0];
        httpReq(url);
    }

    public static void start() {
        String url = "http://localhost:3333/start";
        httpReq(url);
    }

    private static void httpReq(String u) {
        try {
            URL url = new URL(u);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.getResponseCode();
            connection.disconnect();
        } catch (Exception e) {
            System.err.println("[Java][ERROR] HTTP Request Failed");
        }
    }
}
