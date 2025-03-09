# Implement the Chat server example (see the link bellow) using UDP and TCP –only this time each client should contact the server just for registration. 
# All communication happens directly between the peers (clients) without passing trough the server. 
# Each client maintains an endpoint (TCP/UDP) with the server just for learning the arrival/departure of other clients. 
# You create a mesh architecture where all clients connect directly between each others.

import socket, struct, sys, threading, os

clients = []
threads = []

def receiving_messages(client):
    #receive messages from other client
    while True:
        message = client.recv(1024).decode('utf-8')
        print("Message: ", message)
        if message == 'exit':
            break

def sending_messages(client):
    #send messages to other client
    while True:
        message = input("Enter a message: ")
        client.send(message.encode('utf-8'))
        if message == 'exit':
            break

def get_client_list():
    global clients
    data, addr = s.recvfrom(1024)
    data = data.decode('utf-8')
    print(data)
    clients = data.split(',')
    print(clients)
def incoming_client_connection():
        print("Waiting for incoming connection...")
        s2 = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        ip = s.recv(1024).decode('utf-8')
        port = 1234
        addr = (ip, port)
        
        isbinded = False
        while not isbinded:
            try:
                s2.bind(addr)
                isbinded = True
            except Exception as msg:
                print("Error: ", msg)
                port += 1
                addr = (ip, port)

        s2.listen(5)
        print("Client credentials came in, waiting for client to connect...")

        connection, addr = s2.accept()
        print("Client connected: ", addr)
        while True:
            message = connection.recv(1024).decode('utf-8')
            print("Message: ", message)
            if message == 'exit':
                break
            message = input("Enter a message: ")
            connection.send(message.encode('utf-8'))
            if message == 'exit':
                break
        connection.close()
        s2.close()


s = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
if __name__=='__main__':
    try:
        s.connect(('192.168.0.52', 1234))
        s.send('register'.encode('utf-8'))
        name = input("Enter your name: ")     
        s.send(name.encode('utf-8'))
        response, _ = s.recvfrom(1024)
    except socket.error as msg:
        print("Error: ", msg.strerror)
    while True:
        option = input("Enter 1 to get the list of clients, 2 to connect to a client, 3 to exit, 4 to expect incoming connection: ")
        if option == '1':
            s.send('list'.encode('utf-8'))
            get_client_list()
        if option == '2':
            s.send('connect'.encode('utf-8'))
            client = input("Enter the name of the client you want to connect to: ")
            s.send(client.encode('utf-8'))
            ip, _ = s.recvfrom(1024)
            port, _ = s.recvfrom(1024)
            ip = ip.decode('utf-8')
            port = 1234
            addr = (ip, port)
            print(addr)
            s2 = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
            os.system('sleep 3')

            isconnected = False
            while not isconnected:
                try:
                    s2.connect(addr)
                    isconnected = True
                except Exception as msg:
                    print("Error: ", msg)
                    port += 1
                    addr = (ip, port)

            print("Connected to: ", addr)
            while True:
                message = input("Enter a message: ")
                s2.send(message.encode('utf-8'))
                if message == 'exit':
                    break
                message = s2.recv(1024).decode('utf-8')
                print("Message: ", message)
                if message == 'exit':
                    break
            s2.close()
        if option == '3':
            s.send('EXIT'.encode('utf-8'))
            break
        if option == '4':
            incoming_client_connection()

    s.close()
    exit(0)
