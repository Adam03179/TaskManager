package utils;


import utils.exception.WrongInputException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ConsoleHelper {

    private static BufferedReader reader =
            new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message) {
        System.out.println(message);
    }


    public static String readString() {

        String text = null;
        try {
            text = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return text;
    }


    public static Timestamp readTime() throws WrongInputException {
        Long time = null;
        try {
            String dateTime = readString();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
            Date date = dateFormat.parse(dateTime);
            time = date.getTime();
        } catch (ParseException e) {
            ConsoleHelper.writeMessage("Wrong date input, please try again");
            throw new WrongInputException();
         }
        return new Timestamp(time);

    }


    public static int readInt() throws WrongInputException {
        String text = null;
        try {
            text = readString();

        } catch (NumberFormatException e) {
            ConsoleHelper.writeMessage("wrong input, please try again");
            throw new WrongInputException();

        }
        return Integer.parseInt(text.trim());

    }


}
