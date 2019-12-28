#include "directory.h"

#include "file.h"

#include <algorithm>
#include <iostream>
#include <iomanip>
#include <ctime>

namespace
{
    time_t systemClockToTimeType(std::chrono::system_clock::time_point t)
    {
        return std::chrono::system_clock::to_time_t(t);
    }
}

void Directory::printDirectoryContent() const
{
    std::cout << std::setw(15);
    for (auto file : _filesKeeper)
        std::cout << file.getFileName()
                  << file.getCountOfTurns()
                  << std::ctime( reinterpret_cast<const time_t *>(::systemClockToTimeType( file.getCreatedDateTime() )) );
}

void Directory::deleteAfterTime(std::chrono::system_clock::time_point from)
{
    _filesKeeper.remove_if( [&from](File file) -> bool { return file.getCreatedDateTime() >= from; } );
}

File Directory::mostUsedFile() const
{
    return *std::max_element(_filesKeeper.begin(), _filesKeeper.end(), [](File const& a, File const& b) -> bool {
        return a.getCountOfTurns() < b.getCountOfTurns();
    });
}
