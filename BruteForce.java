
import java.util.*;

public class BruteForce extends UC11and12 {
    String userName;
    String userId;
    String password;

    public BruteForce() {
    }

    public void bruteForceAttack() {
        loadTeacherInfo();
        long startTime = System.currentTimeMillis();
        for (TeacherInfo teachers : teacherInfoList) {
            String teacherName = teachers.getFullName().trim();
            String teacherID = teachers.getID();
            guessChar(teacherName);
            guessChar(teacherID);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Total execution time: " + (double)(endTime - startTime)/1000 + "seconds");

    }
    public void guessChar(String text){
        String unKnownName = "*".repeat(text.length());
        String[] unkownNameSplitChar = new String[text.length()];
        unkownNameSplitChar = unKnownName.split("");
        int index = 0;
        for (char c : text.toCharArray()) {
            String findChar = String.valueOf(c);
            for (int i = 0; i < 50000; i++) {
                System.out.println(String.join(",", unkownNameSplitChar).replace(",", ""));
                String guessChar = String.valueOf((char) i);
                if (guessChar.equals(findChar)) {
                    unkownNameSplitChar[index++] = guessChar;
                    if(String.join(",", unkownNameSplitChar).replace(",", "").equals(text))
                        System.out.println(String.join(",", unkownNameSplitChar).replace(",", ""));
                    break;
                }
            }
        }
    }
}