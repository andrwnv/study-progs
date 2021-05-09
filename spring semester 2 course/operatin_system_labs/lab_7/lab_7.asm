data segment para public 'data'
    arr db 2, 5 , 2, 5, 4, 5, 6, 5, 6
    arr_size=$-arr
data ends

stk segment stack
    db 256 dup ('?')
stk ends

print macro ax, dx
    mov dl, 30h
    add dl, al
    mov ah, 2h
    int 21h
endm

code segment para 'code'
    assume cs:code, ds:data, ss:stk, es: data
    main proc
    
    mov ax, data
    mov ds, ax
    mov es, ax
    
    xor cx, cx
    xor bx, bx
    xor dx, dx
    
    cld
    lea si, arr
    lea di, arr
    mov cx, arr_size
    
    arr_loop:
        lodsb
        
        test al, 1
        jnz if_odd
        xor ax, ax
        
        if_odd:
            stosb
            loop arr_loop
        
    exit:
        cld
        lea si, arr
        mov cx, arr_size
        
        print_loop:
            lodsb
            print ax, dx
            loop print_loop
            
        mov ax, 4c00h
        int 21h
     
    main endp
code ends
end main

