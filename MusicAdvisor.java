package advisor;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class MusicAdvisor {
    Scanner scanner = new Scanner(System.in);
    boolean exit = false;
    boolean authorized = false;
    final String clientID = "8e8547fb06414c2cb62c9f91a2b86041";
    String link = String.format("https://accounts.spotify.com/authorize?client_id=%s&redirect_uri=http://localhost:8080&response_type=code", clientID);

    List<String> newReleases = Arrays.asList("Mountains [Sia, Diplo, Labrinth]",
            "Runaway [Lil Peep]",
            "The Greatest Show [Panic! At The Disco]",
            "All Out Life [Slipknot]");
    List<String> featured = Arrays.asList("Mellow Morning",
            "Wake Up and Smell the Coffee",
            "Songs to Sing in the Shower");
    List<String> categories = Arrays.asList("Top Lists",
            "Pop",
            "Mood",
            "Lating");
    List<String> mood = Arrays.asList("Walk LIke A Badass",
            "Rage Beats",
            "Arab Mood Booster",
            "Sunday Stroll");

    void processInput() {
        while (!exit && scanner.hasNext()) {
            String input = scanner.nextLine();
            if (!authorized) {
                if ("auth".equals(input)) {
                    System.out.println(link);
                    System.out.println("---SUCCESS---");
                    authorized = true;
                } else {
                    System.out.println("Please, provide access for application.");
                }
            } else {
                processCommand(input);
            }
        }
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
