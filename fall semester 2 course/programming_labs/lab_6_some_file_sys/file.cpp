#include "file.h"

#include <utility>

File::File() : _fileName("Untitled"),
               _createdDateTime(system_clock::now()),
               _editDateTime(_createdDateTime),
               _countOfTurns(0) { }

File::File(std::string  name) : _fileName(std::move(name)),
                                _createdDateTime(system_clock::now()),
                                _editDateTime(_createdDateTime),
                                _countOfTurns(0) { }

void File::setFileName(std::string const & file_name) { _fileName = file_name; }

std::string File::getFileName()
{
    ++_countOfTurns;
    return _fileName;
}

time_point<system_clock> File::getCreatedDateTime()
{
    ++_countOfTurns;
    return _createdDateTime;
}

time_point<system_clock> File::getEditDateTime()
{
    ++_countOfTurns;
    return _editDateTime;
}

unsigned File::getCountOfTurns() const { return _countOfTurns; }


