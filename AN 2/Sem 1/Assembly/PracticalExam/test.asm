bits 32 ; assembling for the 32 bits architecture

; declare the EntryPoint (a label defining the very first instruction of the program)
global start        

; declare external functions needed by our program
extern exit               ; tell nasm that exit exists even if we won't be defining it
import exit msvcrt.dll    ; exit is a function that ends the calling process. It is defined in msvcrt.dll
                          ; msvcrt.dll contains exit, printf and all the other important C-runtime specific functions

; our data is declared here (the variables needed by our program)
extern scanf, printf, gets
import scanf msvcrt.dll
import printf msvcrt.dll
import gets msvcrt.dll
segment data use32 class=data
    ; ...
    formatdec db '%d',0
    amsg db 'a = ',0
    bmsg db 'b = ',0
    cmsg db 'c = ',0
    a dw 0
    b dw 0
    c dd 0
    

; our code starts here
segment code use32 class=code
    start:
        ; 
        push dword amsg
        call[printf]
        add esp ,4*1
        
        push dword a
        push formatdec
        call [scanf]
        add esp, 4*2
        
         push dword bmsg
        call[printf]
        add esp ,4*1
        
        push dword b
        push formatdec
        call [scanf]
        add esp, 4*2
        
         push dword cmsg
        call[printf]
        add esp ,4*1
        
        push dword c
        push formatdec
        call [scanf]
        add esp, 4*2
        
        mov ax, [a]
        mov bx, [b]
        imul bx; result dx:ax
        
        push dx
        push ax
        pop eax
        
        mov ecx, [c]
        add eax, ecx
    
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
