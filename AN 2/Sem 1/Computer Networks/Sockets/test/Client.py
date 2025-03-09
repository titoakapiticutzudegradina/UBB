import socket

if __name__ == '__main__':
    #Connecting to the server
    try:
        client_server = socket.socket(socket.AF_INET,socket.SOCK_STREAM)
        client_server = socket.create_connection(('localhost',1234))
    except socket.error as msg:
        print("Error: ", msg)
        exit(-1)

    #Sending client information
    while True:
        try:
            print("Enter 1 to register(you cannot register twice), 2 to get the all the peers and their possible operation")
            option = input()
            if option == '1':
                client_server.send('register'.encode('utf-8'))
                msg1 = client_server.recv(1024)
                msg1 = msg1.decode('utf-8')
                print(msg1)
                name = input()
                client_server.send(name.encode('utf-8'))
                msg2 = client_server.recv(1024)
                msg2 = msg2.decode('utf-8')
                print(msg2)
                operation = input()
                client_server.send(operation.encode('utf-8'))
            if option == '2':
                client_server.send('list'.encode('utf-8'))
                list1 = client_server.recv(1024)
                list1 = list1.decode('utf-8')
                print(list1)
                client_server.send('list'.encode('utf-8'))
                list2 = client_server.recv(1024)
                list2 = list2.decode('utf-8')
                print(list2)
        except socket.error as msg:
            print("Error: ", msg)
            exit(-1)
