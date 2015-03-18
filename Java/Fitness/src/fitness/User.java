/*
 * Copyright (C) 2015 ObamaNYoMama
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package fitness;

import java.util.Scanner;

public class User{
   private double  weight;
   private double  height;
   private double  bmr;
   private double  tdee;
   private String  gender;
   private int     age;
   
   User(){
       weight = -1.0;
       height = -1.0;
       tdee   = -1.0;
       bmr    = -1.0;
       gender = "L";
       age    = -1;
   };
   
   // Functions for TDEE Calculation
   void calcMifflin()
   {
      if ("m".equals(gender) || "M".equals(gender))
          bmr = ((10 * weight) + (6.25 * height) - (5 * age) + 5);
      else if ("f".equals(gender) || "F".equals(gender))
          bmr = ((10 * weight) + (6.25 * height) - (5 * age) - 161);
      else
          System.out.println("You entered an invalid gender!");
      
   };
   void calcKatch()
   {
       bmr = 370 + (21.6 * weight);
   };
   
   double convertToKG(double w)
   {
     double KG_CONV = 2.20462262185;
     
     w *= KG_CONV;
     
     return w;
   };
   
   double convertToCM(double h)
   {
       double CM_CONV = 2.54;
       
       h *= CM_CONV;
       
       return h;
   };
   
   void calcBMR()
   {
       if (weight == -1)
       {
           Scanner in = new Scanner(System.in);
           String units, pick;
           
           System.out.println("\nWhat is your weight: ");
           weight = in.nextDouble();
           
           System.out.println("\nIs that in Pounds or Kilograms? (LB/KG): ");
           units = in.next();
           
           if ("LB".equals(units) || "LBS".equals(units) || "lb".equals(units)
                   || "lbs".equals(units) || "pounds".equals(units) || 
                   "Pounds".equals(units))
               weight = convertToKG(weight);
           
           System.out.println("\n What is your height: ");
           height = in.nextDouble();
           
           System.out.println("Is that in Inches or Centimeters? (IN/CM): ");
           units = in.next();
           
           if ("in".equals(units) || "IN".equals(units) || "In".equals(units)
                   || "Inches".equals(units) || "inches".equals(units)
                   || "inch".equals(units))
               height = convertToCM(height);
           
           System.out.println("\nWould you like to pick your formula(Y/N): ");
           pick = in.next();
           
           if ("y".equals(pick) || "Y".equals(pick))
           {
               String form;
               
               System.out.println("\nWould you like to use the Mifflin-St Jeor Equation?");
               System.out.println("\nOr the Katch-McArdle Equation(M/K): ");
               form = in.next();
               
               if ("M".equals(form) || "m".equals(form))
               {
                   System.out.println("\nAre you Male or Female? (M/F): ");
                   gender = in.next();
                   
                   System.out.println("\nEnter your age in Years: ");
                   age    = in.nextInt();
                   
                   calcMifflin();
               }
               else if ("k".equals(form) || "K".equals(form))
                   calcKatch();
               else
                   System.out.println("\nYou need to enter either a K or a M!");
           }
           else
           {
               System.out.println("\nAre you Male or Female? (M/F): ");
               gender = in.next();
                   
               System.out.println("\nEnter your age in Years: ");
               age    = in.nextInt();
                   
               calcMifflin();
           }
           
           System.out.print("\nYour BMR is: ");
           System.out.print(bmr);
               
       }
   }
   void calcTDEE()
   {
      if (tdee == -1)
      {
          int choice;
          Scanner in = new Scanner(System.in);
          
          System.out.println("\nSelect one of the following options.");
          System.out.println("\n1. No exercise (Desk Job / Sedentary)");
          System.out.println("\n2. 3x a Week");
          System.out.println("\n3. 4x a Week");
          System.out.println("\n4. 5x a Week");
          System.out.println("\n5. 6x a Week");
          System.out.println("\n6. 5x a Week (Intense)");
          System.out.println("\n7. Every day");
          System.out.println("\n8. Every Day (Intense) or Twice Daily");
          System.out.println("\n9. Daily Exercise + Physical Job");
          System.out.print("\nYour Choice: ");
          
          choice = in.nextInt();
                  
          calcBMR();
          
          switch (choice)
          {
              case (1):
                  tdee = bmr * 1.2;
                  break;
              case(2):
                  tdee = bmr * 1.375;
                  break;
              case(3):
                  tdee = bmr * 1.418;
                  break;
              case (4):
                  tdee = bmr * 1.462;
                  break;
              case (5):
                  tdee = bmr * 1.5;
                  break;
              case (6):
                  tdee = bmr * 1.55;
                  break;
              case (7):
                  tdee = bmr * 1.637;
                  break;
              case (8):
                  tdee = bmr * 1.725;
                  break;
              case (9):
                  tdee = bmr * 1.9;
                  break;
              default:
                  System.out.println("\nYou need to enter a number 1 - 9!");
          }
          
          System.out.print("\nYour TDEE is: ");
          System.out.print(tdee);
          System.out.print(" calories");
      }
   }
   void calcCal()
   {
       int choice;
       Scanner in = new Scanner(System.in);
       
       System.out.println("Which of the following would you like?");
       System.out.println("1. Maintain Weight.");
       System.out.println("2. Lose Weight (Cut)");
       System.out.println("3. Gain Weight (Bulk)");
       System.out.print("\nYour Choice: ");
       choice = in.nextInt();
       
       calcTDEE();
       
       switch(choice)
       {
           case (1):
               System.out.print("\nEat ");
               System.out.print(tdee);
               System.out.print(" calories to maintain.");
               break;
           case (2):
               System.out.print("\nEat ");
               System.out.print((tdee * .85));
               System.out.print(" calories to cut.");
               break;
           case (3):
               System.out.print("\nEat ");
               System.out.print((tdee * 1.1));
               System.out.print(" calories to bulk.");
               break;
           default:
               System.out.println("\nYou need to enter a value 1-3.");
       }
       
   }
   
   void calcMacro()
   {
       int choice;
       double protein, fat, carbs, cal;
       Scanner in = new Scanner(System.in);
       
       //Calculate Macros
       calcTDEE();
       
       protein = weight * 1.5; // Assuming 1.5g per kg
       fat     = weight * .5;  // Assuming .5g per kg
       
       System.out.println("\nWhich of the following is your goal?");
       System.out.println("1. To Maintain Weight");
       System.out.println("2. To Gain Weight");
       System.out.println("3. To Lose Weight");
       
       choice = in.nextInt();
       
       switch(choice)
       {
           case(1):
               cal = tdee;
               break;
           case(2):
               cal = tdee * 1.1;
               break;
           case(3):
               cal = tdee * .85;
               break;
           default:
               System.out.println("You need to enter a value 1-3");
       }
       carbs = ((tdee - (protein * 4) - (fat * 9)) / 4);
       
       System.out.print("You need to eat ");
       System.out.print(protein);
       System.out.print(" grams of protein.");
       System.out.print("\nAnd ");
       System.out.print(fat);
       System.out.print(" grams of fat.");
       System.out.print("\nAnd ");
       System.out.print(carbs);
       System.out.print(" grams of carbs.");
       
   }
}
