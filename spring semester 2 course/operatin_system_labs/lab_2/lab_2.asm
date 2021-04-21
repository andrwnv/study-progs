data segment para public 'data'
    text db 'Input two number w/o space:$'
    new_line db 13, 10, '$' ; 13&10 need for move cursor to start of program, btw when prog start we have hidden commands
data ends 

stk segment stack
    db 256 dup ('?')
stk ends

code segment para 'code'
    main proc
        assume cs:code, ds:data, ss:stk
        
        ; Print text to console...
        mov ax, data
        mov ds, ax
        mov ah, 9h
        mov dx, offset text
        int 21h
        
        xor ax, ax
        xor dh, dh ; clear for sec num
        
        m0:
            ; input 1 num
            mov ah, 1h
            int 21h
            mov dl, al
            sub dl, 30h ; bcs in ASSCII
            cmp dl, 9h
            jle m1 ; if is num <= 9
            sub dl, 7h ; else -7 bcs asscii 
            
        m1:
            mov cl, 4h
            shl dl, cl
            int 21h
            sub al, 30h
            cmp al, 9h
            jle m2
            sub al, 7h
        
        m2:
            add dl, al
            cmp dh, 0h
            jne m3
            mov dh, dl
            loop m0
            
        m3:
            mov cx, 10h ;(10h = 16 in dec)
            mov bx, dx
            
            ; print new line
            mov ax, data
            mov ds, ax
            mov ah, 9h
            mov dx, offset new_line
            int 21h
            
        m4:
            xor dx, dx
            sal bx, 1
            adc dl, 30h
            mov ah, 02h
            int 21h
            loop m4
            
        mov ax, 4c00h
        int 21h
    main endp
code ends
end main
