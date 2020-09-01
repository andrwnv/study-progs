const outputHolder = document.getElementById('output');
const startButton  = document.getElementById('start_button');
const inputString  = document.getElementById('input_string');

/*
    Example input:
        * 000101010 - correct.
        * 001010    - correct.
        * 0100      - incorrect.
*/

function printFrom(str, from_index) {
    let result = '';

    for (let index = from_index; index < str.length; index++) {
        result += str[index];
    }

    return result;
}

function checkLangRules() {
    function errorMessage() {
        outputHolder.innerHTML = 'Некорректная строка для данного языка!';
    }

    let q = 0;
    let value = inputString.value;
    
    if (value.length === 0) {
        outputHolder.innerHTML = 'Ничего не введено!';
    }

    if (!value.includes('0') && !value.includes('10')) {
        errorMessage();
        return;
    }

    let countOfTen  = value.match(new RegExp('10', 'g')).length;
    let countOfZero = value.match(new RegExp('0', 'g')).length - countOfTen;

    if (countOfTen !== countOfZero) {
        errorMessage();
        return;
    }

    let index = 0;

    if (value[index] === '0') {
        outputHolder.innerHTML += `[q${q}, ${value}, S`;
        index++;
    } else {
        errorMessage();
        return;
    }

    for (index; index < value.length; index++) {
        if (value[index] === '1' && value[index + 1] === '0') {
            q = 2;
            if (index !== value.length)
                outputHolder.innerHTML += `[q${q}, ${printFrom(value, index)}, B`;
        } else if (value[index] === '0') {
            if (q < 2 && value[index] === '0') {
                q = 1;
                outputHolder.innerHTML += `[q${q}, ${printFrom(value, index)}, AB`;
            }
        } else {
            errorMessage();
            return;
        }
    }

    outputHolder.innerHTML += '[q2, e, e]' + '<br>';
}

startButton.onclick.bind(checkLangRules);
