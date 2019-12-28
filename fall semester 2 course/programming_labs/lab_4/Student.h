#pragma once

#include <vector>
#include <string>

class Student
{
public:
    enum Marks
    {
        Unsatisfactory = 2,
        Satisfactory = 3,
        Good = 4,
        Excellent = 5,
    };

public:
    Student() = default;
    explicit Student(std::string const& fullName, unsigned short groupNumber);
    explicit Student(std::string const& fullName, unsigned short groupNumber, std::vector<unsigned short> const& rating);

    void operator <<(unsigned short mark);
    void operator <<(std::vector<Marks> const& rating);
    void operator >>(std::vector<Marks> & rating) const;

    [[ nodiscard ]] std::string                 getFullName()    const;
    [[ nodiscard ]] unsigned short              getGroupNumber() const;
    [[ nodiscard ]] std::vector<unsigned short> getRating()      const;

    void setFullName(const char* fullName);
    void setGroupNumber(unsigned short groupNumber);
    void setRating(std::vector<unsigned short> const& rating);

    ~Student() = default;

private:
    unsigned short     _groupNumber { };
    std::string        _fullName { };
    std::vector<Marks> _rating { };
};
