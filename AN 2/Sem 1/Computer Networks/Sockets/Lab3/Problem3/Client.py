import socket, struct, random,sys, time


if __name__ == '__main__':
    try:
        s = socket.create_connection( ('localhost',1234))
    except socket.error as msg:
        print("Error: ",msg.strerror)
        exit(-1)

    finished=False
    sr = 1.0; er=100.0
    random.seed()

    data=s.recv(1024)
    print(data.decode('ascii'))

    my_num=random.uniform(sr,er)
    try:
        s.sendall( struct.pack('!d',my_num) )
        answer=s.recv(1024)
        print(answer.decode())
    except socket.error as msg:
        print('Error: ',msg.strerror)
        s.close()
        exit(-2)

    s.close()
