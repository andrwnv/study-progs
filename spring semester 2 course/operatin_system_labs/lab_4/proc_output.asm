public output_proc

data segment para public 'data'
    text db 'Input two number w/o space:$'
    new_line db 13, 10, '$' ; 13&10 need for move cursor to start of program, btw when prog start we have hidden commands
data ends 

code segment para public 'code'
    assume cs:code, ds:data
    
    input_start:
    
    output_proc proc near
        push bp
    mov bp,sp
    
    mov ax, [bp+4]

        m3:
            mov cx, 10h ;(10h = 16 in dec)
            mov bx, ax
            
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
    mov sp,bp ;?????????????? sp
    pop bp ;?????????????? bp
    ret
    output_proc endp

code ends
end output_proc
