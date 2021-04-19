masm
model small 

.data
    message db 'Hello world, ASM!$'

.stack
    db 256 dup ('?')

.code
    main proc
        mov ax, @data
        mov ds, ax
        mov ah, 9
        mov dx, offset message

        int 21h
        mov ax, 4c00h
        int 21h

    main endp
end main
