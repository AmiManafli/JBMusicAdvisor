package advisor;

import java.util.Scanner;


public class MusicAdvisor {
    boolean adviceOn = false;

    void advise() {
        adviceOn = true;
        chooseCommand();
    }

    void chooseCommand() {
        Scanner scanner = new Scanner(System.in);

        while (!scanner.hasNext("auth")) {
            if (scanner.nextLine().equals("exit")) { exit(); break; }
            System.out.println("Please, provide access for application.");
        }

        while (adviceOn) {
            String command = scanner.next();

            if (command.contains("playlists")) {
                String category = command.split("playlists ")[1];
                System.out.printf("---%s PLAYLISTS---\n", category.toUpperCase());
                continue;
            }

            switch(command) {
                case "new":
                    System.out.println("---NEW RELEASES---");
                    break;
                case "featured":
                    System.out.println("---FEATURED---");
                    break;
                case "categories":
                    System.out.println("---CATEGORIES---");
                    break;
                case "exit":
                    exit();
                    break;
                case "auth":
                    auth();
                    break;
                default:
                    error();
            }
        }
    }

    void auth() {
        Authorization auth = new Authorization();
        auth.createHttpServer();
        auth.authRequest();
    }

    void error() {
        System.out.println("Incorrect command. Try again.");
    }

    void exit() {
        System.out.println("---GOODBYE!---");
        adviceOn = false;
    }
}