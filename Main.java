package advisor;

public class Main {
    public static void main(String[] args) {
        String serverPath = (args.length > 1) ? args[1] : "https://accounts.spotify.com";
        MusicAdvisor testAdvisor = new MusicAdvisor(serverPath);
        testAdvisor.listsInit();
        testAdvisor.processInput();

    }
}
