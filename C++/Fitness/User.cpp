#include <iostream>
#include <string>

#include "User.h"

using namespace std;

User::User()
{
	weight = -1.0;
	height = -1;
	tdee   = -1;
	bmr    = -1.0;
	gender = 'L';
	age    = -1;
};

User::User(double w, double h, double t, double b, char g, int a)
{
	weight = w;
	height = h;
	tdee   = t;
	bmr    = b;
	gender = g;
	age    = a;
};

void User::calcMifflin()
{
	if (gender == 'm' || gender == 'M')
		bmr = ((10 * weight) + (6.25 * height) - (5 * age) + 5);
	else if (gender == 'f' || gender == 'F')
		bmr = ((10 * weight) + (6.25 * height) - (5 * age) - 161);
};

void User::calcKatch()
{
	bmr = 370 + (21.6 * weight);
};

double User::convertToKG(double w)
{
	const double KG_CONV = 2.20462262185;

	w *= KG_CONV;

	return w;
};

double User::convertToCM(double h)
{
	const double CM_CONV = 2.54;

	h *= CM_CONV;

	return h;
};

void User::calcBMR()
{
	if (weight == -1)
	{
		char pick;

		if (weight == -1.0)
		{
			string units;

			cout << endl << "What is your weight: ";
			cin >> weight;

			cout << endl << "Is that in Pounds or Kilograms (LB/KG): ";
			cin >> units;

			if (units == "LB" || units == "LBS" || units == "lb"
				|| units == "lbs" || units == "pounds" || units == "Pounds")
				weight = convertToKG(weight);
		}

		if (height == -1)
		{
			string units;

			cout << endl << "What is your height: ";
			cin >> height;

			cout << endl << "Is that in Inches or Centimeters (IN/CM): ";
			cin >> units;

			if (units == "in" || units == "IN" || units == "In"
				|| units == "Inches" || units == "inches" || "inch")
				height = convertToCM(height);
		}

		cout << endl << "Would you like to pick your formula? (Y/N)";
		cin >> pick;


		if (pick == 'y' || pick == 'Y')
		{
			char form;

			cout << "Would you like to use the Mifflin-St Jeor Equation?"
				<< endl << "Or the Katch-McArdle Equation(M/K)";
			cin >> form;

			if (form == 'M')
			{
				cout << endl << "Are you Male or Female? (M/F)";
				cin >> gender;

				cout << endl << "Enter your age in Years: ";
				cin >> age;

				calcMifflin();
			}
			else if (form == 'K')
				calcKatch();
			else 
				cout << endl << "You need to enter either a K or a M" << endl;

		}
		else
		{
			cout << endl << "Are you Male or Female? (M/F)";
			cin >> gender;

			cout << endl << "Enter your age in Years: ";
			cin >> age;

			calcMifflin();
		}

		cout << endl << "Your BMR is: " << bmr << endl;
	}
};

void User::calcTDEE()
{
	if (tdee == -1)
	{
		int choice;

		cout << endl << "Select one of the following Options for your exercise: " << endl
			<< "1. No exercise (Desk Job / Sedentary)" << endl
			<< "2. 3x a Week" << endl << "3. 4x a week" << endl
			<< "4. 5x a Week" << endl << "5. 6x a week" << endl
			<< "6. 5x a Week(Intense)" << endl << "7. Every Day" << endl
			<< "8. Every Day (Intense) or Twice Daily" << endl
			<< "9. Daily Exercise + Physical Job" << endl
			<< "Your Choice: ";

		cin >> choice;

		calcBMR();

		switch (choice)
		{
		case (1) :
			tdee = bmr * 1.2;
			break;
		case(2) :
			tdee = bmr * 1.375;
			break;
		case(3) :
			tdee = bmr * 1.418;
			break;
		case(4) :
			tdee = bmr * 1.462;
			break;
		case(5) :
			tdee = bmr * 1.5;
			break;
		case(6) :
			tdee = bmr * 1.55;
			break;
		case(7) :
			tdee = bmr * 1.637;
			break;
		case(8) :
			tdee = bmr * 1.725;
			break;
		case(9) :
			tdee = bmr * 1.9;
			break;
		}

		cout << "Your TDEE is: " << tdee << endl;
	}
}
void User::calcCal()
{
	int choice;

	cout << endl << "Which of the following would you like to do?"
		<< endl << "1. Maintain Weight." << endl
		<< "2. Lose Weight. (Cut) " << endl
		<< "3. Gain Weight (Bulk)" << endl
		<< "Your Choice: ";

	cin >> choice;
	calcTDEE();
	switch (choice)
	{
	case(1) :
		cout << endl <<  "Eat " << tdee << " calories to Maintain.";
		break;
	case(2) :
		cout << endl << "Eat " << (tdee * .85) << " calories to cut.";
		break;
	case(3) :
		cout << endl << "Eat " << (tdee * 1.1) << "calories to bulk. ";

	}
}

void User::calcMacro()
{
	int choice;
	double protein, fat, carbs, cal;
	// Calculate Macros
	calcTDEE();

	protein = weight * 1.5; // Assuming 1.5g per kg

	fat = weight * .5;      // Assuming .5g per kg

	cout << endl << "Which of the following is your goal? "
		<< endl << "1. To Maintain Weight" << endl
		<< "2. To Gain Weight" << endl
		<< "3. To Lose Weight" << endl
		<< "Your Choice: ";
	cin >> choice;

	switch (choice)
	{
	case(1) :
		cal = tdee;
		break;
	case(2) :
		cal = tdee * 1.1;
		break;
	case(3) :
		cal = tdee * .85;
		break;
	}
	
	carbs = ((tdee - (protein * 4) - (fat * 9)) / 4);

	cout << endl << "You need to eat " << protein << " grams of protein. "
		<< endl << "And " << fat << " grams of fat. " << endl
		<< "And " << carbs << " grams of carbs.";
	

}