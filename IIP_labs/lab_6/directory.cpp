#include "directory.h"

#include "file.h"

#include <algorithm>
#include <iostream>
#include <iomanip>
#include <ctime>

namespace
{
    time_t system_clock_to_time_t( std::chrono::system_clock::time_point t )
    {
        return std::chrono::system_clock::to_time_t(t);
    }
}

void Directory::print_directory_content() const
{
    std::cout << std::setw(15);
    for (auto file : m_files_keeper)
        std::cout << file.getFileName() << file.getCountOfTurns() << std::ctime(reinterpret_cast<const time_t *>(::system_clock_to_time_t(file.getCreatedDateTime())));
}

void Directory::deleteAfterTime(std::chrono::system_clock::time_point from)
{
    m_files_keeper.remove_if( [&from](File file) -> bool { return file.getCreatedDateTime() >= from; } );
}

File Directory::mostUsedFile() const
{
    return *std::max_element(m_files_keeper.begin(), m_files_keeper.end(), [](File const& a, File const& b) -> bool{
        return a.getCountOfTurns() < b.getCountOfTurns();
    });
}
