#ifndef USER_H
#define USER_H

class User
{
	private:
		double weight;
		double height;
		double tdee;
		double bmr;
		char   gender;
		int    age;
	public:
		User();                                          // Default Constructor
		User(double, double, double, double, char, int); // Parameterized Constructor


		// Independant Functions
		void calcBMR();
		void calcTDEE();
		void calcCal();
		void calcMacro();

		//Conversion Functions
		double convertToKG(double);
		double convertToCM(double);

		// BMR Formulas
		void calcMifflin();
		void calcKatch();

};

#endif USER_H