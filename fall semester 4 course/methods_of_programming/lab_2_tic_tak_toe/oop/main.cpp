#include <iostream>

#include "src/GameController.h"

int main() {
    std::string firstPlayerName;
    std::string secondPlayerName;

    std::cout << "Input first player name: ";
    std::cin >> firstPlayerName;

    std::cout << std::endl << "Second first player name: ";
    std::cin >> secondPlayerName;
    std::cout << std::endl;

    GameController controller(Player{firstPlayerName, Player::Type::X}, Player{secondPlayerName, Player::Type::O});
    controller.run();
    controller.showWinner();

    return 0;
}
