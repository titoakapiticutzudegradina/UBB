bits 32 ; assembling for the 32 bits architecture

; declare the EntryPoint (a label defining the very first instruction of the program)
global start        

; declare external functions needed by our program
extern exit               ; tell nasm that exit exists even if we won't be defining it
import exit msvcrt.dll    ; exit is a function that ends the calling process. It is defined in msvcrt.dll
                          ; msvcrt.dll contains exit, printf and all the other important C-runtime specific functions

; our data is declared here (the variables needed by our program)

;Compute the doubleword D as follows, A word, B word, C word
;   the bits 8-15 are the same as the bits of C
;   the bits 0-7 are the same as the bits 8-15 of B
;   the bits 24-31 are the same as the bits 0-7 of A
;   the bits 16-23 are the same as the bits 8-15 of A
segment data use32 class=data
    ; ...
    a dw 0000_0001_0001_0001b ;a = 273
    b dw 0000_0111_1010_0001b ;b = 1953
    c dw 0000_1000_0100_0001b ;c = 2113
    d dd 0; 
    ;0001_0001_0000_0001_0000_1000_0000_0111

; our code starts here
segment code use32 class=code
    start:
        ; initialisation
        mov EBX, 0 ;the results will be in BX
        mov eax, 0
        
        ;operation 1: b8-15 D = b8-15 C
        mov AX, [c] 
        and eax,0000_0000_000_0000_1111_1111_0000_0000b ; we isolate the bits 8-15 EAX = 0000_0000_0000_0000_0000_1000_0000_0000b
        or ebx, eax; EBX0000_0000_0000_0000_0000_1000_0000_0000b
        
        ;operation 2: b0-7 D = b8-15 B
        mov eax,0
        mov ax, [b]
        and eax,0000_0000_0000_0000_1111_1111_0000_0000b ; we isolate the bits 8-15 EAX = 0000_0000_0000_0000_0000_0111_0000_0000b
        mov cl,8; the difference is 8 so we rotate to the right with 8
        ror eax, cl; EAX = 0000_0000_0000_0000_0000_0000_0000_0111b
        or ebx, eax; EBX = 0000_0000_0000_0000_0000_1000_0000_0111b
        
        ;operation 3: b24-31 D = b0-7A
        mov eax, 0
        mov ax,[a];AX = 0000_0001_0001_0001b 
        and eax, 0000_0000_0000_0000_0000_0000_1111_1111b ; EAX = 0000_0000_0000_0000_0000_0000_0001_0001b
        rol eax,24;EAX = 0001_0001_000_0000_0000_0000_0000_0000
        or ebx, eax;EBX =0001_0001_0000_0000_0000_1000_0000_0111b
        
        ;operation 4: b16-23 D = 8-15 A
        mov eax,0
        mov ax,[a];AX = 0000_0001_0001_0001b 
        and eax,0000_0000_0000_0000_1111_1111_0000_0000b ;EAX = 0000_0000_0000_0000_0000_0001_0000_0000b
        rol eax, 8;EAX = 0000_0000_0000_0001_0000_0000_0000_0000b
        or ebx,eax;EBX =0001_0001_0000_0001_0000_1000_0000_0111b
        
        mov [d],ebx ; D = 0001_0001_0000_0001_0000_1000_0000_0111
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
