#pragma once

#include <list>
#include <chrono>

class File;

class Directory
{
public:
    Directory()  = default;
    ~Directory() = default;

    void printDirectoryContent() const;
    void deleteAfterTime(std::chrono::system_clock::time_point from);
    [[nodiscard]] File mostUsedFile() const;

private:
    std::list<File> _filesKeeper;
};
