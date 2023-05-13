package com.papputech.pappu;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.TimeZone;
import java.io.PrintStream;

public class TimeInTamil {
    // ... dictionaries and methods ...
    private static final HashMap<String, String> hoursColloquial = new HashMap<String, String>() {{
        put("01", "ஒன்னு");
        put("02", "ரெண்டு");
        put("03", "மூனு");
        put("04", "நால்");
        put("05", "அஞ்சு");
        put("06", "ஆறு");
        put("07", "ஏழு");
        put("08", "எட்டு");
        put("09", "ஒம்போது");
        put("10", "பத்து");
        put("11", "பதனொன்னு");
        put("12", "பன்னண்டு");
    }};

    private static final HashMap<String, String> hoursColloquial15 = new HashMap<String, String>() {
        {
            put("01", "ஒன்னே");
            put("02", "ரெண்டே");
            put("03", "மூனே");
            put("04", "நாலே");
            put("05", "அஞ்சே");
            put("06", "ஆறே");
            put("07", "ஏழே");
            put("08", "எட்டே");
            put("09", "ஒம்போதே");
            put("10", "பத்தே");
            put("11", "பதனொன்னே");
            put("12", "பன்னண்டே");
        }
    };

    private static final HashMap<String, String> hoursColloquial30 = new HashMap<String, String>() {
        {
            put("01", "ஒன்ர");
            put("02", "ரெண்ர");
            put("03", "மூன்ர");
            put("04", "நால்ர");
            put("05", "அஞ்ர");
            put("06", "ஆர்ர");
            put("07", "ஏழ்ர");
            put("08", "எட்ர");
            put("09", "ஒம்போதர");
            put("10", "பத்தர");
            put("11", "பதனொன்ர");
            put("12", "பன்னண்ர");
        }
    };

    private static final HashMap<String, String> minsColloquial = new HashMap<String, String>() {
        {
            put("5", "அஞ்சு");
            put("10", "பத்து");
            put("15", "கால்");
            put("20", "இருபது");
            put("25", "இருபத்தஞ்சு");
            put("30", "அரை");
            put("35", "முப்பத்தஞ்சு");
            put("40", "நாப்பது");
            put("45", "முக்கால்");
            put("50", "அம்பது");
            put("55", "அம்பத்தஞ்சு");
            put("0", "");
        }
    };

    public static String roundToNearest5(int x) {
        int a = 5 * Math.round((float)x / 5);
        if (a == 60) {
            return "0";
        }
        return String.format(Locale.getDefault(), "%d", a);
    }

    public static String exactTimeInTamil() {
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm", Locale.getDefault());
        sdf.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata"));
        String[] timeParts = sdf.format(new Date()).split(":");
        return "\"" + timeParts[0].replaceAll("^0+", "") + "\" \"" + timeParts[1].replaceAll("^0+", "") + "\"";
    }

    public static String timeInTamil() {
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm", Locale.getDefault());
        sdf.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata"));
        String[] timeParts = sdf.format(new Date()).split(":");
        String hour = timeParts[0];
        String minute = timeParts[1];
        // hour = "11";
        // minute = "02";

        String minute2 = roundToNearest5(Integer.parseInt(minute));

        if ( Integer.parseInt(minute)>55) {
            int nextHour = Integer.parseInt(hour) + 1;
            hour = String.format(Locale.getDefault(), "%02d", nextHour);
        }

        String minute3 = minsColloquial.get(minute2);
        String formattedHour;

        if (minute2.equals("15") || minute2.equals("45")) {
            formattedHour = hoursColloquial15.get(hour);
        } else if (minute2.equals("30")) {
            formattedHour = hoursColloquial30.get(hour);
            minute3 = "";
        } else if (minute2.equals("10")) {
            formattedHour = hoursColloquial.get(hour)+ "ம்";
        } else {
            formattedHour = hoursColloquial.get(hour);
        }

        return formattedHour + minute3;
    }
    public static void main(String[] args) {
        String timeInTamil = timeInTamil();
        String exactTimeInTamil = exactTimeInTamil();
        try{
            PrintStream out = new PrintStream(System.out, true, "UTF-8");
            out.println("Time in Tamil: " + timeInTamil);
        } catch (Exception e) {
            throw new InternalError("VM does not support mandatory encoding UTF-8");
        }

        System.out.println( roundToNearest5(14));
        System.out.println("Exact time in Tamil: " + exactTimeInTamil);
    }
}
