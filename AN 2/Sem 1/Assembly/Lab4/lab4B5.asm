bits 32 ; assembling for the 32 bits architecture

; declare the EntryPoint (a label defining the very first instruction of the program)
global start        

; declare external functions needed by our program
extern exit               ; tell nasm that exit exists even if we won't be defining it
import exit msvcrt.dll    ; exit is a function that ends the calling process. It is defined in msvcrt.dll
                          ; msvcrt.dll contains exit, printf and all the other important C-runtime specific functions

; our data is declared here (the variables needed by our program)

;A string of words S is given. Compute string D conatining only low bytes multiple of 9 from string S. If S = 3812h, 5678h, 1a09h => D = 12h, 09h
segment data use32 class=data
    ; ...
    s dw 3812h, 5678h, 1a09h
    len equ ($-s)/2 ;the length of string s
    d times len db 0

; our code starts here
segment code use32 class=code
    start:
        ; ...
        mov ecx, len
        mov esi, 0
        mov edi, 0
        
        repet:
                mov ax, [s + esi] ;we put an elem in ax
                cbw ; so we only take the low byte
                mov bl, 9
                idiv bl; in AH = remeinder
                cmp ah, 0
                JE yey ;we add the number
                JNE next ;we do not add the number
                        yey: 
                                mov ax, [s + esi]
                                mov [d + edi], al
                                add esi, 2
                                inc edi
                                jmp final
                        next:
                            add esi, 2
        final:
        loop repet
        
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
