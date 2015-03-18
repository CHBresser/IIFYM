/*******************************************\
 * Programmer Name: ObamaNYoMama            *
 * Date Written:    03/17/2015              *
 * Purpose:         To perform fitness calc-*
 *                  ulations such as BMR    *
 *                  calculation and TDEE    *
 *                  calculation.            *
 * License:         Released under GPU 3.0  *
 *                  Feel free to distribute,*
 *                  modify or use in any way*
 *                  Although not required   *
 *                  credit would be greatly *
 *                  appreciated.            *
 \******************************************/
package fitness;

import java.util.Scanner;

public class Fitness {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        menu();
        in.close();
    }
    public static void menu()
    {
        int choice;
        String contin = "Y";
        
            while ("Y".equals(contin) || "y".equals(contin))
            {
                Scanner in = new Scanner(System.in);
                User singleUser = new User(); // Create an instance of User
                
                System.out.println("\nWhat would you like to do? ");
                System.out.println("1. Calculate BMR.");
                System.out.println("2. Calculate TDEE.");
                System.out.println("3. Find Needed Caloric Intake.");
                System.out.println("4. Calculate Macros.");
                System.out.print("Your Choice: ");
                
                choice = in.nextInt();
                
                switch(choice)
                {
                    case(1):
                        singleUser.calcBMR();
                        break;
                    case(2):
                        singleUser.calcTDEE();
                        break;
                    case(3):
                        singleUser.calcCal();
                        break;
                    case(4):
                        singleUser.calcMacro();
                        break;
                }
                
                System.out.print("\nWould you like to choose another(Y/N?: ");
                contin = in.next();
            }
            
        }
}
