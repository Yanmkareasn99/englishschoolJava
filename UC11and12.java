import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

class StudentLessonInf {
    private LocalDate date;
    private String time;
    private String lessonFormat;
    private String studentName;
    private String teacherName;

    public StudentLessonInf(LocalDate date, String time, String lessonFormat, String studentName, String teacherName) {
        this.date = date;
        this.time = time;
        this.lessonFormat = lessonFormat;
        this.studentName = studentName;
        this.teacherName = teacherName;

        // getter
    }

    public LocalDate getDate() {
        return this.date;
    }

    public String getTime() {
        return this.time;
    }

    public String getLessonFormat() {
        return this.lessonFormat;
    }

    public String getStudentName() {
        return this.studentName;
    }

    public String getTeacherName() {
        return this.teacherName;
    }

    // setter
    public void setDate(int year, int month, int date) {
        this.date = LocalDate.of(year, month, date);
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setLessonFormat(String lessonFormat) {
        this.lessonFormat = lessonFormat;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }
}

class StudentInfo {
    private String id;
    private String fullName;
    private String dateOfBirth;
    private String status;
    private String points;
    private String currentlyScheduledLessons;

    public StudentInfo(String id, String fullName, String dateOfBirth, String status, String points,
            String currentlyScheduledLessons) {
        this.id = id;
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
        this.status = status;
        this.points = points;
        this.currentlyScheduledLessons = currentlyScheduledLessons;

    }

    // getter
    public String getID() {
        return this.id;
    }

    public String getFullName() {
        return this.fullName;
    }

    public String getDateOfBirth() {
        return this.dateOfBirth;
    }

    public String getStatus() {
        return this.status;
    }

    public String getPoints() {
        return this.points;
    }

    public String getCurrentlyScheduledLessons() {
        return this.currentlyScheduledLessons;
    }

    // setter
    public void setStatus() {
        this.status = "cancellation";
    }

}

class TeacherInfo {
    private String id;
    private String fullName;
    private String dateOfBirth;
    private String employstatus;
    private String address;
    private String email;

    public TeacherInfo(String id, String fullName, String dateOfBirth, String Employstatus, String address,
            String email) {
        this.id = id;
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
        this.employstatus = employstatus;
        this.address = address;
        this.email = email;

    }

    // getter
    public String getID() {
        return this.id;
    }

    public String getFullName() {
        return this.fullName;
    }

    public String getDateOfBirth() {
        return this.dateOfBirth;
    }

    public String getEmploystatus() {
        return this.employstatus;
    }

    public String getAddress() {
        return this.address;
    }

    public String getEmail() {
        return this.email;
    }

}

public class UC11and12 {
    public Scanner sc = new Scanner(System.in);
    public ArrayList<StudentLessonInf> StudentLI = new ArrayList<>();
    public ArrayList<StudentInfo> studentInfoList = new ArrayList<>();
    public ArrayList<TeacherInfo> teacherInfoList = new ArrayList<>();

    public UC11and12() {

    }

    public void viewTeacherSch() {
        loadTeacherInfo();
        loadTeacherSch();
        try {

            System.out.print("講師ID: ");
            String teacherId = sc.nextLine();//"TID-7728";// 
            String teacherName = "";
            int countTeachers = 0;
            for (TeacherInfo teacher : teacherInfoList) {
                if (teacher.getID().trim().equalsIgnoreCase(teacherId)) {
                    teacherName = teacher.getFullName();
                    break;
                }
                if (++countTeachers == teacherInfoList.size()) {
                    System.err.println("このIDはまだシステムに登録されていないので、表teacherInfo.csvのIDを参照してください");
                    return;
                }

            }

            System.out.print("開始日(YYYY/MM/DD):");
            String fromDate =  sc.nextLine();//"2026/01/02";//// ; //
            System.out.print("終了日(YYYY/MM/DD):");
            String toDate = sc.nextLine();// "2026/12/08";// // ;//
            System.out.println();
            String waiting="";
            for (int i = 0; i < 10; i++) {
                Thread.sleep(500);
                System.out.print(waiting += ".");
                System.out.print(" \r");
            }

            String[] startDate = fromDate.split("/");
            String[] endtDate = toDate.split("/");

            LocalDate startDateFormate = LocalDate.of(Integer.parseInt(startDate[0]), Integer.parseInt(startDate[1]),
                    Integer.parseInt(startDate[2]));
            LocalDate endDateFormate = LocalDate.of(Integer.parseInt(endtDate[0]), Integer.parseInt(endtDate[1]),
                    Integer.parseInt(endtDate[2]));

            DateTimeFormatter format = DateTimeFormatter.ofPattern("YYYY/MM/dd");
            System.out.println("+-----------------------------------------------------------+");
            System.out.printf("| %-48s|\n", "講師レッスン担当予定");

            System.out.println("+-----------------------------------------------------------+");
            System.out.printf("| 講師: %-17s [ %-14s ]                |\n",  teacherName, teacherId.toUpperCase()  ); 
            System.out.println("+-----------------------------------------------------------+");
            System.out.println("| 期間選択 開始日：" + fromDate + "  終了日：" + toDate + "           |");
            System.out.println("+-----------------------------------------------------------+");

            System.out.println("|日時        | レッスン形式 | 生徒名               | 順番　 |");

            int countG = 0;
            LocalDate [] previousStudentDate = new LocalDate[2];
            boolean checkIfThereIsLessonShow = true;
            int countDaysOfTeaching = 0;
            for (StudentLessonInf student : StudentLI) {
                LocalDate date = student.getDate();
                String blank = "";
                if (!date.isBefore(startDateFormate) && !date.isAfter(endDateFormate)
                        && teacherId.equalsIgnoreCase(student.getTeacherName().trim())) {
                           
                        
                    if (student.getLessonFormat().trim().equalsIgnoreCase("group")) {
                        checkIfThereIsLessonShow = false; 
                        previousStudentDate[0] = student.getDate();
                       
                        if (countG == 0 || !previousStudentDate[0].isEqual(previousStudentDate[1])) {
                            countDaysOfTeaching++;
                            previousStudentDate[1] = previousStudentDate[0];
                            countG = 1;
                            countG++;
                            System.out.println("+-----------------------------------------------------------+");
                            System.out.printf("| %-8s | %-12s | %-20s | %-6s |\n| %-10s | %-12s | %-20s | %-6s |\n" + //
                                    "", student.getDate().format(format),
                                    student.getLessonFormat(), student.getStudentName(), "1", student.getTime(), blank,
                                    blank,
                                    blank);
                                  
                        } else {
                            
                            if(countG==0)countDaysOfTeaching++;
                            String groupMstr = Integer.toString(countG);
                            System.out.printf("| %-10s | %-12s | %-20s | %-6s |\n| %-10s | %-12s | %-20s | %-6s |\n" + //
                                    "", blank,
                                    blank, student.getStudentName(), groupMstr, blank, blank, blank,
                                    blank);
                                    countG++;
                        }

                    } else {
                        countDaysOfTeaching++;
                        checkIfThereIsLessonShow = false;
                        System.out.println("+-----------------------------------------------------------+");
                        System.out.printf("| %-8s | %-12s | %-20s | %-6s |\n| %-10s | %-12s | %-20s | %-6s | \n" + //
                                "", student.getDate().format(format),
                                student.getLessonFormat(), student.getStudentName(), 1, student.getTime(), blank,
                                blank,
                                blank);
                                                    countG = 0;
    
                    }

                }
            }
            System.out.println("+-----------------------------------------------------------+");
            System.out.println("授業日数合計: " + countDaysOfTeaching);


            if (checkIfThereIsLessonShow) {
                System.out.println("|            レッスン予定がありません                       |");
                System.out.println("+-----------------------------------------------------------+");

            }

        } catch (Exception e) {
            System.out.println("------->>>>無効な形式の入力<<<<-------");
            System.out.println("開始日の形式と終了の日は (YYYY/MM/DD), 例: 2026/01/01");

        }

    }

    public void cancellationProcess() {
        loadStudentInfo();
       
        try {

            System.out.print("受付スタッフID: ");
            String inchargeName = sc.nextLine();// "Noni" ;//
            System.out.print("生徒ID: ");
            String studentID = sc.nextLine();// "SID-3421";
            System.out.print("理由: ");
            String reason = sc.nextLine(); 
            System.out.println("");
            String waiting = "Searching";

            for (int i = 0; i < 10; i++) {
                Thread.sleep(500);
                System.out.print(waiting += ".");
                System.out.print(" \r");
            }         
            if (reason.length() > 40) {
                reason = reason.substring(0, 40) + "...";
            }

            for (StudentInfo students : studentInfoList) {
                if (students.getID().trim().equalsIgnoreCase(studentID)) {
                    System.out.println("-----------------------------------------------------------");
                    System.out.println();
                    System.out.println(" 画面名: 退会手続き画面");
                    System.out.println("-----------------------------------------------------------");
                    System.out.println(" 担当者：" + inchargeName);
                    System.out.println("-----------------------------------------------------------");
                    System.out.println(" 生徒ID：" + studentID);
                    System.out.println("-----------------------------------------------------------");
                    System.out.println(" ■ 生徒情報");
                    System.out.println("-----------------------------------------------------------");
                    System.out.println(" 氏名: " + students.getFullName());
                    System.out.println(" 生年月日: " + students.getDateOfBirth());
                    System.out.println(" 会員ステータス: " + students.getStatus());
                    System.out.println(" ポイント残高: " + students.getPoints());
                    System.out.printf(" 予約中レッスン: %s\n",
                            students.getCurrentlyScheduledLessons().equalsIgnoreCase("none") ? "なし" : "あり");
                    System.out.println("-----------------------------------------------------------");
                    System.out.println("■ 退会手続き");
                    System.out.println("-----------------------------------------------------------");
                    System.out.println("退会理由： " + reason);
                    LocalDateTime today = LocalDateTime.now();
                    DateTimeFormatter format = DateTimeFormatter.ofPattern("YYYY/MM/dd H:M:M");
                    System.out.println("退会日：" + today.format(format));
                    System.out.println("-----------------------------------------------------------");
                    System.out.println("[ 1 - 退会を確定する ]  　　　 [ 0 - キャンセル ]");
                    System.out.println("-----------------------------------------------------------");
                    int choice = sc.nextInt();
                    switch (choice) {
                        case 0:
                            System.out.println("手続きをキャンセルしました。");
                            break;
                        case 1:
                            studentStatusUpdate(studentID);
                            System.out.println("退会をは了です。");
                            break;
                        default:
                            System.out.println("0 と 1 を入力してください");
                    }

                    return;

                }

            }

            System.out.println(">>生徒情報が見つかりません,表StudentLessonInfo.csvのIDを参照してください<<");

        } catch (Exception e) {
            System.out.println("------->>>>無効な形式の入力<<<<-------");
            e.getMessage();

        }

    }

    public void loadTeacherSch() {
        StudentLI.clear();
        BufferedReader br = null;

        String fileName = "teacherSch.csv";
        try {
            br = new BufferedReader(new FileReader(new File(fileName)));
            String line = "";
            int time = 0;
            while ((line = br.readLine()) != null) {
                if(time++ == 0) continue;
                String[] lineSplit = line.split(",");
                String[] splittDate = lineSplit[0].split("-");
                LocalDate date = LocalDate.of(Integer.parseInt(splittDate[0]), Integer.parseInt(splittDate[1]),
                        Integer.parseInt(splittDate[2]));
                StudentLI.add(new StudentLessonInf(date, lineSplit[1], lineSplit[2], lineSplit[3], lineSplit[4]));
            }
            br.close();
        } catch (Exception e) {
            // System.out.println(e.getMessage() + " Error");
        }
    }

    public void loadStudentInfo() {
        studentInfoList.clear();
        BufferedReader br = null;

        String fileName = "studentLessonInfo.csv";
        try {
            br = new BufferedReader(new FileReader(new File(fileName)));
            String line = "";
            int time = 0;
            while ((line = br.readLine()) != null) {
                if(time++ == 0) continue;
                String[] lineSplit = line.split(",");
                studentInfoList.add(new StudentInfo(lineSplit[0], lineSplit[1], lineSplit[2], lineSplit[3],
                        lineSplit[4], lineSplit[5]));
            }
            br.close();
        } catch (Exception e) {
            System.out.println(e.getMessage() + " Error");
        }
    }

    public void loadTeacherInfo() {
        teacherInfoList.clear();
        BufferedReader br = null;

        String fileName = "teacherInfo.csv";
        try {
            br = new BufferedReader(new FileReader(new File(fileName)));
            String line = "";
            int time = 0;
            while ((line = br.readLine()) != null) {
                if(time++ == 0) continue;
                String[] lineSplit = line.split(",");
                teacherInfoList.add(new TeacherInfo(lineSplit[0], lineSplit[1], lineSplit[2], lineSplit[3],
                        lineSplit[4], lineSplit[5]));
            }
            br.close();
        } catch (Exception e) {
            System.out.println(e.getMessage() + " Error");
        }
    }

    public void studentStatusUpdate(String id) {
        studentInfoList.clear();
        loadStudentInfo();
        BufferedWriter bw = null;
        String text = "";
        for (StudentInfo students : studentInfoList) {
            if (students.getID().trim().equalsIgnoreCase(id)) {
                students.setStatus();
            }
            text += students.getID() + "," + students.getFullName() + "," + students.getDateOfBirth() + ","
                    + students.getStatus() + "," + students.getPoints() + "," + students.getCurrentlyScheduledLessons()
                    + "\n";
        }
        try {
            bw = new BufferedWriter(new FileWriter(new File("studentLessonInfo.csv")));
            bw.write(text);
            bw.close();
        } catch (Exception e) {
            e.getMessage();
        }

    }
}
