.MODEL small
.stack 100h
.code

public _asmproc
_asmproc proc C arr:word, arr_size:word
    mov cx, arr_size
    mov bx, arr

    arr_loop:
        test word ptr [bx], 1
        jnz if_odd
        jmp if_even
    
    if_odd:
        mov word ptr [bx], 0
        
    if_even:
        add bx, 2h
        loop arr_loop

    ret
_asmproc endp
end
