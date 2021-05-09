data segment para public 'data'
    arr dd 2, 5 , 2, 5, 4, 5, 6, 5, 6
    arr_size dw 9
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
    assume cs:code, ds:data, ss:stk
    
    main proc
        mov ax, data
        mov ds, ax
        
        mov cx, arr_size
        
        mov si, offset arr 
        
        arr_loop:
            mov ax, [si]
            test al, 1
            jnz if_odd
            mov [si], 0
            
        if_odd:
            add si, 4h
            loop arr_loop
        
        prog_end:
            mov si, offset arr 
            mov cx, arr_size
            
            print_loop:
                mov ax, [si]          
                print ax, dx
                
                add si, 4h
                loop print_loop
        
            mov ax, 4c00h
            int 21h
    main endp
code ends
end main
