#include <iostream>

#include "menu.h"
#include "User.h"

using namespace std;

void menu()
{
	int choice;
	char contin = 'Y';

	while (contin == 'y' || contin == 'Y')
	{

		static User singleUser; // Create an instance of User

		cout << " What would you like to do? " << endl
			<< "1. Calculate BMR. " << endl
			<< "2. Calculate TDEE." << endl
			<< "3. Find Needed Caloric Intake. " << endl
			<< "4. Calculate Macros. " << endl
			<< "Your Choice: ";

		cin >> choice;

		switch (choice)
		{
			case (1):
				singleUser.calcBMR();
				break;
			case (2):
				singleUser.calcTDEE();
				break;
			case (3):
				singleUser.calcCal();
				break;
			case(4):
				singleUser.calcMacro();
				break;
		}

		cout << endl << "Would you like to choose another? (Y/N)";
		cin >> contin;
	}
}
