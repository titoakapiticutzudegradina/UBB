# Implement the Chat server example (see the link bellow) using UDP and TCP –only this time each client should contact the server just for registration. 
# All communication happens directly between the peers (clients) without passing trough the server. 
# Each client maintains an endpoint (TCP/UDP) with the server just for learning the arrival/departure of other clients. 
# You create a mesh architecture where all clients connect directly between each others.

import socket, struct, sys, threading, os

rs = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)

clients = []
threads = []

def client_master():
    global clients
    name, addr = rs.recvfrom(1024)
    name = name.decode('utf-8')
    if name in [c[0] for c in clients]:
        rs.sendto('!!!!Client already registered!!!!'.encode('utf-8'), addr)
        return
    if name not in clients:
        clients.append((name, addr))
        print("Client registered: ", name, " - ", str(addr[0])+":"+str(addr[1]))
        rs.sendto('Client registered'.encode('utf-8'), addr)
        return
    if name == 'EXIT':
        for c in clients:
            if c[1] == addr:
                clients.remove(c)
                print("Client removed: ", str(c[0]), " - ", str(c[1][0])+":"+str(c[1][1]))
                break
    #save and active clients list in a file for ease of reading
    with open ('clients.txt', 'w') as f:
        for c in clients:
            f.write(str(c[0]) + ' - ' + str(c[1][0])+":"+str(c[1][1]) + '\n')
            f.write("Total" + ' : ' + str(len(clients)))


#@requester is the address of the client that wants to connectd to another client
#@addr is the address of the client that requester wants to connect to
def client_connect_to_client(requester, name):
    global clients
    #connect a client to another client
    #send the address of client2 to client1 so client1 can directly connect to client2
    for c in clients:
        if c[0] == name:
            print ("Client found: ", c[0])
            ip = c[1][0].encode('utf-8')
            port = str(c[1][1]).encode('utf-8')
            rs.sendto(ip, c[1])
            rs.sendto(str(requester[1]).encode('utf-8'), c[1])
            rs.sendto(ip, requester)
            rs.sendto(port, requester)
            print("Informations sent to: ", requester, " - ", str(c[1][0])+":"+str(c[1][1]))
            return
    print("Client not found: ", name)


if __name__=='__main__':
    #try:
    rs.bind(('192.168.0.52', 1234))
    print("Server started")
    #except Exception as msg:
    #    print("Error: ", msg)
    #    exit(-1)

    while True:
        data, addr = rs.recvfrom(1024)
        data = data.decode('utf-8')
        if data == 'register':
            print ("Client registration : ", addr)
            client_master()
            print ("Total clients: ", len(clients))
        if data == 'list':
            data = ','.join([c[0] for c in clients])
            rs.sendto(data.encode('utf-8'), addr)
        if data == 'connect':
            print("Client connection request: ", addr)
            name, addr = rs.recvfrom(1024)
            name = name.decode('utf-8')
            print ("Client to connect to: ", name)
            client_connect_to_client(addr, name)
    rs.close()
    exit(0)