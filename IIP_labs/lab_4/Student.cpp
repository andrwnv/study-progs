#include "Student.h"

Student::Student(const char *full_name, unsigned short group_number)
                : m_full_name(full_name), m_group_number(group_number) { }

Student::Student(const char *full_name, unsigned short group_number, std::vector<unsigned short> const& rating)
                : Student::Student (full_name, group_number)
{
    setRating(rating);
}

void Student::operator <<(unsigned short mark)
{
    if (mark != Marks::Undefined)
        m_rating.push_back(Marks(mark));
}

void Student::operator <<(std::vector<Marks> const &rating) { m_rating.insert(m_rating.end(), rating.begin(), rating.end()); }

void Student::operator >>(std::vector<Marks> & rating) const { rating.insert(rating.end(), m_rating.begin(), m_rating.end()); }

const char *Student::getFullName() const { return m_full_name; }

unsigned short Student::getGroupNumber() const { return m_group_number; }

std::vector<unsigned short> Student::getRating() const
{
    std::vector<unsigned short> rating;
    for (auto const& mark : m_rating)
        rating.push_back(static_cast<unsigned short>(mark));

    return rating;
}

void Student::setGroupNumber(unsigned short group_number) { m_group_number = group_number; }

void Student::setFullName(const char *full_name) { m_full_name = full_name; }

void Student::setRating(std::vector<unsigned short> const &rating)
{
    for (auto const & mark : rating)
        m_rating.push_back(Marks(mark));
}
