#include "file.h"

#include <utility>

File::File() : m_file_name("Untitled"),
               m_created_date_time(system_clock::now()),
               m_edit_date_time(m_created_date_time),
               m_count_of_turns(0) { }

File::File(std::string  name) : m_file_name(std::move(name)),
                                m_created_date_time(system_clock::now()),
                                m_edit_date_time(m_created_date_time),
                                m_count_of_turns(0) { }

void File::setFileName(std::string const & file_name) { m_file_name = file_name; }

std::string File::getFileName()
{
    ++m_count_of_turns;
    return m_file_name;
}

time_point<system_clock> File::getCreatedDateTime()
{
    ++m_count_of_turns;
    return m_created_date_time;
}

time_point<system_clock> File::getEditDateTime()
{
    ++m_count_of_turns;
    return m_edit_date_time;
}

unsigned File::getCountOfTurns() const { return m_count_of_turns; }


