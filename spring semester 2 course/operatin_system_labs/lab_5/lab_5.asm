data segment para public 'data'
    message_input db 'Input data =', 13, 10, '$'
    message_output db 'Output data =', 13, 10, '$'
    
    matrix dd  1, 2, 3
           dd  5, 152, 7
           dd  9, 2, 13
          
    nSize = 3
data ends 

stk segment stack
    db 256 dup ('?')
stk ends

code segment para 'code'
    main proc 
        assume cs:code, ds:data, ss:stk
        
        mov ax, data
        mov ds, ax
        
        mov dx, offset message_input
        mov ah, 9h
        int 21h
        
        mov bx, offset matrix
        
        call print_matrix
        
        xor cx, cx
        
        mov bx, offset matrix
        mov ch, offset nSize
        
        out_loop_task:
            mov cl, offset nSize

            inner_loop_task:
                mov ax, [bx]
                
                test ax, 1h
                jnz if_odder
                mov [bx], 0h
                
                if_odder:
                    add bx, 4h
                    dec cl
                    jnz inner_loop_task

            dec ch
            jnz out_loop_task
        
        mov dx, offset message_output
        mov ah, 9h
        int 21h
        
        mov bx, offset matrix
        call print_matrix
            
        mov ax, 4c00h
        int 21h
    main endp
    
    print_decimal proc
        assume cs:code, ds:data, ss:stk

        push dx
        push bx
        push cx
        
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
    
    print_matrix proc
        assume cs:code, ds:data, ss:stk
    
        push ax
        push dx

        xor cx, cx
        
        mov ch, offset nSize
        
        out_loop:
            mov cl, offset nSize

            inner_loop:
                mov ah, 2h
                mov dl, 20h
                int 21h
                
                mov ax, [bx]
                
                call print_decimal
                
                add bx, 4h
                dec cl
                jnz inner_loop
            
            mov ah, 2h
            mov dl, 13
            int 21h
            
            mov dl, 10
            int 21h
            
            dec ch
            jnz out_loop
            
        pop dx
        pop ax

        ret
    print_matrix endp
code ends
end main
