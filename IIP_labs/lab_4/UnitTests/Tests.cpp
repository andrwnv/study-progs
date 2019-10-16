#define CATCH_CONFIG_MAIN

#include <catch2/catch.hpp>

#include "../Student.h"

TEST_CASE ("Initialize test", "[Student tests]")
{
    Student st1("Andrew G.", 123);
    REQUIRE( st1.getFullName() == "Andrew G." );
    REQUIRE( st1.getGroupNumber () == 123 );

    Student st2("Andrew G.", 123, {2, 3, 4} );
    REQUIRE( st2.getFullName() == "Andrew G." );
    REQUIRE( st2.getGroupNumber () == 123 );

    auto test_rating = st2.getRating();
    for (size_t i = 0; i < test_rating.size (); ++i)
    {
        REQUIRE( test_rating[i] == i + 2 );
    }
}

TEST_CASE ("Operator<< test", "[Student tests]")
{
    Student st1("Andrew G.", 123);
    std::vector<Marks> test_rating = { Marks(2), Marks(3) };
    st1 << test_rating;
    st1 << 4;

    auto gotten_rating = st1.getRating();
    for (size_t i = 0; i < gotten_rating.size (); ++i)
    {
        REQUIRE( gotten_rating[i] == i + 2 );
    }
}

TEST_CASE ("Operator>> test", "[Student tests]")
{
    Student st1("Andrew G.", 123, {2, 3, 4} );
    std::vector<Marks> gotten_rating;
    st1 >> gotten_rating;

    for (size_t i = 0; i < gotten_rating.size (); ++i)
        REQUIRE( gotten_rating[i] == i + 2 );
}