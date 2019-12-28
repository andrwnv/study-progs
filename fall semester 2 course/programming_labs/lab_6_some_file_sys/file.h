#pragma once

#include <string>
#include <chrono>

using namespace std::chrono;

class File
{
public:
    File();
    explicit File(std::string );

    ~File()           = default;

    File(File &&)     = default;
    File(const File&) = default;

    void setFileName(std::string const&);

    [[ nodiscard ]] std::string              getFileName();
    [[ nodiscard ]] time_point<system_clock> getCreatedDateTime();
    [[ nodiscard ]] time_point<system_clock> getEditDateTime();
    [[ nodiscard ]] unsigned                 getCountOfTurns() const;

private:
    std::string _fileName;
    unsigned    _countOfTurns;

    time_point<system_clock> _createdDateTime;
    time_point<system_clock> _editDateTime;
};