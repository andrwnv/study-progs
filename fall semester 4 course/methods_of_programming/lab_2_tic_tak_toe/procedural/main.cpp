#include <iostream>
#include <array>

enum Players {
    O = 0,
    X
};

namespace GLOBAL_VARS {
    std::array<char, 9> BOARD{
            '0', '1', '2',
            '3', '4', '5',
            '6', '7', '8'
    };

    bool GAME_OVER = false;
    Players CURRENT_PLAYER;
}

void display_board() {
    std::cout << "\n\n-------------\n";

    for (auto index = 1; index < GLOBAL_VARS::BOARD.size() + 1; ++index) {
        if ((index - 1) % 3 == 0)
            std::cout << "| ";

        std::cout << GLOBAL_VARS::BOARD.at(index - 1) << " | ";

        if (index % 3 == 0)
            std::cout << std::endl << "-------------" << std::endl;
    }
}

uint8_t get_user_input() {
    std::string input_value;
    std::cout << "Now " << (GLOBAL_VARS::CURRENT_PLAYER == Players::X ? 'X' : 'O') << " choose: ";
    std::cin >> input_value;

    return std::stoi(input_value);
}

bool is_end_game() {
    char current_player_sym = GLOBAL_VARS::CURRENT_PLAYER == Players::X ? 'X' : 'O';

    return
        (GLOBAL_VARS::BOARD.at(0) == current_player_sym and GLOBAL_VARS::BOARD.at(1) == current_player_sym and GLOBAL_VARS::BOARD.at(2) == current_player_sym) or
        (GLOBAL_VARS::BOARD.at(3) == current_player_sym and GLOBAL_VARS::BOARD.at(4) == current_player_sym and GLOBAL_VARS::BOARD.at(5) == current_player_sym) or
        (GLOBAL_VARS::BOARD.at(6) == current_player_sym and GLOBAL_VARS::BOARD.at(7) == current_player_sym and GLOBAL_VARS::BOARD.at(8) == current_player_sym) or
        (GLOBAL_VARS::BOARD.at(0) == current_player_sym and GLOBAL_VARS::BOARD.at(3) == current_player_sym and GLOBAL_VARS::BOARD.at(6) == current_player_sym) or
        (GLOBAL_VARS::BOARD.at(1) == current_player_sym and GLOBAL_VARS::BOARD.at(4) == current_player_sym and GLOBAL_VARS::BOARD.at(7) == current_player_sym) or
        (GLOBAL_VARS::BOARD.at(2) == current_player_sym and GLOBAL_VARS::BOARD.at(5) == current_player_sym and GLOBAL_VARS::BOARD.at(8) == current_player_sym) or
        (GLOBAL_VARS::BOARD.at(0) == current_player_sym and GLOBAL_VARS::BOARD.at(4) == current_player_sym and GLOBAL_VARS::BOARD.at(8) == current_player_sym) or
        (GLOBAL_VARS::BOARD.at(2) == current_player_sym and GLOBAL_VARS::BOARD.at(4) == current_player_sym and GLOBAL_VARS::BOARD.at(6) == current_player_sym);
}

void show_winner() {
    std::cout << "Winner is " << (GLOBAL_VARS::CURRENT_PLAYER == Players::X ? 'X' : 'O') << std::endl;
}

int main() {
    GLOBAL_VARS::CURRENT_PLAYER = Players::X;
    uint8_t selected_position = 0;

    display_board();

    while (not GLOBAL_VARS::GAME_OVER) {
        selected_position = get_user_input();

        while (selected_position > 10) {
            std::cout << "Incorrect user input!" << std::endl;
            selected_position = get_user_input();
        }

        GLOBAL_VARS::BOARD.at(selected_position) = GLOBAL_VARS::CURRENT_PLAYER == Players::X
                                                   ? 'X'
                                                   : 'O';

        display_board();
        GLOBAL_VARS::GAME_OVER = is_end_game();

        if (not GLOBAL_VARS::GAME_OVER) {
            GLOBAL_VARS::CURRENT_PLAYER = GLOBAL_VARS::CURRENT_PLAYER == Players::X
                                          ? Players::O
                                          : Players::X;
        }
    }

    show_winner();

    return 0;
}
