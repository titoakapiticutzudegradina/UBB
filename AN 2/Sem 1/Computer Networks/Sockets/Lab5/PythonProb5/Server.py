import socket
import sys
import time
import threading
import re

s =  socket.socket(socket.AF_INET, socket.SOCK_DGRAM)  #for UDP use DGRAM
s.setsockopt(socket.SOL_SOCKET, socket.SO_BROADCAST, 1) # enable broadcast
s.bind(('0.0.0.0', 7777))
port = 7777
broadcastAddr = '255.255.255.255'
timeQueryString = b"TIMEQUERY\0"
dateQueryString = b"DATEQUERY\0"
peersList = dict()
malformdList = []

def sendDateQueryThread():
    global s, port , dateQueryString, broadcastAddr
    msg = b"DATEQUERY\0"

    while True:
        b  = s.sendto(msg,(broadcastAddr, port))
        if b == len(msg):
            print("Error in sending date query(nothing was sent)")
        print("message was sent")
        time.sleep(3)


def sendTimeQueryThread():
    global s, port, timeQueryString, broadcastAddr
    msg = b"TIMEQUERY\0"
    while True:
        b  = s.sendto(msg,(broadcastAddr, port))
        if b == len(msg):
            print("Error in sending time query(nothing was sent)")
        print("message was sent")
        time.sleep(10)

def regexMatch(msg,patern):
    patern = re.compile(patern)
    return patern.fullmatch(msg)

def respondThread():
    global s, port, timeQueryString, dateQueryString, broadcastAddr, peersList
    while True:
        msg, addr = s.recvfrom(1024)
        print(f"message received: {msg.decode()})
        if msg == timeQueryString:
            myTime = time.strftime("TIME %H:%M:%S")
            print(f"responding with {myTime} to ip {addr[0]}, port {addr[1]}")
            myTime = myTime.encode()
            b = s.sendto(myTime, addr)
            if b != len(myTime):
                print("Error in sending time response")
        elif msg == dateQueryString:
            myDate = time.strftime("DATE %d:%m:%Y")
            print(f"responding with {myDate} to ip {addr[0]}, port {addr[1]}")
            myDate = myDate.encode()
            b = s.sendto(myDate, addr)
            if b != len(myDate):
                print("Error in sending date response")
            else:
                msg=msg.decode()
                if regexMatch(msg,"TIME [0-9]{2}: [0-9]{2}: [0-9]{2}" ) is None:
                    malformdList.append(addr[0])
                #To do
                #for date

                #TO DO
                #if addr[0] not in peersList.keys():
                #    peersList[addr[0]] = (msg.decode(), 3)




if __name__ == '__main__':
    print("Running...")
    args = sys.argv
    if(len(args) <= 1):
        print("specify the broadcast address")
        exit(-1)
    broadcastAddr = args[1]
    threads = []
    t1 = threading.Thread(target = sendTimeQueryThread)
    t2 = threading.Thread(target = sendDateQueryThread)
    threads.append(t1)
    threads.append(t2)
    t1. start()
    t2. start()
    respondThread()
    for t in threads:
        t.join()
