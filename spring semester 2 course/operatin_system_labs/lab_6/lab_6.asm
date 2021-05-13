.MODEL small
.stack 100h
.code

public _asmproc
_asmproc proc C matrix:word, nSize:word
    mov bx, matrix
    mov cx, nSize
    mov ax, nSize

    out_loop_task:
        mov cx, nSize

        inner_loop_task:
            test word ptr [bx], 1h
            jnz if_odder
            mov word ptr [bx], 0h
            
            if_odder:
                add bx, 2h
                dec cx
                jnz inner_loop_task

        dec ax
        jnz out_loop_task
    ret
_asmproc endp

end
