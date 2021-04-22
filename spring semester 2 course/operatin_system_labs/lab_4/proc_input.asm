public input_proc

data segment para public 'data'
    text db 'Input two number w/o space:$'
    new_line db 13, 10, '$' ; 13&10 need for move cursor to start of program, btw when prog start we have hidden commands
data ends 

code segment para public 'code'
    assume cs:code, ds:data
    
    input_start:
    
    input_proc proc near
    push bp
    mov bp,sp   

        ; Print text to console...
        mov ax, data
        mov ds, ax
        mov ah, 9h
        mov dx, offset text
        int 21h
    
        mov cx, 1h
        xor dh, dh
        xor ax, ax
    
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
            jne exit
            mov dh, dl
            loop m0
            
        exit:
        mov sp,bp ;?????????????? sp
        pop bp ;?????????????? bp
    ret
    input_proc endp

code ends
end input_start
