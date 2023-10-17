import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;

public class Main {
    public static void main(String[] args) throws IOException {
        double stStephensGreenLatitude = 53.337839;
        double stStephensGreenLongitude = -6.259520;
        List<Friend> invitedFriends = new ArrayList<>();
        File file = new File("friends.txt");

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String st;

            while ((st = br.readLine()) != null) {
                try (JsonReader jsonReader = Json.createReader(new StringReader(st))) {
                    JsonObject jsonFriend = jsonReader.readObject();
                    String latitudeStr = jsonFriend.getString("latitude");
                    String longitudeStr = jsonFriend.getString("longitude");
                    int userId = jsonFriend.getInt("user_id");
                    String name = jsonFriend.getString("name");

                    double friendLatitude = Double.parseDouble(latitudeStr);
                    double friendLongitude = Double.parseDouble(longitudeStr);

                    double distance = calculateDistance(stStephensGreenLatitude, stStephensGreenLongitude, friendLatitude, friendLongitude);

                    if (distance <= 100.0) {
                        invitedFriends.add(new Friend(userId, name, distance));
                    } // end if
                } // end try
            } // end while
        } catch (IOException e) {
            e.printStackTrace();
        } // end catch

        // sort friends by id number
        invitedFriends.sort(Comparator.comparingInt(Friend::getUserId));

        for (Friend friend : invitedFriends) {
            System.out.println("User ID: " + friend.getUserId() + ", Name: " + friend.getName() + ", Distance: " + friend.getDistance());
        } // end for
    } // end try

    private static double calculateDistance(double latitude1, double longitude2, double latitude2, double longitude1) {
        // Convert degree values to radians
        double converted_longitude1 = Math.toRadians(longitude1);
        double converted_latitude1 = Math.toRadians(latitude1);
        double converted_longitude2 = Math.toRadians(longitude2);
        double converted_latitude2 = Math.toRadians(latitude2);

        // Calculating the distance
        double doubleLatitude = converted_latitude2 - converted_latitude1;
        double doubleLongitude = converted_longitude2 - converted_longitude1;
        double a = Math.pow(Math.sin(doubleLatitude / 2), 2) + Math.cos(latitude1) * Math.cos(latitude2) * Math.pow(Math.sin(doubleLongitude / 2), 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double radius = 6371; // earth radius
        return radius * c;
    } // end calculateDistance
} // end main


