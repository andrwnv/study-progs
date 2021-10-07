#pragma once

#include <string>
#include <utility>

class Player {
public:
    enum class Type {
        X = 0,
        O
    };

    explicit Player(std::string  name, Type type)
        : _name(std::move(name))
        , _type(type) { }

    Player() = default;
    ~Player() = default;

    [[nodiscard]] std::string name() const noexcept { return _name; }
    void setName(std::string const& name) noexcept { _name = name; }

    [[nodiscard]] Type type() const noexcept { return _type; }
    void setType(Type type) noexcept { _type = type; }

private:
    std::string _name;
    Type        _type;
};
