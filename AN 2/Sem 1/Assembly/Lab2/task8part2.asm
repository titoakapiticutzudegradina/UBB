bits 32 ; assembling for the 32 bits architecture

; declare the EntryPoint (a label defining the very first instruction of the program)
global start        

; declare external functions needed by our program
extern exit               ; tell nasm that exit exists even if we won't be defining it
import exit msvcrt.dll    ; exit is a function that ends the calling process. It is defined in msvcrt.dll
                          ; msvcrt.dll contains exit, printf and all the other important C-runtime specific functions

; our data is declared here (the variables needed by our program)
segment data use32 class=data
    ; ...1/a+200*b-c/(d+1)+x/a-e = 0+6314000-0+15290-100000 = 6229290
    ;a,b -word c,d-byte e-double x-qword
    a dw 32700;5
    b dw 31570;2
    c db 120;10
    d db 127;1
    e dd 100000;15
    x dq 500000000;25
    aux dd 0
    r dd 0

; our code starts here
segment code use32 class=code
    start:
        ; we first compute 1/a RESULT IN EBX
        mov ax, 1 ; AX = 1
        cwd
        idiv word[a]; AX = 1/5 = 0 quotient DX = 1/5 = 1 remeinder
        mov bx, ax ; BX = AX =0
        movsx ebx, bx
        
        ;we compute 200*b RESULT IN aux
        mov ax, 200; AX = 200
        imul word[b] ; DX:AX = 400;
        mov word[aux+0],ax ; 
        mov word[aux+2],dx ; after this 2 commands we have aux = 400
        
        ;we compute d+1 RESULTS IN CL
        mov cl, [d]; CL = d = 1
        add cl, 1; CL = d + 1 = 2
        
        ;we compute c /(d +1) RESULTS IN AX
        mov al, [c];
        movsx ax, al; AX = AL = c = 10
        idiv cl; AL = 5 quotient AH = 0 remeinder
        movsx ax, al ; AX = AL = 5
        
        ; we compute 1/a + 200*b - c/(d + 1) = 395 RESULT IN EBX
        add ebx, [aux] ; BX = 1/a + 200*b = 0 + 400 = 400
        movsx eax, ax
        sub ebx, eax ; BX = 400 - c/(d +1) = 400 - 5 = 395
        
        ;we compute x/a RESULTS IN EAX
        mov eax, dword[x+ 0] ;EAX = first 4 bytes
        mov edx, dword[x+ 4] ;EDX = last 4 bytes
        movsx ecx, word[a] ; ECX = a = 5
        idiv ecx; EAX = 25/5 = 5 quotient EDX = 0 remeinder
        
        ;we get the final result 
        add ebx, eax; EBX = 395 + 5 =400
        sub ebx, [e] ; EBX = 400 - 15
        
        mov [r], ebx ; r = 385
    
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
