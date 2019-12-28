#include "Student.h"

#include <algorithm>
#include <iostream>
#include <sstream>
#include <array>

void task_1()
{
    std::cout << "TASK - 1" << std::endl << std::endl;

    std::array<Student, 2> stud_base;

    for (auto & i : stud_base)
    {
        std::string tmp;

        std::cout << "Student full name: ";
        std::getline(std::cin, tmp);
        i.setFullName(tmp.c_str());

        std::cout << "Student group number: ";
        std::getline(std::cin, tmp);
        i.setGroupNumber (stoul(tmp));

        std::cout << "Student marks: ";
        std::getline(std::cin, tmp);

        std::istringstream str_input(tmp);

        std::string pars_tmp;
        while (std::getline(str_input, pars_tmp, ' '))
        {
            i << stoul(pars_tmp);
        }
    }

    std::sort( stud_base.begin(), stud_base.end(), [](Student const& a, Student const& b) -> bool {
        unsigned aprox_a = 0;
        for (auto const& mark : a.getRating())
            aprox_a += mark;
        aprox_a /= a.getRating().size();

        unsigned aprox_b = 0;
        for (auto const& mark : b.getRating())
            aprox_b += mark;
        aprox_b /= b.getRating().size();

        return aprox_a < aprox_b;
    });

    for (auto & i : stud_base)
        std::cout << i.getFullName() << std::endl;

    std::cout << std::endl;
}

void task_2()
{
    std::cout << "TASK - 2" << std::endl << std::endl;

    std::array<Student, 10> stud_base { Student( "Aretha Orellana", 123, { 2, 3, 4 } ), Student( "Tula Vaughn",   567, { 2, 4, 2 } ),
                                        Student( "Youlanda Ram",    345, { 4, 4, 5 } ), Student( "Indira Minks",  456, { 5, 3, 2 } ),
                                        Student( "Josh Krol",       345, { 2, 3, 5 } ), Student( "Le Griffie",    789, { 5, 5, 5 } ),
                                        Student( "Stephan Moring",  234, { 4, 4, 5 } ), Student( "Moira Towers",  666, { 4, 4, 4 } ),
                                        Student( "Bobbi Mears",     123, { 5, 5, 4 } ), Student( "Brent Cordero", 999, { 4, 4, 5 } ) };

    bool havePositiveMarks = false;

    for (auto const& student : stud_base)
    {
        auto stud_rating = student.getRating();
        bool findNegativeRes = std::any_of(stud_rating.begin(), stud_rating.end(), [](auto const& item) -> bool {
            return item == Student::Marks::Satisfactory or item == Student::Marks::Unsatisfactory;
        });

        if (!havePositiveMarks and !findNegativeRes)
            havePositiveMarks = true;

        if (!findNegativeRes)
            std::cout << student.getFullName() << "\t" << student.getGroupNumber() << std::endl;
    }

    if (!havePositiveMarks)
        std::cout << "In current stud base doesnt have positive marks" << std::endl;
}

int main()
{
    task_1();
    std::cout << std::endl;
    task_2();

    return 0;
}