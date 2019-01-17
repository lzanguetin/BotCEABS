package br.com.voxage.botceabs.models;

public class Validators {
    public static boolean isValidOSNum(String os) {
        if ((os == null) || (os.length() < 7)) {
            return false;
        }
        else return true;
    }
}
