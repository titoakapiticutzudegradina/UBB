bits 32 ; assembling for the 32 bits architecture

; declare the EntryPoint (a label defining the very first instruction of the program)
global start        

; declare external functions needed by our program
extern exit               ; tell nasm that exit exists even if we won't be defining it
import exit msvcrt.dll    ; exit is a function that ends the calling process. It is defined in msvcrt.dll
                          ; msvcrt.dll contains exit, printf and all the other important C-runtime specific functions

; our data is declared here (the variables needed by our program)

;A8 A string of bytes A is given. Construct string B only conatining only odd values of A. If A = 17,4,2,-2,-1 => B = 17,-1 
segment data use32 class=data
    ; ...
    a db 17, 4, 2, -2, -1
    len equ $-a; len = 5
    b times len db 0 

; our code starts here
segment code use32 class=code
    start:
        ; ...
        mov ECX, len; the loop will be repeted len times
        mov esi, 0 ;basically esi = i    ;mov esi, a
        mov edi, 0 ; edi = j                   ;mov edi, b
        
        repet:
            mov al, [a+esi] ;an element from a   ;lodsb
            cbw ; AX = AL for the division
            mov bl, 2
            idiv bl; in AL = quotient and in AH= 0/1
            cmp ah, 0
            JE not_add
            JNE add_nr
                not_add:
                    inc esi
                    jmp final
                add_nr:
                    mov al,[a+esi] ;bc we do not have the number:))
                    mov [b+edi], al ; we put the correct number in b
                    inc esi
                    inc edi
        final:
        loop repet
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
