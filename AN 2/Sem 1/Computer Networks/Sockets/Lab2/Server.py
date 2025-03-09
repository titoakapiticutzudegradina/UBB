import socket, struct
import time

s = socket.socket(socket.AF_INET,socket.SOCK_STREAM)
s.bind(("0.0.0.0",2124))
s.listen(3)

while True:
    print("Server is listening...");
    client, addr = s.accept()

    print("Client connected from: ", addr)

    len1 = client.recv(4);
    len1 = struct.unpack('!I', len1)
    print(len1[0])
    arr1 = []
    for i in range(len1[0]):
        x = client.recv(4)
        x = struct.unpack('!I', x)
        arr1.append(x[0])

    print("Am primit arr1")
    len2 = client.recv(4);
    len2 = struct.unpack('!I', len2)

    arr2 = []
    for i in range(len2[0]):
        x = client.recv(4)
        x = struct.unpack('!I', x)
        arr2.append(x[0])
    print("Am primit arr2")

    result = []
    for i in range(len(arr1)):
        for j in range(len(arr2)):
            if arr1[i] == arr2[j]:
                result.append(arr1[i])

    count = len(result)
    print(count)
    count = struct.pack('!I', count)
    client.send(count)

    client.send(struct.pack("!{}I".format(len(result)), *result))
    client.close()
