data segment para public 'data'
    arr db 1, 2, 3
        db 5, 152, 7
        db 9, 2, 13
    nSize=3
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
    
    mov ch, nSize
    arr_loop_1:
        mov cl, nSize
        
        arr_loop_2:
            lodsb
        
            test al, 1
            jnz if_odd
            xor ax, ax

            if_odd:
                stosb
                dec cl
                jnz arr_loop_2
        dec ch
        jnz arr_loop_1
        
    exit:
        cld
        lea si, arr

        mov ch, nSize
        arr_loop_12:
            mov cl, nSize

            arr_loop_22:
                mov ah, 2h
                mov dl, 20h
                int 21h
                
                lodsb
                call print_decimal

                dec cl
                jnz arr_loop_22
            
            mov ah, 2h
            mov dl, 13
            int 21h
            
            mov dl, 10
            int 21h
                
            dec ch
            jnz arr_loop_12
            
        mov ax, 4c00h
        int 21h
     
    main endp
    
    print_decimal proc
        assume cs:code, ds:data, ss:stk, es: data

        push dx
        push bx
        push cx
        
        xor ah, ah
        xor cx, cx
        mov bx, 0ah
        
        output_dec:
            xor dx, dx
            div bx
            push dx
            inc cx
            or ax, ax
            jne output_dec
        
        mov ah, 2h
        
        disp:
            pop dx
            or dl, 30h
            int 21h
            loop disp
        
        pop cx
        pop bx 
        pop dx

        ret
    print_decimal endp
code ends
end main

