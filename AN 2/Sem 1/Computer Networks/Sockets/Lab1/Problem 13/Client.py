'''13.The client sends a small text file to the server. The server saves
the file and returns the length
of the received file content as an unsigned integer'''

import socket

if __name__ == '__main__':
    #Creating the client socket
    client_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

    #Asking for the file name
    print("Please enter file name: ")
    file_name = input()

    #Connectiong to the server
    client_socket.connect(('localhost',1234))

    #Sending the data to the server
    client_socket.send(file_name.encode())

    #Receving the lenght of the file
