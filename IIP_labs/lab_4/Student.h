#pragma once

#include <vector>

enum Marks {
    Unsatisfactory = 2,
    Satisfactory,
    Good,
    Excellent,
    Undefined
};

class Student
{
public:
    Student() = default;
    explicit Student(const char* full_name, unsigned short group_number);
    explicit Student(const char* full_name, unsigned short group_number, std::vector<unsigned short> const& rating);

    void operator <<(unsigned short mark);
    void operator <<(std::vector<Marks> const& rating);
    void operator >>(std::vector<Marks> & rating) const;

    [[nodiscard]] const char*                 getFullName()    const;
    [[nodiscard]] unsigned short              getGroupNumber() const;
    [[nodiscard]] std::vector<unsigned short> getRating()      const;

    void setFullName(const char* full_name);
    void setGroupNumber(unsigned short group_number);
    void setRating(std::vector<unsigned short> const& rating);

    ~Student() = default;

private:
    unsigned short     m_group_number{};
    const char*        m_full_name{};
    std::vector<Marks> m_rating;
};
