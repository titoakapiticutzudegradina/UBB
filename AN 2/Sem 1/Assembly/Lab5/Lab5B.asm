bits 32 ; assembling for the 32 bits architecture

; declare the EntryPoint (a label defining the very first instruction of the program)
global start        

; declare external functions needed by our program
extern exit               ; tell nasm that exit exists even if we won't be defining it
import exit msvcrt.dll    ; exit is a function that ends the calling process. It is defined in msvcrt.dll
                          ; msvcrt.dll contains exit, printf and all the other important C-runtime specific functions

extern scanf, printf, gets
import scanf msvcrt.dll
import printf msvcrt.dll
import gets msvcrt.dll

; our data is declared here (the variables needed by our program)
;Read a string. Extract all small characters and save them into a second string. Print the second string 
segment data use32 class=data
    msgstart db 'Insert a string of characters: ', 0
    initialstring resb 20
    fromatstring db '%s', 0
    printresult db 'The new string is: ',0
    newstring resb 20
 

; our code starts here
segment code use32 class=code
    start:
        ; print the initial msg
        push msgstart
        call [printf]
        add esp, 4*1
        
        ;read the initial string
        push dword initialstring
        call [gets]
        add esp, 4*1
       
       ;take the small characters from the string
       mov ecx, 20 ; the max number of characters
       mov esi, 0
       mov edi,0
       
       repet:
            mov al, [initialstring + esi]
            cmp al , 'a'
            jnl probadd
            jl final
                probadd:
                     cmp al, 'z'
                     jle addchar
                     jnle final
                        addchar:
                            mov [newstring + edi], al
                            inc edi
                            ;inc esi
                 final:
                    inc esi
       loop repet
       
       ;print the result
       push dword printresult
       push dword newstring
       call [printf]
       add esp, 4*2
       
        
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
