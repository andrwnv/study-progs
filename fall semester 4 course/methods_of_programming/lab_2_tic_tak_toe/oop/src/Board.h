#pragma once

#include <array>

#include "Player.h"


class Board {
public:
    Board() = default;
    ~Board() = default;

    void display() const noexcept {
        std::cout << "\n\n-------------\n";

        for (auto index = 1; index < _positions.size() + 1; ++index) {
            if ((index - 1) % 3 == 0)
                std::cout << "| ";

            std::cout << _positions.at(index - 1) << " | ";

            if (index % 3 == 0)
                std::cout << std::endl << "-------------" << std::endl;
        }
    }

    bool update(size_t index, char value) noexcept {
        if (index > 10)
            return false;

        _positions.at(index) = value;
        return true;
    }

    [[nodiscard]] bool isPlayerWin(Player const& player) const noexcept {
        auto player_sym = player.type() == Player::Type::X ? 'X' : 'O';

        return
            (_positions.at(0) == player_sym and _positions.at(1) == player_sym and _positions.at(2) == player_sym) or
            (_positions.at(3) == player_sym and _positions.at(4) == player_sym and _positions.at(5) == player_sym) or
            (_positions.at(6) == player_sym and _positions.at(7) == player_sym and _positions.at(8) == player_sym) or
            (_positions.at(0) == player_sym and _positions.at(3) == player_sym and _positions.at(6) == player_sym) or
            (_positions.at(1) == player_sym and _positions.at(4) == player_sym and _positions.at(7) == player_sym) or
            (_positions.at(2) == player_sym and _positions.at(5) == player_sym and _positions.at(8) == player_sym) or
            (_positions.at(0) == player_sym and _positions.at(4) == player_sym and _positions.at(8) == player_sym) or
            (_positions.at(2) == player_sym and _positions.at(4) == player_sym and _positions.at(6) == player_sym);
    }

    [[nodiscard]] char infoByPosition(size_t index) const noexcept { return _positions.at(index); }

private:
    std::array<char, 9> _positions{
            '0', '1', '2',
            '3', '4', '5',
            '6', '7', '8'
    };
};
