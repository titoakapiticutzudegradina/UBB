import socket
import threading
import random
import struct
import sys
import time

#Choosing a float number between 1 and 100
random.seed()
start = float(1); stop = float(100)
my_number = random.uniform(start, stop)
print("Server number is: ", my_number)

mylock = threading.Lock()

out_of_time = False
winner_thread = 0
e = threading.Event()
e.clear()
threads = []
client_count = 0
timeout = 10
client_data = [] #list of tuples (number, socket, client_id)

#The worker function
def worker(cs):
    global mylock, out_of_time, winner_thread, client_count, e

    my_idcount = client_count
    print('client #', client_count, 'from: ',cs.getpeername(),cs)
    message = 'Hello client #' + str(client_count)+ '! You are entering the number guess competion now! Send a float number.'
    cs.sendall(bytes(message,'ascii'))

    try:
        client_number = cs.recv(8)
        client_number=struct.unpack('!d',client_number)[0]
        mylock.acquire()
        client_data.append((client_number, cs, my_idcount))
        mylock.release()
    except socket.error as msg:
        print('Error:',msg.strerror)
    print("Worker Thread ",my_idcount, " end")


#Reset the game
def resetServer():
    global mylock, out_of_time, winner_thread, my_number,threads, e, client_count, client_data
    while True:
        e.wait()
        for i in threads:
            t.join()
        print("All threads are finished now")
        e.clear()
        #we need to notify the client about the winner
        min_dist = 100000000000000.0
        winner = None
        for (n, s, cid) in client_data:
            if abs(n - my_number) < min_dist:
                min_dist = abs(n - my_number)
                winner = (n, s, cid)
        if winner:
            for (_, s, cid) in client_data:
                if winner[2] == cid:
                    s.send("Congreats".encode('ascii'))
                else:
                    s.send("You lose!".encode('ascii'))


        mylock.acquire()
        threads = []
        out_of_time = False
        winner_thread = -1
        client_count = 0
        client_data.clear()
        my_number = random.uniform(start, stop)
        print('Server number: ', my_number)
        mylock.release()

#Main part
if __name__=='__main__':
    try:
        #Creating a socket
        s=socket.socket(socket.AF_INET,socket.SOCK_STREAM)
        s.bind(('0.0.0.0',1234))
        s.listen(5)
        s.settimeout(timeout) # will cause accept to throw an exception
    except socket.error as msg:
        print(msg.strerror)
        exit(-1)
    t = threading.Thread(target = resetServer, daemon = True)
    t.start()
    while True:
        try:
            client_socket, addrc = s.accept()
            t = threading.Thread(target = worker, args = (client_socket,))
            threads.append(t)
            client_count += 1
            t.start()
        except socket.timeout:
            print("time's out")
            #when we reached here no connection for timeout seconds
            e.set() # reset the event so wakey wakey the game is on
