package bd1415.unipd.dei.it.cardb;

public class Util {

    private static String output;
    private static boolean isSet = false;

    public static void setOutput(String result) {
        output = result;
        isSet= true;
    }

    public static String getOutput() {
        return output;
    }

    public static boolean isSet(){
        return isSet;
    }

    public static void setToNull(){
        isSet = false;
        output = null;
    }
}
