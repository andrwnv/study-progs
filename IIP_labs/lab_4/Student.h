#pragma once

#include <vector>
#include <string>

enum Marks {
    Unsatisfactory = 2,
    Satisfactory = 3,
    Good = 4,
    Excellent = 5,
};

class Student
{
public:
    Student() = default;
    explicit Student(std::string const& full_name, unsigned short group_number);
    explicit Student(std::string const& full_name, unsigned short group_number, std::vector<unsigned short> const& rating);

    void operator <<(unsigned short mark);
    void operator <<(std::vector<Marks> const& rating);
    void operator >>(std::vector<Marks> & rating) const;

    [[nodiscard]] std::string                 getFullName()    const;
    [[nodiscard]] unsigned short              getGroupNumber() const;
    [[nodiscard]] std::vector<unsigned short> getRating()      const;

    void setFullName(const char* full_name);
    void setGroupNumber(unsigned short group_number);
    void setRating(std::vector<unsigned short> const& rating);

    ~Student() = default;

private:
    unsigned short     m_group_number{};
    std::string        m_full_name{};
    std::vector<Marks> m_rating;
};
