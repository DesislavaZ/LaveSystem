package com.company;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class MainMenu {
    public void menu() {
        System.out.println("\t\t*******************************************");
        System.out.println("\t\t\t          Система за отпуски ");
        System.out.println("\t\t*******************************************");

        System.out.println("\n\n1. Заяви отпуск");
        System.out.println("2. Виж всички отпуски ");
        System.out.println("3. Виж отпуска на служител");
        System.out.println("4. Промени статус на отпуск");
        System.out.println("5. Изход");

    }
}

class Leave_Add {
    public void createFile() {
        Scanner sc = new Scanner(System.in);

        EmployDetail emp = new EmployDetail();
        emp.getInfo();
        try {
            File f1 = new File("file" + emp.name + ".csv");
                if (f1.createNewFile()) {
                    FileWriter myWriter = new FileWriter("file" + emp.name + ".csv");
                    myWriter.write("Име на служител :" + emp.name + "\n" +
                            "ЕГН :" + emp.employ_id + "\n" + "Заявка номер :" + emp.request_number++ + "\n" +
                            "E-mail :" + emp.email + "\n" + "Тип отпуск: " + emp.type + "\n" +
                            "От дата - до дата :" + emp.validity + "\n" + "Статус: " + emp.status);
                    myWriter.close();
                    System.out.println("\n Заявлението е въведено :)\n");

                    System.out.print("\nНатисни Enter за продължение...");
                    sc.nextLine();
                } else {
                    System.out.println("\nЗаявлението вече съществува :(");
                    System.out.print("\nНатисни Enter за да продължиш");
                    sc.nextLine();
                }

        } catch (Exception e) {
            System.out.println("Грешка при въвеждането");
        }
    }

    class EmployDetail {
        String name;
        String email;
        String type;
        long employ_id;
        String validity;
        long request_number=11;
        String status = "Очаква одобрение";

        public void getInfo() {
            Scanner sc = new Scanner(System.in);
            System.out.print("Въведи имената на служител: ");
            name = sc.nextLine();
            System.out.print("Въведи ЕГН : ");
            employ_id = sc.nextLong();
            System.out.print("Въведи e-mail: ");
            email = sc.nextLine();
            email = sc.nextLine();
            System.out.print("Тип отпуск (платен или неплатен): ");
            type = sc.nextLine();
            System.out.print("От дата - до дата (dd.mm.yy) : ");
            validity = sc.nextLine();
        }
    }
}

class Employee_Show {
    public void viewFile(String s) throws Exception {
        File file = new File("file" + s + ".csv");
        Scanner sc = new Scanner(file);

        while (sc.hasNextLine()) {
            System.out.println(sc.nextLine());
        }
    }
}

class Employee_Update {
    public void updateFile(String s, String o, String n) throws IOException {
        File file = new File("file" + s + ".csv");
        Scanner sc = new Scanner(file);
        String fileContext = "";
        while (sc.hasNextLine()) {
            fileContext = fileContext + "\n" + sc.nextLine();
        }
        FileWriter myWriter = new FileWriter("file" + s + ".csv");
        fileContext = fileContext.replaceAll(o, n);
        myWriter.write(fileContext);
        myWriter.close();

    }
}

class CodeExit {
    public void out() {
        System.out.println("\n*************************************************");
        System.out.println("Благодарим, че използвахте системата за отпуски :) ");
        System.out.println("***************************************************");

        System.exit(0);
    }
}

class EmployManagementSystem {
    public static void main(String arv[]) {
        /** To clear the output Screen **/
        System.out.print("\033[H\033[2J");

        Scanner sc = new Scanner(System.in);
        Employee_Show epv = new Employee_Show();

        int i = 0;

        /*** Callining Mainmenu Class function ****/
        MainMenu obj1 = new MainMenu();
        obj1.menu();

        /*** Initialising loop for Menu Choices ***/
        while (i < 6) {

            System.out.print("\nМоля изберете :");
            i = Integer.parseInt(sc.nextLine());

            /** Switch Case Statements **/
            switch (i) {
                case 1: {
                    /** Creating class's object and calling Function using that object **/
                    Leave_Add ep = new Leave_Add();
                    ep.createFile();

//                    System.out.print("\033[H\033[2J");
                    obj1.menu();
                    break;
                }
                case 2: {


                    String s = "all";
                    try {
                        epv.viewFile(s);
                    } catch (Exception e) {
                        System.out.println(e);
                    }


                    System.out.print("\nНатисни Enter за да продължиш");
                    sc.nextLine();
                    System.out.print("\033[H\033[2J");
                    obj1.menu();
                    break;
                }

                case 3: {
                    System.out.print("\nВъведи име на служител :");
                    String s = sc.nextLine();
                    try {
                        epv.viewFile(s);
                    } catch (Exception e) {
                        System.out.println(e);
                    }


                    System.out.print("\nНатисни Enter за да продължиш");
                    sc.nextLine();
                    System.out.print("\033[H\033[2J");
                    obj1.menu();
                    break;
                }
                case 4: {
                    System.out.print("\nВъведете име :");
                    String I = sc.nextLine();
                    try {
                        epv.viewFile(I);
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                    Employee_Update epu = new Employee_Update();
                    String s = "Очаква одобрение";
                    System.out.print("Моля въведете новия статус" + "\n" + "(одобрена или отхвърлена) :");
                    String n = sc.nextLine();
                    try {
                        epu.updateFile(I, s, n);

                        System.out.println("Статусът е променен! ");
                        System.out.print("\nМоля, натиснете за да продължите!");
                        sc.nextLine();
                        System.out.print("\033[H\033[2J");
                        obj1.menu();
                        break;
                    } catch (IOException e) {
                        System.out.println(e);
                    }
                }
                case 5: {
                    CodeExit obj = new CodeExit();
                    obj.out();
                }
            }
        }
        System.out.println("Невалиден избор!");
        System.out.print("\nНатисни Enter за да продължиш");
        sc.nextLine();
        System.out.print("\033[H\033[2J");
        obj1.menu();
    }
}




