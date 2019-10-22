#pragma once

#include <list>
#include <chrono>

class File;

class Directory
{
public:
    Directory()  = default;
    ~Directory() = default;

    void print_directory_content() const;
    void deleteAfterTime(std::chrono::system_clock::time_point from);
    [[nodiscard]] File mostUsedFile() const;

private:
    std::list<File> m_files_keeper;
};
