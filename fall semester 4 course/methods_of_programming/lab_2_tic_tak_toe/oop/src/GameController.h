#pragma once

#include "Player.h"
#include "Board.h"

class GameController {
public:
    GameController(Player const &player, Player const &opponent) {
        _players.first = player;
        _players.second = opponent;
    }

    void run() noexcept {
        uint8_t selected_position;
        char player_sym;

        _board.display();

        while (not _isGameOver) {
            player_sym = _players.first.type() == Player::Type::X ? 'X' : 'O';
            selected_position = _get_user_input();

            while (selected_position > 10) {
                std::cout << "Incorrect user input!" << std::endl;
                selected_position = _get_user_input();
            }

            _board.update(selected_position, player_sym);
            _board.display();

            _isGameOver = _board.isPlayerWin(_players.first);
            if (not _isGameOver) {
                std::swap(_players.first, _players.second);
            }
        }
    }

    void showWinner() const noexcept {
        std::cout << "Winner is " << _players.first.name() << std::endl;
    }

private:
    [[nodiscard]] uint8_t _get_user_input() const noexcept {
        std::string input_value;
        std::cout << "Now " << _players.first.name() << " choose: ";
        std::cin >> input_value;

        return std::stoi(input_value);
    }

private:
    Board _board{};
    std::pair<Player, Player> _players{};

    bool _isGameOver = false;
};
