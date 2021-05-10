;var 5

data segment para public 'data'
    text db 'Input two number w/o space:$'
    new_line db 13, 10, '$'
data ends 

stk segment stack
    db 256 dup ('?')
stk ends

extrn input_proc:near
extrn output_proc:near

calc macro bx, ax, dx

    mov al, dh
    add al, dl
    
    xor ah, ah
    
    mov bh, 4h
    div bh
    
    ; reset first bit if it === 1
    test ax, 0000000000000001b
    jz m0 
    and ax, 1111111111111110b
    
    m0:
endm

code segment para 'code'
    main proc
        assume cs:code, ds:data, ss:stk
        
        push dx
        call input_proc
        xor dx, dx
        pop dx
        
        calc bx, ax, dx

        push ax
        xor ax, ax

        call output_proc

        mov ax, 4c00h
        int 21h
    main endp
code ends
end main
