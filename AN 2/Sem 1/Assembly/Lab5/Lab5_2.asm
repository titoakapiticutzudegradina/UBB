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
    
    msgstart db 'Insert a string of characters: ', 0
    initialstring resb 20
    printresult db 'The new string is: ',0
    newstring resb 20
    x db 'X'

; our code starts here
segment code use32 class=code
    start:
        ; start msg
       
        
        push msgstart
        call [printf]
        add esp, 4*1
        
        ;read string
        
        push dword initialstring
        call [gets]
        add esp, 4*1

        
        
        ;change c with X
        mov ecx, 0
        mov ecx,15
        mov esi, 0
        mov edi, 0
        
        mov bl, 'm'
        mov dl,  'X'
        
        repet:
            mov al, [initialstring+ esi]
            cmp al,bl
            je change
            jne notchange
                change:
                    mov [newstring+edi], dl
                    inc edi
                    jmp final
                    notchange:
                        mov [newstring+edi],al
                        inc edi
                 final:
                    inc esi
        loop repet
        
        ;print result
        push printresult
        push dword newstring
        call [printf]
        add esp, 4*2
    
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
