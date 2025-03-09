import socket

if __name__ =='__main__':
    #Creating the client socket
    try:
        cs = socket.create_connection(('localhost',1234))
    except socket.error as msg:
        print(msg.strerror)
        exit(-1)

    #Getting the hello message and send the data
    hellomsg = cs.recv(1024)
    print(hellomsg.decode('ascii'))

    command = input()
    try:
        cs.sendall(command.encode())
        answer = cs.recv(1024)
        print(answer.decode())
    except socket.error as msg:
        print(msg.strerror)
        exit(-1)
