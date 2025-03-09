import socket
import threading
import os
import subprocess

threads = []


#Function that runs the command sent by the server
def commandRun(client_socket):
    #Sending a welcoming message to the clients
    msg = 'Hello client! Please send a command'
    client_socket.sendall(bytes(msg,'ascii'))

    #Getting the command and run it
    try:
        command = client_socket.recv(1024).decode()
        print("This is the command: ",command)
        #output = subprocess.check_output(command)
        #client_socket.send(output)
        output = subprocess.run(['cat','Test.txt'], stdout = subprocess.PIPE, shell = True)
        client_socket.send(output.stdout)
    except socket.error as msg:
        print("Error: ", msg.strerror)
        exit(-1)

#Main
if __name__ == '__main__':
    try:
        s = socket.socket(socket.AF_INET,socket.SOCK_STREAM)
        s.bind(('0.0.0.0',1234))
        s.listen(5)
    except socket.error as msg:
        print (msg.strerror)
        exit(-1)

    #Threads and while true
    while True:
        cs, addr_client = s.accept()
        t = threading.Thread(target = commandRun, args = (cs,))
        threads.append(t)
        t.start()
