#include "Student.h"

Student::Student(std::string const& fullName, unsigned short groupNumber)
                : _fullName(fullName), _groupNumber(groupNumber) { }

Student::Student(std::string const& fullName, unsigned short groupNumber, std::vector<unsigned short> const& rating)
                : Student::Student (fullName, groupNumber)
{
    setRating(rating);
}

void Student::operator <<(unsigned short mark)
{
    if (mark > 1 and mark < 6)
        _rating.push_back(Marks(mark));
}

void Student::operator <<(std::vector<Marks> const &rating) { _rating.insert(_rating.end(), rating.begin(), rating.end()); }

void Student::operator >>(std::vector<Marks> & rating) const { rating.insert(rating.end(), _rating.begin(), _rating.end()); }

std::string Student::getFullName() const { return _fullName; }

unsigned short Student::getGroupNumber() const { return _groupNumber; }

std::vector<unsigned short> Student::getRating() const
{
    std::vector<unsigned short> rating;
    for (auto const& mark : _rating)
        rating.push_back( mark );

    return rating;
}

void Student::setGroupNumber(unsigned short groupNumber) { _groupNumber = groupNumber; }

void Student::setFullName(const char *fullName) { _fullName = fullName; }

void Student::setRating(std::vector<unsigned short> const &rating)
{
    for (auto const & mark : rating)
        _rating.push_back(Marks(mark));
}
