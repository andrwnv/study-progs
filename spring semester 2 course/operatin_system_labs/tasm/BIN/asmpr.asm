.model small
.stack 256
.data
mes db 'Hello, All!', 0ah,0dh, '$'
.code
start:
mov ax,@data
mov ds,ax
lea dx,mes
mov ah,09h
int 21h
mov ax,4c00h
int 21h
end start