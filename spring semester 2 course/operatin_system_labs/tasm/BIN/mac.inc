
; GetStr Buf,MaxLen ################################### #########################
; Ввод строки произвольной длины (функция 0ah int 21h).
; На входе:
;	Buf+1 - адрес строки куда будет помещен ввод
;	MaxLen - максимальная длина вводимой строки
; На выходе - введенная строка по адресу Buf
;	ax - длина введенной строки

GetStr	macro	Buf,MaxLen
local	m,TmpBuf
jmp	m
TmpBuf	label	byte	;временный буфер форматом для функции 0ah (int 21h)
rept	MaxLen+3	;доп. три байта - служебная информация
db	' '
endm
m:
SaveReg	<ds,es,dx,cx>
xor	cx,cx
mov	cs:TmpBuf,MaxLen+1
mov	ah,0ah
push	ds
pop	es
push	cs
pop	ds
lea	dx,cs:TmpBuf
int	21h
mov	al,cs:TmpBuf+1 ;пересылка TmpBuf в Buf со сдвигом влево (для удаления служебного символа)
mov	cl,al	
xor ah,ah
inc cl ;длина введенной строки
inc al ;длина введенной строки
lea	si,cs:TmpBuf+2	;откуда - ds:si
lea	di,buf	;куда - esi
rep	movsb
LoadReg	<cx,dx,es,ds>
endm