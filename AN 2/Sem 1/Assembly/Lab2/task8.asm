bits 32 ; assembling for the 32 bits architecture

; declare the EntryPoint (a label defining the very first instruction of the program)
global start        

; declare external functions needed by our program
extern exit               ; tell nasm that exit exists even if we won't be defining it
import exit msvcrt.dll    ; exit is a function that ends the calling process. It is defined in msvcrt.dll
                          ; msvcrt.dll contains exit, printf and all the other important C-runtime specific functions

; our data is declared here (the variables needed by our program)
segment data use32 class=data
    ; ...need to solve (100*a+d+5-75*b)/(c-5) first expresion (12700 + 3600+5-9450)/120 = 6855/120=57
    ; a,b,c -byte d-word
     a db 127
     b db 126
     c db 125
     d dw 3600
     r dd 0
    
; our code starts here
segment code use32 class=code
    start:
        ; first we compute 100*a THIS RESULT IS IN BX
        mov al, 100 ; AL = 100
        imul byte[a] ; AX = 100*a = 100*2 = 200
        mov bx, ax ; BX = AX =200

        ;we compute 75*b THIS RESULT IS IN AX
        mov al, 75 ; AL = 75
        imul byte[b] ;AX = 75*b = 75*3 = 225
        
        ;we compute (100*a+d+5-75*b) THIS RESULT IS IN AX
        add bx,[d] ; BX = 100*a+d = 200 + 5 = 205
        add bx, 5; ;BX = 210;
        sub bx, ax; BX = -15
        mov ax, bx ; AX = BX = -15
        
        ;we compute (c - 5) THIS RESULT IS IN BX
        mov bl,[c] ; BL = c = 6
        sub bl, 5; BL = c- 5 = 1
        movsx bx,bl; BX = 1
        
        ;we do the final division
        cwd
        idiv bx ; AX = -15/1 = -15 DX = 0;
        movsx eax, ax ; EAX = AX = -15
        mov [r], eax ; r = -15
        
      
    
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
