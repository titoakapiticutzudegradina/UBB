bits 32 ; assembling for the 32 bits architecture

; declare the EntryPoint (a label defining the very first instruction of the program)
global start        

; declare external functions needed by our program
extern exit               ; tell nasm that exit exists even if we won't be defining it
import exit msvcrt.dll    ; exit is a function that ends the calling process. It is defined in msvcrt.dll
                          ; msvcrt.dll contains exit, printf and all the other important C-runtime specific functions

; our data is declared here (the variables needed by our program)
extern scanf, printf
import scanf msvcrt.dll
import printf msvcrt.dll
 
 ;A negative number is given. Display the value of the number in base 10 and in base 16 in the following format "a = <base 10>(base 10), a= <base 16>(base 16)
segment data use32 class=data
    ; ...
    formatdecimal db '%d', 0
    printresult db 'a = %d (base 10), a = %x (base 16)',0
    msgstart db 'Give a number: ', 0
    a dd 0

; our code starts here
segment code use32 class=code
    start:
        ; print the welcoming msg
        push dword msgstart
        call [printf]
        add esp, 4*1
        
        ;read the negative number a
        push dword a
        push formatdecimal
        call [scanf]
        add esp, 4*2
        
        ;print the result
        push dword [a]
        push dword [a]
        push printresult
        call [printf]
        add esp, 4*3
        
        
    
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
