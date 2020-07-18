package advisor;

public class Main {
    public static void main(String[] args) {
        String serverPath = setServerPath(args);
        MusicAdvisor testAdvisor = new MusicAdvisor(serverPath);
        testAdvisor.listsInit();
        testAdvisor.processInput();
    }

    public static String setServerPath(String[] args) {
        String serverPath = "https://accounts.spotify.com";
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-access") && i + 1 <= args.length - 1) {
                serverPath = args[i + 1];
                break;
            }
        }
        return serverPath;
    }
}
