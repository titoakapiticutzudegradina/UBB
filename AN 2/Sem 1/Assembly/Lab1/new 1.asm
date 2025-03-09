bits 32 ; assembling for the 32 bits architecture

; declare the EntryPoint (a label defining the very first instruction of the program)
global start        

; declare external functions needed by our program
extern exit               ; tell nasm that exit exists even if we won't be defining it
import exit msvcrt.dll    ; exit is a function that ends the calling process. It is defined in msvcrt.dll
                          ; msvcrt.dll contains exit, printf and all the other important C-runtime specific functions

; our data is declared here (the variables needed by our program)
;a-b+7-(c+d)
segment data use32 class=data
a dw 30
b db 10
c dw 50
d db 9
r dd 0
    ; ...

; our code starts here
segment code use32 class=code
    start:
    mov ax, [a]
    movzx bx, [b]
    sub ax,bx
    add ax,7
    mov cx,[c]
    movzx dx,[d]
    add cx,dx
    sub ax, cx
    mov [r],eax
    ; ...
    
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
