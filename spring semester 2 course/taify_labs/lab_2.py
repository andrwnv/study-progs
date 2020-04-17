import copy

def prepare_input(input_str: str) -> str:
    return "K" + copy.deepcopy(input_str)

def get_condition(input_str: str, size: int) -> str:
    if len(input_str) > 2: return  None
 
    if 'Ka' in input_str: return 'L'
    if 'La' in input_str: return 'L'
    if 'Lb' in input_str: return 'L'

    return None

def check_str(input_str: str) -> bool:
    while len(input_str) >= 1:
        curr_statment = ''

        if input_str == 'D': 
            return True
        
        if len(input_str) == 2 and input_str == 'Lb': curr_statment = 'D'
        else: curr_statment = get_condition(input_str[0:2], len(input_str))

        if curr_statment is None:
            return False

        if len(curr_statment) > 1:
            for char in curr_statment:
                input_str = input_str[2:]
                input_str = char + input_str

                return check_str(input_str)
        else:
            if input_str == 'D': return True

            input_str = input_str[1:]
            input_str = curr_statment + input_str[1:]
        
        print(input_str)

    return True

if __name__ == '__main__':
    input_str = input('Enter your string: ')
    input_str = prepare_input(input_str)
    print(input_str)
    print(check_str(input_str))
