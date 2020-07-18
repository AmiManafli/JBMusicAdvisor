package advisor;

import com.sun.net.httpserver.HttpServer;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class MusicAdvisor {
    Scanner scanner = new Scanner(System.in);
    boolean exit = false;
    boolean authorized = false;
    private String access_code;
    HttpRequestHandler httpRequestHandler;
    HttpServer server;
    String serverPath;

    List<String> newReleases;
    List<String> featured;
    List<String> categories;
    List<String> mood;

    public MusicAdvisor(String serverPath) {
        this.serverPath = serverPath;
    }


    void listsInit() {
        newReleases = Arrays.asList("Mountains [Sia, Diplo, Labrinth]",
                "Runaway [Lil Peep]",
                "The Greatest Show [Panic! At The Disco]",
                "All Out Life [Slipknot]");
        featured = Arrays.asList("Mellow Morning",
                "Wake Up and Smell the Coffee",
                "Songs to Sing in the Shower");
        categories = Arrays.asList("Top Lists",
                "Pop",
                "Mood",
                "Lating");
        mood = Arrays.asList("Walk LIke A Badass",
                "Rage Beats",
                "Arab Mood Booster",
                "Sunday Stroll");
    }


    void processInput() {
        while (!exit && scanner.hasNext()) {
            String input = scanner.nextLine();
            if (!authorized) {
                if ("auth".equals(input)) {
                    Util util = new Util(serverPath);
                    httpRequestHandler = new HttpRequestHandler();
                    server = httpRequestHandler.serverSetup();
                    if (server != null) {
                        httpRequestHandler.createContext(
                                util.getCLIENT_ID(),
                                util.getCLIENT_SECRET(),
                                serverPath,
                                this::printSuccess
                        );
                        server.start();
                    }
                    String urlAccessRequest = util.getAuthLink();
                    System.out.println("Input system path: " + serverPath);
                    System.out.println("use this link to request the access code:\n" + urlAccessRequest);
                    System.out.println("\nwaiting for code...");
                    String accessToken = httpRequestHandler.getAccessToken();
                    if (accessToken != null) {
                        System.out.println("response:");
                        System.out.println(accessToken);
                        System.out.println("\n---SUCCESS---");
                    }
                    authorized = true;
                } else {
                    System.out.println("Please, provide access for application.");
                    continue;
                }
            }
            processCommand(input);
        }
    }

    public void printSuccess(String accessToken) {
        System.out.println("response: ");
        System.out.println(accessToken);
        System.out.println("---SUCCESS---");
    }


    private void processCommand(String input) {
        switch (input) {
            case "new":
                System.out.println("---NEW RELEASES---");
                print(newReleases);
                break;
            case "featured":
                System.out.println("---FEATURED---");
                print(featured);
                break;
            case "categories":
                System.out.println("---CATEGORIES---");
                print(categories);
                break;
            case "playlists Mood":
                System.out.println("---MOOD PLAYLISTS---");
                print(mood);
                break;
            case "exit":
                System.out.println("---GOODBYE!---");
                httpRequestHandler.server.stop(1);
//                System.out.println(server.getAddress());
                exit = true;
                break;
        }
    }

    private static void print(List<String> listOfStrings) {
        for (String element : listOfStrings) {
            System.out.println(element);
        }
    }

}
