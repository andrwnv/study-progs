#!/bin/bash

help() {
    echo "./os_lab_2 [-b | -e] [file]"
    echo "WRITE FULL FILE PATH"
    echo "Options: "
    echo "  -h     Help instruction"
    echo "  -b     Write to beginning of file"
    echo "  -e     Write to end of file"
}

getUserData() {
    read -r -p "Enter your text (Use \n for new line): " user_text
    echo "$user_text"
}

if [ -z "$1" ] || [ -z "$2" ]; then help; exit 1; fi

if [[ ! "$2" ]]; then touch "$2"; fi

user_text="$(getUserData)"

case "$1" in 
    "-e")
        echo "$user_text" >> "$2"
        ;;
    "-b")
        # -e key for show special chars.
        echo "$user_text$(cat "$2" 2> /dev/null)" > "$2"
        ;;
    *) # unknown key.
        help
        exit 1
        ;;
esac