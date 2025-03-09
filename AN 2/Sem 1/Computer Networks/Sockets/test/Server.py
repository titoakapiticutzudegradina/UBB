import socket
import struct

clients = [] #list for activ peers\
client_count = 0

if __name__ =='__main__':
    #Socket creation
    try:
        server_socket = socket.socket(socket.AF_INET,socket.SOCK_STREAM)
        server_socket.bind(('0.0.0.0',1234))
        server_socket.listen(5)
    except socket.error as msg:
        print("Error: ", msg)
        exit(-1)
    print("Server is listening...")

    #Accepting connection for the peers and putting them in the list
    #client_socket, addrc = server_socket.accept()
    #client_count +=1
    while True:
        try:
            client_socket, addrc = server_socket.accept()
            client_count +=1
            data = client_socket.recv(1024)
            data = data.decode('utf-8')
            if data == 'register':
                msg1 = "Hello! Enter name: "
                client_socket.send(msg1.encode('utf-8'))
                name = client_socket.recv(1024)
                name = name.decode('utf-8')
                msg2 = "Please enter an operation you can do: "
                client_socket.send(msg2.encode('utf-8'))
                operation = client_socket.recv(1024)
                operation = operation.decode('utf-8')
                clients.append((operation, name, client_socket ,addrc ))
            elif data == 'list':
                list1 = ','.join([c[1] for c in clients]) #print the names
                list2 = ','.join([c[0] for c in clients]) #print the poss operations
                client_socket.send(list1.encode('utf-8'))
                client_socket.send(list2.encode('utf-8'))
        except socket.error as msg:
            print("Error: ", msg)
            exit(-1)
